public class Menu {
  public static Pizza[] pizzaMenu;

  public Menu() {
    Pizza Vesuvio = new Pizza(1, "Vesuvio", "tomatsauce, ost, skinke og oregano.", 57.00);
    Pizza Amerikaner = new Pizza(2, "Amerikaner", "tomatsauce, ost, oksefars og oregano.", 53.00);
    Pizza Cacciatore = new Pizza(3, "Cacciatore", "tomatsauce, ost, pepperoni og oregano.", 57.00);
    Pizza Carbona = new Pizza(4, "Carbona", "tomatsauce, ost, kødsauce, spagghetti, cocktailpølser og oregano.", 63.00);
    Pizza Dennis = new Pizza(5, "Dennis", "tomatsauce, ost, skinke, pepperoni, cocktailpølser og oregano.", 65.00);
    Pizza Bertil = new Pizza(6, "Bertil", "tomatsauce, ost, bacon og oregano.", 57.00);
    Pizza Silvia = new Pizza(7, "Silvia", "tomatsauce, ost, pepperoni, rød peber, løg, oliven og oregano.", 61.00);
    Pizza Victoria = new Pizza(8, "Victoria", "tomatsauce, ost, skinke, ananas, champignon, løg og oregano.", 61.00);
    Pizza Toronfo = new Pizza(9, "Toronfo", "tomatsauce, ost, skinke, bacon, kebab, chili og oregano.", 61.00);
    Pizza Capricciosa = new Pizza(10, "Capricciosa", "tomatsauce, ost, skinke, champignon og oregano.", 61.00);
    Pizza Hawai = new Pizza(11, "Hawai", "tomatsauce, ost, skinke, ananas og oregano.", 61.00);
    Pizza Le_Blissola = new Pizza(12, "Le_Blissola", "tomatsauce, ost, skinke, rejer og oregano.", 61.00);
    Pizza Venezia = new Pizza(13, "Venezia", "tomatsauce, ost, skinke, bacon og oregano.", 61.00);
    Pizza Mafia = new Pizza(14, "Mafia", "tomatsauce, ost, pepperoni, bacon, løg og oregano.", 61.00);
  }

  public static Pizza[] getPizzaMenu() {
    return Menu.pizzaMenu;
  }
}
