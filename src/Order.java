import java.util.ArrayList;

public class Order {
  private int id;
  private ArrayList<Pizza> pizzas;
  private double totalPrice;
  private ETA eta;

  public Order(ArrayList<Pizza> pizzas, ETA eta) {
    this.setPizzas(pizzas);
    this.setEta(eta);
  }

  public void setPizzas(ArrayList<Pizza> pizzas) {
    this.pizzas = pizzas;
  }

  public void setEta(ETA eta) {
    this.eta = eta;
  }

  public ETA getEta() {
    return this.eta;
  }

  public String toString() {
    return "Order ID: " + this.id + ", Pizza: " + this.pizzas.toString() + ", Price: " + this.totalPrice;
  }
}
