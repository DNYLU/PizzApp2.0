import java.util.ArrayList;
import java.util.List;

public class Command {
  private String primaryCommand;
  private String commandSpecifier;
  private ArrayList<String> arguments;
  private OrderManager orderManager;

  private final String CREATE_COMMAND = "new";
  private final String LIST_COMMAND = "ls";
  private final String EXIT_COMMAND = "exit";

  private final String ORDER_SPECIFIER = "order";
  private final String PIZZA_SPECIFIER = "pizza";
  private final String ACTIVE_ORDER_SPECIFIER = "active";
  private final String STORED_ORDER_SPECIFIER = "stored";

  //Todo: has some index out of bounds exceptions...
  public Command(String command, OrderManager orderManager) {
    //split is used to split the String command up for each white space
    //\s+ is regular expression for 1 or more spaces
    ArrayList<String> commandArray = new ArrayList<>(List.of(command.split("\s+")));

    //Sets the command that is to be executed when it is evaluated
    this.setPrimaryCommand(commandArray.get(0));

    if (commandArray.size() > 1) {
      //Sets the command specifier which handles what part of the orderManager the command interacts with
      this.setCommandSpecifier(commandArray.get(1));

    }

    //Removes the first and second element in the commandArray and sets the arguments that add functionality to the command
    commandArray.remove(0);
    commandArray.remove(0);
    this.setArguments(commandArray);
    System.out.println(commandArray);

    //Sets the orderManager which is the same as the one in PizzApp
    this.setOrderManager(orderManager);
  }

  public boolean evaluateCommand() {
    if (this.arguments.isEmpty() && !this.primaryCommand.equals(this.EXIT_COMMAND)) {
      //If no argument is provided to the command the program will return to PizzApp with true
      System.out.println("Please provide a argument to your command");
      return true;
    }

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
      default:
        this.invalidCommand();
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

  //Todo: there is an exception that occurs when you try and access an index that does not exits
  public void newOrder() {
    //We instantiate a new ArrayList to store the pizza numbers in
    ArrayList<Integer> pizzaNums = new ArrayList<>();

    //We loop through each argument given by the user and add it to pizzaNums
    for (int i = 0; i < this.arguments.size(); i++) {
      int pizzaNum;

      //The program checks if the entered value is a whole number and reacts accordingly if it is not
      try {
        //We subtract one from the specified index as the users menu goes from 1-30 and our menu from 0-29
        pizzaNum = Integer.parseInt(this.arguments.get(i)) - 1;
      } catch (NumberFormatException e) {
        //We set pizzaNum to -1 in this iteration to indicate an error in the command
        //OrderManager can then spot this error and avoid it
        pizzaNum = -1;
        System.out.println(this.arguments.get(i) + " is not a whole number, please enter a whole number when creating a new order");
      }
      pizzaNums.add(pizzaNum);
    }

    this.orderManager.addNewOrder(pizzaNums);
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

  public void invalidInput(String input, String commandType) {
    System.out.println(input + ": is not a valid " + commandType);
  }

  public void invalidCommand() {
    invalidInput(this.primaryCommand, "command");
  }

  public void invalidCommandSpecifier() {
    invalidInput(this.commandSpecifier, "command specifier");
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
