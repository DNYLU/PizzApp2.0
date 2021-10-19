import java.util.ArrayList;
import java.time.LocalDateTime;

public class Order {
    private static int count = 0;
    private int id;
    private ArrayList<Pizza> pizzas;
    private double totalPrice;
    private Eta eta;

    public Order(ArrayList<Pizza> pizzas, Eta eta, ArrayList<Pizza> pizzaMenu) {
        //Counts the total number of orders created
        Order.count += 1;
        //Sets the current count as the id of this order
        this.setId(Order.count);

        this.setPizzas(pizzas);
        this.calculateTotalPrice();

        this.setEta(eta);
    }

    public void calculateTotalPrice() {
        int totalPrice = 0;
        for (int i = 0; i < pizzas.size(); i++) {
            totalPrice += pizzas.get(i).getPrice();
        }

        this.setTotalPrice(totalPrice);
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

    public double getTotalPrice() {
        return totalPrice;
    }

    //Returns the LocalDateTime in the ETA class rather than the instance itself
    public LocalDateTime getEtaTime() {
        return this.eta.getEta();
    }

    public String toString() {
        return "Ordre ID: " + this.id + ", Pizza: " + this.pizzas.toString() + ", Pris: " + this.totalPrice + " Anslået leveringstid: " + this.eta;
    }
}
