import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Command {
  private String command;
  private ArrayList<String> arguments;
  private OrderManager orderManager;

  private final String CREATE_COMMAND = "new";
  private final String LIST_COMMAND = "ls";
  private final String EXIT_COMMAND = "exit";

  private final String ORDER_ARGUMENT = "order";
  private final String PIZZA_ARGUMENT = "pizza";

  public Command(String command, OrderManager orderManager) {
    //split is used to split the String command up for each white space
    ArrayList<String> commandArray = new ArrayList<>(List.of(command.split("\s+")));

    //Sets the command that is to be executed when it is evaluated
    this.setCommand(commandArray.get(0));
    //Sets the arguments that add to the command
    this.setArguments((ArrayList<String>) commandArray.subList(1, commandArray.size()));
    //Sets the orderManager wich is the same as the one in PizzApp
    this.setOrderManager(orderManager);
  }

  public boolean evaluateCommand() {
    if (this.arguments.isEmpty()) {
      //If no argument is provided to the command the program will return to PizzApp with true
      System.out.println("Please provide a argument to your command");
      return true;
    }

    //Return value that is responsible for whether the program should continue its execution
    boolean run = true;

    //Evaluates the users command and interacts with the orderManager
    switch (this.command) {
      case CREATE_COMMAND:
        create();
        break;
      case LIST_COMMAND:
        break;
      //If the user typed "exit" then run is equal to false
      //So when the program returns to PizzApp the while loop will terminate, effectively exiting the program
      case EXIT_COMMAND:
        run = false;
    }

    //The run boolean returns to the system here.
    return run;
  }

  public void create() {
    switch (this.arguments.get(1)) {
      case ORDER_ARGUMENT:
        this.newOrder();
      case PIZZA_ARGUMENT:
        break;
    }
  }

  public void newOrder() {
    //We instantiate a new ArrayList to store the pizza numbers in
    ArrayList<Integer> pizzaNums = new ArrayList<>();

    //We loop through each argument given by the user and add it to pizzaNums
    for (int i = 0; i < this.arguments.size(); i++) {
      int pizzaNum;

      //The program checks if the entered value is a whole number and reacts accordingly if it is not
      try {
        pizzaNum = Integer.parseInt(this.arguments.get(i));
      } catch (NumberFormatException e) {
        //We set pizzaNum to -1 in this iteration to indicate an error in the command
        //OrderManager can then spot this error and avoid it
        pizzaNum = -1;
        System.out.println(this.arguments.get(i) + " is not a whole number, please enter a whole number when creating a new order");
      }
      pizzaNums.add(pizzaNum);
    }

    this.orderManager.addNewOrder(pizzaNums);
  }

  public void list() {

  }

  public void setCommand(String command) {
    this.command = command;
  }

  public void setArguments(ArrayList<String> arguments) {
    this.arguments = arguments;
  }

  public void setOrderManager(OrderManager orderManager) {
    this.orderManager = orderManager;
  }

}
