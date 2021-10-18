import java.util.ArrayList;

public class Menu {
  public static ArrayList<Pizza> pizzaMenu = new ArrayList<>();

  //private static final String COLUMN_FORMAT = "%d. %-15s %s%n"; // TEST

  public static void createMenu() {
    Pizza vesuvio = new Pizza(1, "Vesuvio", "tomatsauce, ost, skinke og oregano.", 57.00);
    Pizza amerikaner = new Pizza(2, "Amerikaner", "tomatsauce, ost, oksefars og oregan.", 53.00);
    Pizza cacciatore = new Pizza(3, "Cacciatore", "tomatsauce, ost, pepperoni og oregano.", 57.00);
    Pizza carbona = new Pizza(4, "Carbona", "tomatsauce, ost, kødsauce, spaghetti, cocktailpølser og oregano.", 63.00);
    Pizza dennis = new Pizza(5, "Dennis", "tomatsauce, ost, skinke, pepperoni, cocktailpølser og oregano.", 65.00);
    Pizza bertil = new Pizza(6, "Bertil", "tomatsauce, ost, bacon og oregano.", 57.00);
    Pizza silvia = new Pizza(7, "Silvia", "tomatsauce, ost, pepperoni, rød peber, løg, oliven og oregano.", 61.00);
    Pizza victoria = new Pizza(8, "Victoria", "tomatsauce, ost, skinke, ananas, champignon, løg og oregano.", 61.00);
    Pizza toronfo = new Pizza(9, "Toronfo", "tomatsauce, ost, skinke, bacon, kebab, chili og oregano.", 61.00);
    Pizza capricciosa = new Pizza(10, "Capricciosa", "tomatsauce, ost, skinke, champignon og oregano.", 61.00);
    Pizza hawaii = new Pizza(11, "Hawaii", "tomatsauce, ost, skinke, ananas og oregano.", 61.00);
    Pizza leBlissola = new Pizza(12, "Le Blissola", "tomatsauce, ost, skinke, rejer og oregano.", 61.00);
    Pizza venezia = new Pizza(13, "Venezia", "tomatsauce, ost, skinke, bacon og oregano.", 61.00);
    Pizza mafia = new Pizza(14, "Mafia", "tomatsauce, ost, pepperoni, bacon, løg og oregano.", 61.00);

    pizzaMenu.add(vesuvio);
    pizzaMenu.add(amerikaner);
    pizzaMenu.add(cacciatore);
    pizzaMenu.add(carbona);
    pizzaMenu.add(dennis);
    pizzaMenu.add(bertil);
    pizzaMenu.add(silvia);
    pizzaMenu.add(victoria);
    pizzaMenu.add(toronfo);
    pizzaMenu.add(capricciosa);
    pizzaMenu.add(hawaii);
    pizzaMenu.add(leBlissola);
    pizzaMenu.add(venezia);
    pizzaMenu.add(mafia);
  }

  public static ArrayList<Pizza> getPizzaMenu() {
    return Menu.pizzaMenu;
  }

  public void listMenu() {
    System.out.println("-------------------------------------------------------------------------------------");
    // Test
    System.out.format("%-20s %-12s %80s%n", "Pizza", "Description", "Price");

    for (int i = 0; i < pizzaMenu.size(); i++){
/* OG
      System.out.print((i + 1) + ": " + pizzaMenu.get(i).getName() +", "+ pizzaMenu.get(i).getDescription() +" ");
      System.out.println(pizzaMenu.get(i).getPrice() + ",-"); */

      System.out.print((i + 1) + ": ");
      System.out.format("%-20s", pizzaMenu.get(i).getName());
      System.out.format("%-12s", pizzaMenu.get(i).getDescription());
      System.out.format("%60s%n", pizzaMenu.get(i).getPrice());

     /* System.out.format("%-20s %12s %10s%n", pizzaMenu.get(i).getName() +", "
              + pizzaMenu.get(i).getDescription() + " " + pizzaMenu.get(i).getPrice() + ",-");
*/

      System.out.println();
    }
    System.out.println("-------------------------------------------------------------------------------------");

  }
}
