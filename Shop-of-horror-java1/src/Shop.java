import java.util.*;

public class Shop {
    //skapar customer som kommer innehålla amvändarens val (vilken produkt och mängden)
    private Customer customer;
    // arraylista som innehåller produkterna
    private ArrayList<Product> shopMenu;

    // konstruktor som initiliserar customer (där info om användarens val sparas) och shopMenu (arraylist om valen)
    public Shop() {
        customer = new Customer();
        shopMenu = new ArrayList<>();
    }

    // metod som lägger till en produkt i shopMenu
    public void addItem(Product item) {
        shopMenu.add(item);
    }

    // metod som visar menyn - loop som visar att lagersaldot uppdateras när användaren gör ett köp
    public void viewMenu() {
        System.out.println("Menu:");
        for (int i = 0; i < shopMenu.size(); i++) {
            Product item = shopMenu.get(i);
            System.out.println(i + 1 + ". " + item.getName() + " " + item.getPrice() + " sek " + "Balance: " + item.getBalance());
        }
    }

    //metod som hanterar köpet
    public void buyItem(int productChoice, int quantity) {
        if (productChoice < 1) {// ifall användaren skriver en siffra lägre än 1
            System.out.println("Error: invalid product choice. Please choose a number between 1-3. 4 to exit");
            return;
        } else if (productChoice > shopMenu.size()) {// ifall användaren skriver en siffra högre än arraylistans längd - i mitt fall 3
            System.out.println("Error: invalid product choice. Please choose a number between 1-3. 4 to exit");
            return;
        }
        // hämtar produkten från arraylistan - -1 eftersom första produkten börjar på 0
        Product item = shopMenu.get(productChoice - 1);
        //error meddelande för ifall kunden väljer fler produkter än vad som finns i lager
        if (!item.isAvailable(quantity)) {
            System.out.println("Error: Sorry we're out of stock for this product");
            return;
        }//ifall produkten vald är discountProduct då ska rabatten läggas på, mänggden produkter valda och deras totala kostnad läggs därefter i customer
        if (item instanceof DiscountProduct) {
            DiscountProduct discountProduct = (DiscountProduct) item;
            double totalCost = discountProduct.getDiscountPrice() * quantity;
            customer.updatePurchase(quantity, totalCost);
        } else {//ifall produkten inte är en discountProduct räknas totala priset ut, det och mängden produkter läggs in i customer
            double totalCost = item.getPrice() * quantity;
            customer.updatePurchase(quantity, totalCost);
        }//uppdaterar lagersaldot efter köp
        item.adjustBalance(quantity);

    }

    //metod som visar hur många produkter har köpts och deras totalkostnad - visar bara 2 decimaler
    public void purchaseSummary() {
        System.out.println("Total products purchased: " + customer.getNumItemsBought());
        System.out.printf("Total cost: %.2f sek%n", customer.getTotalCost());
    }
}
