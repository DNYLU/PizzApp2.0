import java.util.ArrayList;

public class Order {
  private int id;
  private ArrayList<Pizza> pizzas;
  private double totalPrice;

  public Order(ArrayList<Pizza> pizzas) {
    this.setPizzas(pizzas);
  }
  public String toString() {
    return "Order ID: " + this.id + "\nPizza: " + this.pizzas.toString() + "\nPrice: " + this.totalPrice;
  }
}
