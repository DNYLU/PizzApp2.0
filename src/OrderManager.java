import java.util.ArrayList;

public class OrderManager {
  private ArrayList<Order> activeOrders = new ArrayList<>();
  private ArrayList<Order> storedOrders = new ArrayList<>();

  ArrayList<String> pizza = new ArrayList<>();

  public void addNewOrder(ArrayList<Integer> pizzaNums) {
    ArrayList<Pizza> pizzas = new ArrayList<>(pizzaNums.size());

    for (int i = 0; i < pizzaNums.size(); i++) {
      //If the value at the specified index is less than zero then Command has spotted an error in the command
      //We do not want to add that negative value to the active orders
      if (pizzaNums.get(i) >= 0) {
        pizzas.add(Menu.getPizzaMenu().get(pizzaNums.get(i)));
      }
    }

    //Instantiates a new order with all the specified pizzas in it
    Order newOrder = new Order(pizzas);
    activeOrders.add(newOrder);
  }

  public void popActiveOrder() {
    //Calls the popActiveOrders with an argument of 0 to simply remove the order at the beginning of the array.
    this.popActiveOrders(0);
  }

  public void popActiveOrders(int index) {
    //Stores the order so Mario can calculate the revenue and do statistics.
    this.storedOrders.add(this.activeOrders.get(index));
    //Removes the order from active orders at the given index.
    this.activeOrders.remove(index);
  }

  public void printActiveOrders() {
    for (int i = 0; i < activeOrders.size(); i++){

      System.out.println((i + 1) + ": " + activeOrders.get(i));
      System.out.println();
      System.out.println();
    }
  }

  public void printStoredOrders() {
    System.out.println(storedOrders);
  }

}
