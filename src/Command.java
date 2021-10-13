import java.util.Arrays;

public class Command {
  private String command;
  private String[] arguments;
  private OrderManager orderManager;

  private final String CREATE_COMMAND = "new";
  private final String LIST_COMMAND = "ls";
  private final String EXIT_COMMAND = "exit";

  public Command(String command, OrderManager orderManager) {
    //split is used to split the String command up for each white space
    String[] commandArray = command.split("\s+");

    //Sets the command that is to be executed when it is evaluated
    this.setCommand(commandArray[0]);
    //Sets the arguments that add to the command
    this.setArguments(Arrays.copyOfRange(commandArray, 1, commandArray.length - 1));
    //Sets the orderManager wich is the same as the one in PizzApp
    this.setOrderManager(orderManager);
  }

  public boolean evaluateCommand() {
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

  }

  public void setCommand(String command) {
    this.command = command;
  }

  public void setArguments(String[] arguments) {
    this.arguments = arguments;
  }

  public void setOrderManager(OrderManager orderManager) {
    this.orderManager = orderManager;
  }

}
