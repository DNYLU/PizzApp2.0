import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Command {
  private String primaryCommand;
  private String commandSpecifier = "";
  private ArrayList<String> arguments = new ArrayList<>();
  private OrderManager orderManager;
  private ArrayList<Pizza> pizzaMenu;

  private final String CREATE_COMMAND = "ny";
  private final String STORE_COMMAND = "gem";
  private final String REMOVE_COMMAND = "fjern";
  private final String LIST_COMMAND = "vis";
  private final String HELP_COMMAND = "hjælp";
  private final String EXIT_COMMAND = "afslut";

  private final String ORDER_SPECIFIER = "ordre";
  private final String PIZZA_SPECIFIER = "pizza";
  private final String ACTIVE_ORDER_SPECIFIER = "aktive";
  private final String STORED_ORDER_SPECIFIER = "gemte";
  private final String MENU_SPECIFIER = "menu";
  private final String FIRST_SPECIFIER = "første";
  private final String INDEX_SPECIFIER = "nummer";


  private final String COMMAND_ARGUMENT_SPLITTER = ":";

  public Command(String command, OrderManager orderManager, ArrayList<Pizza> pizzaMenu) {
    this.pizzaMenu = pizzaMenu;
    //We instantiate two strings, one to store the primaryCommand and commandSpecifier and one to store the arguments
    String commandAndSpecifier;
    String arguments = "";
    //If there is a token representing a split between the primaryCommand and commandSpecifier then we execute this if block
    if (command.contains(this.COMMAND_ARGUMENT_SPLITTER)) {
      //We use .substring to get the parts of the string we want and trim it to remove whitespace as it could cause errors
      commandAndSpecifier = command.substring(0, command.indexOf(this.COMMAND_ARGUMENT_SPLITTER)).trim();
      //We add 1 to the index of the substring otherwise the program would also add the command argument splitter token to argumentsArray
      arguments = command.substring(command.indexOf(this.COMMAND_ARGUMENT_SPLITTER) + 1).trim();
    } else {
      //If there is no token representing the split then we simply set the commandAndSpecifier to be the entire command
      commandAndSpecifier = command;
    }

    //Here we instantiate two ArrayLists one to store the primaryCommand and the commandSpecifier
    //And one to store the arguments
    //\s+ is regular expression for 1 or more white spaces
    ArrayList<String> commandArray = new ArrayList<>(List.of(commandAndSpecifier.split("\s+")));
    ArrayList<String> argumentArray = new ArrayList<>(List.of(arguments.split("\s+")));

    //Sets the command that is to be executed when it is evaluated
    this.setPrimaryCommand(commandArray.get(0));

    //if there is one or more elements in the CommandArray then the program will set the second element as the commandSpecifier
    if (commandArray.size() > 1) {
      this.setCommandSpecifier(commandArray.get(1));
    }

    this.setArguments(argumentArray);

    //Sets the orderManager which is the same as the orderManager in pizzApp
    this.setOrderManager(orderManager);
  }

  public boolean evaluateCommand() {
    //Return value that is responsible for whether the program should continue its execution
    boolean run = true;

    //Evaluates the users command and interacts with the orderManager
    switch (this.primaryCommand) {
      case CREATE_COMMAND:
        create();
        break;
      case STORE_COMMAND:
        this.store();
        break;
      case REMOVE_COMMAND:
        this.removeOrder();
        break;
      case LIST_COMMAND:
        list();
        break;
      case HELP_COMMAND:
        this.help();
        break;
      //If the user typed "exit" then run is equal to false
      //So when the program returns to PizzApp the while loop will terminate, effectively exiting the program
      case EXIT_COMMAND:
        System.out.println("Afslutter programmet");
        run = false;
        break;
      default:
        this.invalidPrimaryCommand();
        break;
    }

    //The run boolean returns to PizzApp
    return run;
  }

  public void create() {
    switch (this.commandSpecifier) {
      case ORDER_SPECIFIER:
        this.newOrder(pizzaMenu);
      case PIZZA_SPECIFIER:
        //Todo: implement if there is time
        break;
      default:
        this.invalidCommandSpecifier();
        break;
    }
  }

  public void store() {
    switch (this.commandSpecifier) {
      case FIRST_SPECIFIER:
        this.storeFirstOrder();
        break;
      case INDEX_SPECIFIER:
        this.storeOrders();
        break;
      default:
        this.invalidCommandSpecifier();
        break;
    }
  }

  public void list() {
    switch (this.commandSpecifier) {
      case ACTIVE_ORDER_SPECIFIER:
        this.orderManager.printActiveOrders();
        break;
      case STORED_ORDER_SPECIFIER:
        this.orderManager.printStoredOrders();
        break;
      case MENU_SPECIFIER:
        Menu menu = new Menu();
        menu.listMenu();
        break;
      default:
        this.invalidCommandSpecifier();
        break;
    }
  }

  public void help() {
    switch (this.commandSpecifier) {
      //"" is meant to catch if the user simply types in "help" so it prints out all the commands
      case "":
        this.printCommands();
        break;
      case CREATE_COMMAND:
        this.printCommandCreate();
        break;
      case LIST_COMMAND:
        this.printCommandList();
        break;
      case STORE_COMMAND:
        this.printCommandStore();
        break;
      case REMOVE_COMMAND:
        this.printCommandRemove();
      default:
        this.invalidCommandSpecifier();
    }
  }

  public ArrayList<Integer> convertStringsToInts(String orderAction, String targetName, int targetSize) {
    String outsideOfRangeMsg = "venligst indtast et nummer inden for " + targetName + " længde";
    ArrayList<Integer> intArray = new ArrayList<>(this.arguments.size());

    for (String argument : this.arguments) {
      int index;

      //The program checks if the entered value is a whole number and reacts accordingly if it is not
      try {
        //We subtract one from the specified index as out arrays start from 0 and most people starting counting from 1
        index = Integer.parseInt(argument) - 1;
      } catch (NumberFormatException e) {
        //We call invalidOrderAction and pass the orderAction so that the method can print the correct error message
        this.invalidOrderAction(orderAction, argument + " er ikke et helt tal, venligst indtast et hel tal når du indtaster en ny ordre.");
        //if the cashier didn't enter a whole number then we want to return from this method to avoid further errors
        return new ArrayList<>();
      }

      if (index < 0) {
        this.invalidOrderAction(orderAction, argument + " er mindre end 1, " + outsideOfRangeMsg);
        return new ArrayList<>();

      } else if (index >= targetSize) {
        this.invalidOrderAction(orderAction, argument + " er for højt et tal, " + outsideOfRangeMsg);
        return new ArrayList<>();
      }

      intArray.add(index);
    }
    return intArray;
  }

  //Checks if the arguments are within the range of the menu
  //Calls on the orderManager to create a new order with the given pizza number
  public void newOrder(ArrayList<Pizza> pizzaMenu) {
    ArrayList<Integer> pizzaNums = convertStringsToInts("oprettet", "menuen", pizzaMenu.size());

    if (pizzaNums.isEmpty()) {
      return;
    }

    //Enters a loop where the user is expected to enter the estimated time of arrival
    Eta eta = this.createEta();

    //Here the program adds the newly created order to the active orders via the orderManager
    this.orderManager.addNewOrder(pizzaNums, eta, pizzaMenu);
  }

  public void newPizza() {
    //Todo: implement this method if there is time
  }

  public void storeFirstOrder() {
    this.arguments = new ArrayList<>();
    this.arguments.add("1");

    ArrayList<Integer> firstIndex = this.convertStringsToInts("gemt", "aktive listes", this.orderManager.getActiveOrders().size());
    if (firstIndex.isEmpty()) {
      return;
    }

    this.orderManager.storeActiveOrders();
  }

  public void storeOrders() {
    ArrayList<Integer> storedIndexes = this.convertStringsToInts("gemt", "aktive listes", this.orderManager.getActiveOrders().size());

    if (storedIndexes.isEmpty()) {
      return;
    }
    orderManager.storeActiveOrders(storedIndexes);
  }

  public void removeOrder() {
    ArrayList<Integer> removeIndexes = this.convertStringsToInts("fjernet", "aktive liste", this.orderManager.getActiveOrders().size());

    if (removeIndexes.isEmpty()) {
      return;
    }
    this.orderManager.removeActiveOrders(removeIndexes);
  }

  public Eta createEta() {
    //We instantiate the scanner where we will get the input
    Scanner scanner = new Scanner(System.in);
    //We instantiate the boolean that will determine the loops continued execution
    boolean etaCreated = false;
    //Sets an ETA instance to null although the flow should ensure that it won't ever return a null value
    Eta eta = null;

    while (!etaCreated) {
      System.out.println("Venligst indtast hvornår kunden ankommer for at hente sin bestilling.");
      System.out.println("minut: \" " + Eta.getMinuteOption() + "\" eller time: \"" + Eta.getHourOption() +
              "\" efterfulgt af hvor mange min/timer. f.eks. -m 10");
      String etaInput = scanner.nextLine().toLowerCase().trim();
      String[] etaInputArray = etaInput.split("\s+");

      if (etaInputArray.length < 2) {
        System.out.println("Ugyldigt input.");
      } else {
        int time;
        try {
          time = Integer.parseInt(etaInputArray[1]);
          eta = new Eta(etaInputArray[0], time);
          etaCreated = true;
        } catch (NumberFormatException e) {

        }
      }
    }
    return eta;
  }

  public void printCommands() {
    this.printCommandCreate();
    this.printCommandList();
    this.printCommandStore();
    this.printCommandRemove();
    this.printCommandExit();
  }

  public void printCommandCreate() {
    System.out.println("\"" + this.CREATE_COMMAND + "\" bruges med nedenstående muligheder:");
    System.out.println("\t\"" + this.ORDER_SPECIFIER + "\": opretter en ny ordre.");
    System.out.println("\t\tHusk at tilføje pizzaerne via deres nummer efter et \":\". for eksempel: \"" +
            this.CREATE_COMMAND + " " + this.ORDER_SPECIFIER + "\" : 1 2 3\".");

    System.out.println();
  }

  public void printCommandList() {
    System.out.println("\"" + this.LIST_COMMAND + "\" bruges med følgende muligheder.");
    System.out.println("\t\"" + this.ACTIVE_ORDER_SPECIFIER + "\": viser listen over de aktive ordre.");
    System.out.println("\t\"" + this.STORED_ORDER_SPECIFIER + "\": viser listen over gemte ordre.");
    System.out.println("\t\"" + this.MENU_SPECIFIER + "\": viser menu kortet.");

    System.out.println();
  }

  public void printCommandStore() {
    System.out.println("\"" + this.STORE_COMMAND + "\": bruges med nedenstående muligheder:");
    System.out.println("\t\"" + this.FIRST_SPECIFIER + "\": gemmer den første ordre i listen over aktive ordre.");
    System.out.println("\t\"" + this.INDEX_SPECIFIER + "\": gemmer en ordre ift. dens position i listen over aktive ordre." +
            " for eksempel \"" + this.STORE_COMMAND + " " + this.INDEX_SPECIFIER + "\": 1 2 3.");

    System.out.println();

  }

  public void printCommandRemove() {
    System.out.println("\"" + this.REMOVE_COMMAND + "\" fjerner en ordre fra den aktive liste som ikke gemmes.");
    System.out.println("\tHusk at skrive nummeret på den ordre du gerne vil fjerne," +
            " f.eks. \"" + this.REMOVE_COMMAND + "\" : 1 2 3");

    System.out.println();
  }

  public void printCommandExit() {
    System.out.println("\"" + this.EXIT_COMMAND + "\": afslutter programmet.");
    System.out.println();
  }

  //displayInvalidInput is called by all other methods that prints out errors to the console
  //This methods displays the command entered by the user
  public void displayInvalidInput() {
    System.out.println("Din kommando:");
    System.out.println("\t\"" + this + "\"");
    //We add a new line to the console so everything looks more clean
    System.out.println("indtast \"" + this.HELP_COMMAND + "\" for at se alle de mulige kommandoer.");
    System.out.println();
  }

  //commandNotExecuted prints out the part of the command that is not valid
  //It also prints out the command type, primaryCommand or commandSpecifier
  //It then calls the displayInvalidInput method
  public void commandNotExecuted(String input, String commandType) {
    System.out.println("Din kommando blev ikke udført, se begrundelsen nedenunder:");
    System.out.println("\t\"" + input + "\" er ikke en gyldig " + commandType);
    displayInvalidInput();
  }

  //Is called when a primaryCommand is invalid
  //It calls commandNotExecuted and passes the primaryCommand as the input and command as the commandType
  public void invalidPrimaryCommand() {
    commandNotExecuted(this.primaryCommand, "kommando");
  }

  //Is called when a commandSpecifier is invalid
  //it calls commandNotExecuted() and passes the commandSpecifier as the input and commandSpecifier as the commandType
  public void invalidCommandSpecifier() {
    commandNotExecuted(this.commandSpecifier, "kommando mulighed");
  }

  //If an order could not be created then this method is called, it prints out that the order could not be created
  //It also gives the reason why the order could not be created
  //Then it calls displayInvalidInput method
  public void invalidOrderAction(String action, String reason) {
    System.out.println("Ordren blev ikke " + action + ", se begrundelsen nedenunder:");
    System.out.println("\t" + reason);

    this.displayInvalidInput();
  }

  public String toString() {
    return this.primaryCommand + " " + this.commandSpecifier + " " + this.arguments;
  }

  public void setPrimaryCommand(String primaryCommand) {
    this.primaryCommand = primaryCommand;
  }

  public void setCommandSpecifier(String commandSpecifier) {
    this.commandSpecifier = commandSpecifier;
  }

  public void setArguments(ArrayList<String> arguments) {
    this.arguments = arguments;
  }

  public void setOrderManager(OrderManager orderManager) {
    this.orderManager = orderManager;
  }
}
