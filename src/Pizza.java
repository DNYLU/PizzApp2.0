public class Pizza {
  private int number;
  private String name;
  private String description;
  private double price;

  // Constructor
  public Pizza(int number, String name, String description, double price){
    this.number = number;
    this.name = name;
    this.description = description;
    this.price = price;
  }

  //Setter number til et nyt tal
  public void setNumber(int number) {
    this.number = number;
  }

  // Returner pizzaens nummer
  public int getNumber() {
    return this.number;
  }

  //Setter pizzaens navn
  public void setName(String name) {
    this.name = name;
  }

  //Returner pizzaens navn
  public String getName() {
    return this.name;
  }
  public String toString() {
    return this.name;
  }

  //Setter pizzaens beskrivelse
  public void setDescription(String description) {
    this.description = description;
  }

  //Henter pizzaens beskrivelse
  public String getDescription() {
    return this.description;
  }

  //Setter pizzaens pris
  public void setPrice(int price) {
    this.price = price;
  }

  //Henter pizzaens pris
  public double getPrice() {
    return this.price;
  }
}
