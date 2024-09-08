//import java.util.InputMismatchException;

public class HangmanMain
{
    public static void main(String[] args) {

        Hangman game = new Hangman();
        //number of incorrect guesses allowed

        //Boolean to determine if game is running or not
        boolean running = true;
//--------------------------------------

        System.out.println("Welcome to Hangman!\n");




        while (running) {
            System.out.println("Enter: 1 to View Rules -- 2 to Start Game -- 0 to Quit\n");

            int choice = game.input.nextInt();

            switch (choice) {
                case 1 -> HangmanRules.displayRules();
                case 2 -> {
                    System.out.println("Game Starting...");
                    game.startGame();

                }
                case 0 -> running = false;
                default -> System.out.println("Illegal character pressed\n");
            }
        }

        System.out.println("Thank you for playing.");

    }

}
