import java.util.Scanner;

public class PizzApp {
  private OrderManager orderManager = new OrderManager();
  private Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    Menu.createMenu();
    PizzApp pizzApp = new PizzApp();
    pizzApp.run();
  }

  public void run() {
    //run is responsible for weather or not the program should be running
    boolean run = true;

    while (run) {
      System.out.print("Please enter your command -> ");
      //Gets the users command, makes it lowercase and removes any whitespace from the beginning and end of the string
      String userCommand = this.scan.nextLine().toLowerCase().trim();
      //Adds a new line to the console so everything looks clean
      System.out.println();

      //Creates a new command and passes the users command and the orderManager for this pizzApp to it
      Command command = new Command(userCommand, this.orderManager);
      //Executes the evaluateCommand method in Command which returns a boolean
      run = command.evaluateCommand();
    }
  }
}
