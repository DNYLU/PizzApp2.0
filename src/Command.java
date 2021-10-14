import java.util.ArrayList;
import java.util.List;

public class Command {
  private String primaryCommand;
  private String commandSpecifier = "";
  private ArrayList<String> arguments = new ArrayList<>();
  private OrderManager orderManager;

  private final String CREATE_COMMAND = "new";
  private final String STORE_COMMAND = "store";
  private final String LIST_COMMAND = "ls";
  private final String EXIT_COMMAND = "exit";

  private final String ORDER_SPECIFIER = "order";
  private final String PIZZA_SPECIFIER = "pizza";
  private final String ACTIVE_ORDER_SPECIFIER = "active";
  private final String STORED_ORDER_SPECIFIER = "stored";
  private final String MENU_SPECIFIER = "menu";


  private final String COMMAND_ARGUMENT_SPLITTER = ":";

  public Command(String command, OrderManager orderManager) {
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
        break;
      case LIST_COMMAND:
        list();
        break;
      //If the user typed "exit" then run is equal to false
      //So when the program returns to PizzApp the while loop will terminate, effectively exiting the program
      case EXIT_COMMAND:
        System.out.println("Terminating program");
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
        this.newOrder();
      case PIZZA_SPECIFIER:
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

  //Checks if the arguments are within the range of the menu
  //Calls on the orderManager to create a new order with the given pizza number
  public void newOrder() {
    //We instantiate a new ArrayList to store the pizza numbers in
    ArrayList<Integer> pizzaNums = new ArrayList<>();

    String outsideOfRangeMsg = "please provide a value within the menu range";

    //We loop through each argument given by the user and add it to pizzaNums
    for (int i = 0; i < this.arguments.size(); i++) {
      int pizzaNum;

      //The program checks if the entered value is a whole number and reacts accordingly if it is not
      try {
        //We subtract one from the specified index as the users menu goes from 1-30 and our menu from 0-29
        pizzaNum = Integer.parseInt(this.arguments.get(i)) - 1;
      } catch (NumberFormatException e) {
        //We call orderNotCreated and give it the reason why the order was not created
        this.orderNotCreated(this.arguments.get(i) + " is not a whole number, please enter a whole number when creating a new order");
        //if the cashier didn't enter a whole number then we want to return from this method and not create a new order
        return;
      }

      //If the number entered by the user is less than zero the program will print out an error and return to pizzApp
      if (pizzaNum < 0) {
        //orderNotCreated prints out an error message to the user, first argument is the input and the second is the reason
        this.orderNotCreated(this.arguments.get(i) + " is less than 1, " + outsideOfRangeMsg);
        return;
      } else if (pizzaNum >= Menu.getPizzaMenu().size()) {
        this.orderNotCreated(this.arguments.get(i) + " is greater than the length of the menu, " + outsideOfRangeMsg);
        return;
      }
      pizzaNums.add(pizzaNum);
    }
    //Here the program adds the newly created order to the active orders via the orderManager
    this.orderManager.addNewOrder(pizzaNums);
    //Here the program prints all the active orders to the console via the order Manager
    this.orderManager.printActiveOrders();
  }

  public void newPizza() {

  }

  public void storeOrder() {

  }

  //displayInvalidInput is called by all other methods that prints out errors to the console
  //This methods displays the command entered by the user
  public void displayInvalidInput() {
    System.out.println("Your command:");
    System.out.println("\t" + this);
    //We add a new line to the console so everything looks more clean
    System.out.println();
  }

  //commandNotExecuted prints out the part of the command that is not valid
  //It also prints out the command type, primaryCommand or commandSpecifier
  //It then calls the displayInvalidInput method
  public void commandNotExecuted(String input, String commandType) {
    System.out.println("Your command was not executed see below:");
    System.out.println("\t" + input + " is not a valid " + commandType);
    displayInvalidInput();
  }

  //Is called when a primaryCommand is invalid
  //It calls commandNotExecuted and passes the primaryCommand as the input and command as the commandType
  public void invalidPrimaryCommand() {
    commandNotExecuted(this.primaryCommand, "command");
  }

  //Is called when a commandSpecifier is invalid
  //it calls commandNotExecuted() and passes the commandSpecifier as the input and commandSpecifier as the commandType
  public void invalidCommandSpecifier() {
    commandNotExecuted(this.commandSpecifier, "command specifier");
  }

  //If an order could not be created then this method is called, it prints out that the order could not be created
  //It also gives the reason why the order could not be created
  //Then it calls displayInvalidInput method
  public void orderNotCreated(String reason) {
    System.out.println("Order was not created see below:");
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
