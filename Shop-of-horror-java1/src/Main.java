// Shop of horror - Kean von Wowern

import java.util.*;
public class Main{
    public static void main(String[] args) {
        // skapar shop klassen
        Shop shop = new Shop();
        //skapar produkter olika variablernamn. Innehåller 1 sträng, 1 double och 1 integer
        Product product1 = new Product("Fake blood", 74.95, 11);
        Product product2 = new Product("Skeleton", 299.95, 7);
        // skapar en rabaterad produkt där rabatten dras av vid slutet. innehåller 1 sträng, 2 doubles och 1 integer
        DiscountProduct discountProduct = new DiscountProduct("Retractable knife", 99.95, 0.2, 14);
        //lägger till produkterna i shop
        shop.addItem(product1);
        shop.addItem(product2);
        shop.addItem(discountProduct);
        
        Scanner scanner = new Scanner (System.in);
        //loop som fortsätter till användaren skriver 4.
        while (true) {
            shop.viewMenu(); // kallar metoden som innehåller en loop som hämtar shopMenu och printar ut namn, pris och uppddaterat lagersaldo  vid köp
            System.out.println("Enter a number between 1-3 to buy a product. 4 to exit: ");
            int choice = scanner.nextInt();
            
            if (choice ==4){
                break;
            } else if (choice < 1) { //ifall siffran är lägre än 1 spelas detta upp och loopen fortsätter
                System.out.println("Error: invalid choice, please enter a number between 1-3. 4 to exit");
                continue;
            }
            else if (choice > 3) {//ifall siffran är högre än 3 spelas detta upp och loopen fortsätter
                System.out.println("Error: invalid choice, please enter a number between 1-3. 4 to exit");
                continue;
            }
            System.out.println("Enter how many products you wish to buy");
            int quantity = scanner.nextInt();
            //kallar buyItem metoden som lägger in vilken produkt och hur många användaren ville ha
            shop.buyItem(choice, quantity);
        }
        //metoden som spelar upp summan av alla produkter och dess kostnad
       shop.purchaseSummary();


    }
}



