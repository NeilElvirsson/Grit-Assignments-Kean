import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Variabler
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        //While loop som körs så länge spelaren vill spela - när denna skriver "no" blir playAgain false och koden körs inte
        while (playAgain) {
            // genererar ny random siffra mellan 1 och 100
            int randomNumber = rand.nextInt(100) + 1;
            int tryCounter = 0;

            // System.out.println("the number is " + randomNumber); - kod för att berätta vad siffra är - kan köras för att kolla så allt funkar
            while (true) {
                System.out.println("Enter your guess (1-100):");

                int guess = scanner.nextInt();
                // tryCounter startar på 0 och plussar på 1 för varje gissning eller loop som körs tills kravet uppfylls - alltså rätt siffra gissat
                tryCounter++;

                //uppdaterering - kallar metoden som evaluerar ifall guess == randomNumber
                if (evaluate(guess, randomNumber)) {
                    System.out.println("Correct! You win!");
                    System.out.println("It took you " + tryCounter + " attempts");
                    break;
                }
            }
            //frågar om användaren vill spela igen
            System.out.println("Do you want to play again? (yes/no)");
            String playAgainResponse = scanner.next();
            // sätter min boolean playAgain till false ifall användar svarar "no"(eller något annat än en variation av yes) och koden skippas
            if (!playAgainResponse.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }
        //stänger scannern
        scanner.close();
    }
    //metoden som evaluerar ifall guess==randomNumber
    public static boolean evaluate(int guess, int randomNumber) {
        if (guess == randomNumber) {
            return true; //returnerar true ifall det är rätt
        } else if (randomNumber > guess) {
            System.out.println("Wrong! The number is higher. Guess again!");
        } else {
            System.out.println("Wrong! The number is lower. Guess again!");
        }
        return false; //returnerar false ifall det är fel
    }
}
