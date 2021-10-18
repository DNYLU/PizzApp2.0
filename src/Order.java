import java.util.ArrayList;
import java.time.LocalDateTime;

public class Order {
  private static int count = 0;
  private int id;
  private ArrayList<Pizza> pizzas;
  private double totalPrice;
  private Eta eta;

  public Order(ArrayList<Pizza> pizzas, Eta eta) {
    Order.count += 1;
    this.setId(Order.count);

    this.setPizzas(pizzas);
    this.setEta(eta);


  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public void setId(int id) {
    this.id = id;
  }
  public void setPizzas(ArrayList<Pizza> pizzas) {
    this.pizzas = pizzas;
  }

  public void setEta(Eta eta) {
    this.eta = eta;
  }

  public Eta getEta() {
    return this.eta;
  }

  //Returns the LocalDateTime in the ETA class rather than the instance itself
  public LocalDateTime getEtaTime() {
    return this.eta.getEta();
  }

  public String toString() {
    return "Ordre ID: " + this.id +  ", Pizza: " + this.pizzas.toString() + ", Pris: " + this.totalPrice + " Anslået leveringstid: " + this.eta;
  }
}
