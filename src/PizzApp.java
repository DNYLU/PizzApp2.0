import java.util.Scanner;

public class PizzApp {
  private OrderManager orderManager = new OrderManager();
  private Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
  }

  public void run() {
    //Boolean that is responsible for weather or not the program should be running
    boolean run = true;

    while (run) {
      String userCommand = this.scan.nextLine();
      Command command = new Command(userCommand, this.orderManager);
      run = command.evaluateCommand();
    }
  }
}
