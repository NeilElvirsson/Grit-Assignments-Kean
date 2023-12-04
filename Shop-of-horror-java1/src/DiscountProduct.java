public class DiscountProduct extends Product {

    //variabel som innehåller rabatten
    private double discount;

    //konstruktor som skapar DiscountProduct och vad den ska innehålla
    public DiscountProduct(String name, double price, double discount, int initialBalance) {
        super(name, price, initialBalance); // drar namn, pris och lagersaldo från konstruktor i föräldrarklassen
        this.discount = discount; // hämtar rabatten och anger värdet till discount variabel
    }

    //metod för att räkna ut och ge priset av den rabbaterade produkten
    public double getDiscountPrice() {// kallar på getPrice för att få det orginella priset och multiplicerar det med (1- discount (i mitt fall 0.2)) för tillämpa rabatten
        return getPrice() * (1 - discount);
    }

}