public class Order {
  private int id;
  private Pizza[] pizzas;
  private double totalPrice;

  public Order(Pizza[] pizzas) {
    this.pizzas = pizzas;
  }
}
