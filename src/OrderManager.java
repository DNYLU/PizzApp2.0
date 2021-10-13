import java.util.ArrayList;

public class OrderManager {
  private ArrayList<Order> activeOrders;
  private ArrayList<Order> storedOrders;

  ArrayList<String> pizza = new ArrayList<>();

  public void addNewOrder(int[] pizzaNums) {
    Pizza[] pizzas = new Pizza[pizzaNums.length];

    for (int i = 0; i < pizzas.length; i++) {
      pizzas[i] = Menu.getPizzaMenu()[pizzaNums[i]];
    }

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

      System.out.println(activeOrders);
    }
  }

  public void printStoredOrders() {
    System.out.println(storedOrders);
  }

}
