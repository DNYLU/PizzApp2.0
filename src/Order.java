import java.util.ArrayList;

public class Order {
  private int id;
  private ArrayList<Pizza> pizzas;
  private double totalPrice;

  public Order(ArrayList<Pizza> pizzas) {
    this.setPizzas(pizzas);
  }
}
