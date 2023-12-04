public class Product {
    //variabler för info om produkterna
    private String name; //namn
    private double price;//pris
    private int balance;//lagersaldo

    //konstruktor som skapar Product
    public Product (String name, double price, int initialBalance) { //initialiserar med värdena angivna (finns i main)
        this.name = name;
        this.price = price;
        this.balance = initialBalance;
    }

    //get metod som hämtar namnet
    public String getName(){
        return name;
    }

    //get metod som hämtar priset
    public double getPrice(){
        return price;
    }

    //get metod som hämtar lagersaldot
    public int getBalance(){
        return balance;
    }

    // metod osm justerar lagersaldot vid köp
    public void adjustBalance(int quantity) { //lagersaldot minus mängd köpt
        this.balance -= quantity;
    }

    //boolean som kollar ifall angivna önskade mängd är högre än lagersaldot
    public boolean isAvailable (int quantity){
        return quantity <= balance; //true ifall angivna siffran är samma eller lägre än lagersaldot
    }


}
