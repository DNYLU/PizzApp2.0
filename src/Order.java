import java.util.ArrayList;
import java.time.LocalDateTime;

public class Order {
  private static int count = 0;
  private int id;
  private ArrayList<Pizza> pizzas;
  private double totalPrice;
  private ETA eta;

  public Order(ArrayList<Pizza> pizzas, ETA eta) {
    // Order ID
    Order.count += 1;
    this.setId(Order.count);

    this.setPizzas(pizzas);
    this.setEta(eta);


  }

  //test
  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public void setId(int id) {
    this.id = id;
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

  //Returns the LocalDateTime in the ETA class rather than the instance itself
  public LocalDateTime getEtaTime() {
    return this.eta.getEta();
  }

  public String toString() {
    return "Order ID: " + this.id +  ", Pizza: " + this.pizzas.toString() + ", Price: " + this.totalPrice + " ETA: " + this.eta;
  }
}
