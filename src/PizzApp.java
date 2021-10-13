import java.util.Scanner;

public class PizzApp {
  private OrderManager orderManager = new OrderManager();
  private Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    PizzApp pizzApp = new PizzApp();
    pizzApp.run();
  }

  public void run() {
    //run is responsible for weather or not the program should be running
    boolean run = true;

    while (run) {
      String userCommand = this.scan.nextLine().toLowerCase().trim();

      Command command = new Command(userCommand, this.orderManager);
      run = command.evaluateCommand();
    }
  }
}
