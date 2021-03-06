import java.util.ArrayList;
import java.util.Collections;

public class OrderManager {
    private ArrayList<Order> activeOrders = new ArrayList<>();
    private ArrayList<Order> storedOrders = new ArrayList<>();

    ArrayList<String> pizza = new ArrayList<>();

    public void addNewOrder(ArrayList<Integer> pizzaIndexes, Eta eta, ArrayList<Pizza> pizzaMenu) {
        ArrayList<Pizza> pizzas = new ArrayList<>(pizzaIndexes.size());

        for (int i = 0; i < pizzaIndexes.size(); i++) {
            //If the value at the specified index is less than zero then Command has spotted an error in the command
            //We do not want to add that negative value to the active orders
            if (pizzaIndexes.get(i) >= 0) {
                pizzas.add(pizzaMenu.get(pizzaIndexes.get(i)));
            }
        }

        //Instantiates a new order with all the specified pizzas in it
        Order newOrder = new Order(pizzas, eta, pizzaMenu);

        this.addToActiveOrders(newOrder);
        this.printActiveOrders();
    }

    public void addToActiveOrders(Order order) {
        if (this.activeOrders.isEmpty()) {
            this.activeOrders.add(order);
        } else {
            int orderIndex = this.activeOrders.size();
            for (int i = 0; i < this.activeOrders.size(); i++) {
                //When we find the first order that has an eta after the new order we break the loop
                //The program has found the index it needed so there is no reason to continue the loop
                //The sorting will actually be incorrect if we continue the loop as it is
                if (this.activeOrders.get(i).getEtaTime().isAfter(order.getEtaTime())) {
                    orderIndex = i;
                    break;
                }
            }

            this.activeOrders.add(orderIndex, order);
        }
    }

    public void storeActiveOrders() {
        //Calls the popActiveOrders with an ArrayList that only contains a single element, which is 0
        //So only the first element will be removed
        ArrayList<Integer> firstElement = new ArrayList<>();
        firstElement.add(0);
        this.storeActiveOrders(firstElement);
    }

    public void storeActiveOrders(ArrayList<Integer> indexes) {
        Collections.sort(indexes);

        for (int i = indexes.size() - 1; i >= 0; i--) {
            //Stores the order so that revenue and statistics can be made at a later time
            this.storedOrders.add(this.activeOrders.get(indexes.get(i)));
            //Removes the order from active orders at the given index.
            this.activeOrders.remove(indexes.get(i).intValue());
        }
        this.printActiveOrders();
    }

    public void removeActiveOrders(ArrayList<Integer> indexes) {
        Collections.sort(indexes);

        for (int i = indexes.size() - 1; i >= 0; i--) {
            this.activeOrders.remove(indexes.get(i).intValue());
        }
        this.printActiveOrders();
    }

    public void printActiveOrders() {
        for (int i = 0; i < activeOrders.size(); i++) {

            System.out.println((i + 1) + ": " + activeOrders.get(i));
            System.out.println();
            System.out.println();
        }
    }

    public void printStoredOrders() {
        System.out.println(storedOrders);
    }

    public ArrayList<Order> getActiveOrders() {
        return this.activeOrders;
    }


}
