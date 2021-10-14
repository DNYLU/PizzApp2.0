import java.util.ArrayList;
import java.util.List;

public class Command {
  private String primaryCommand;
  private String commandSpecifier = "";
  private ArrayList<String> arguments = new ArrayList<>();
  private OrderManager orderManager;

  private final String CREATE_COMMAND = "new";
  private final String LIST_COMMAND = "ls";
  private final String EXIT_COMMAND = "exit";

  private final String ORDER_SPECIFIER = "order";
  private final String PIZZA_SPECIFIER = "pizza";
  private final String ACTIVE_ORDER_SPECIFIER = "active";
  private final String STORED_ORDER_SPECIFIER = "stored";

  public Command(String command, OrderManager orderManager) {
    //split is used to split the String command up for each white space
    //\s+ is regular expression for 1 or more spaces
    ArrayList<String> commandArray = new ArrayList<>(List.of(command.split("\s+")));

    //Sets the command that is to be executed when it is evaluated
    this.setPrimaryCommand(commandArray.get(0));

    //If there are more inputs from the user than the primary command we set them in the following if statements
    //The first if statement sets the commandSpecifier
    if (commandArray.size() > 1) {
      //Removes the first element in the command array which was set to be the primaryCommand
      commandArray.remove(0);
      //Sets the command specifier which handles what part of the orderManager the command interacts with
      this.setCommandSpecifier(commandArray.get(0));

      //This if statement sets the arguments
      if (commandArray.size() > 0) {
        //Removes the first element in the commandArray which used to be the primaryCommand but is now the CommandSpecifier
        commandArray.remove(0);
        //Sets the arguments which adds data to the primaryCommand
        this.setArguments(commandArray);
      }
    }

    //Sets the orderManager which is the same as the one in PizzApp
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

  public void list() {
    switch (this.commandSpecifier) {
      case ACTIVE_ORDER_SPECIFIER:
        this.orderManager.printActiveOrders();
        break;
      case STORED_ORDER_SPECIFIER:
        this.orderManager.printStoredOrders();
        break;
      default:
        this.invalidCommandSpecifier();
        break;
    }
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
  public void commandNotExecuted(String input, String commandType) {
    System.out.println("Your command was not executed see below:");
    System.out.println("\t" + input + " is not a valid " + commandType);
    displayInvalidInput();
  }

  public void invalidPrimaryCommand() {
    commandNotExecuted(this.primaryCommand, "command");
  }

  public void invalidCommandSpecifier() {
    commandNotExecuted(this.commandSpecifier, "command specifier");
  }

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
