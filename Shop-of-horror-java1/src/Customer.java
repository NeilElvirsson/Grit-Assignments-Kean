public class Customer {
    //håller koll på hur många produkter har köpts av kunden
    private int numItemsBought;

    //håller koll på kostnaden för produkterna köpts
    private double cost;

    //konstruktor för customer
    public Customer(){//startar med noll produkter köpta med total pris på 0 - kommer uppdateras med nya värden
        this.numItemsBought = 0;
        this.cost = 0;
    }
    // get metod för att hämta totala mängden produkter köpta
    public int getNumItemsBought(){
        return numItemsBought;
    }

    //get metod för att hämta den totala kostnaden för alla produkter
    public double getTotalCost() {
        return cost;
    }

    //metod för att uppdatera information om hur många produkter köpts och dess totalkostnad
    public void updatePurchase(int numOfItems, double totalCost){
        this.numItemsBought += numOfItems;
        this.cost +=totalCost;
    }

}
