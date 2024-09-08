import java.util.*;

public class Hangman {

    //initialize array list for hangman
    private ArrayList<String> wordsList = new ArrayList<>();

    private Set<Character> guessedLetters;

    private int incorrectGuesses = 0;

    private int maxGuesses = 9;

    Scanner input = new Scanner(System.in);

    public Hangman (){
        //Adding 8 different words to list
        wordsList.add("Billy");
        wordsList.add("College");
        wordsList.add("Basketball");
        wordsList.add("Camera");
        wordsList.add("Chelsea");
        wordsList.add("Freeport");
        wordsList.add("Queens");
        wordsList.add("Nordstrom");
        wordsList.add("Playstation");

        guessedLetters = new HashSet<>();



    }

    public void startGame(){
        //resets letter bank and guesses count
        clearGame();

        String randomWord = getRandomWord();

        //System.out.println(randomWord);
        StringBuilder hiddenWord = displayGameWord(randomWord);
        System.out.println("\n"+ hiddenWord + "\n");

        while (incorrectGuesses < maxGuesses ){

            //handles case insensitivity for user input
            char guess = getUserGuess();

            if (guessedLetters.contains(guess)){
                System.out.println("You already guessed " + guess + ". Try a different letter");
            }
            else {
                guessedLetters.add(guess);
                boolean guessedCorrectly = updateHiddenWord(randomWord,hiddenWord,guess);
                System.out.println(hiddenWord.toString().toUpperCase());
                System.out.println("Letter Bank: " + guessedLetters);

                //Wrong entry condition
                if (!guessedCorrectly) {
                    incorrectGuesses++;
                    //Lose Condition
                    if (incorrectGuesses == maxGuesses){
                        System.out.println("All out of Guesses! The word was: " + randomWord.toUpperCase());
                        break;
                    }
                    System.out.println("Incorrect!");
                    System.out.println("You have " + (maxGuesses - incorrectGuesses) + " guesses left!");
                }
            }
            //converts stringbuilder to string for comparison
            String updatedHiddenWord = hiddenWord.toString();

            //Win condition
            if((updatedHiddenWord).equalsIgnoreCase((randomWord))){
                System.out.println("You win! You guessed the word: " + randomWord.toUpperCase() + "\". Thank you for playing");
                break;
            }
        }
        handleGameRestart();
    }


    /*
    Empties letter bank, resets incorrect guesses to 0
     */
    private void clearGame(){
        guessedLetters.clear();
        incorrectGuesses = 0;
    }

    /*
    Generates a random word from the list of words created and returns it
     */
    private String getRandomWord(){
        Random rand = new Random();

        String randomWord = wordsList.get(rand.nextInt(wordsList.size()));

        return randomWord.toLowerCase();
    }


    private boolean updateHiddenWord(String word, StringBuilder hiddenWord, char guess){
        boolean guessedCorrectly = false;
        for (int i = 0; i < word.length(); i++) {

            if (word.charAt(i) == guess) {
                hiddenWord.setCharAt(i, guess);
                guessedCorrectly = true;
            }
        }
        return guessedCorrectly;
    }
    /*
    takes user's guessed letter and returns it to lowercase
     */
    private char getUserGuess() {
        System.out.println("Guess a letter from A - Z: ");
        return Character.toLowerCase(input.next().charAt(0));
    }
    /*
    takes randomly generated word from list, hides it with underscores, displays it to the screen
     */
    private StringBuilder displayGameWord(String word){
        StringBuilder hiddenWord = new StringBuilder();
        //creates word hidden by underscores to be unlocked
        hiddenWord.append("_".repeat(word.length()));

        //ensures that hidden word is the same size as the random word chosen for the game
        if (hiddenWord.length() != word.length()){
            System.out.println("Application Error");
        }
        return hiddenWord;
    }

    private void handleGameRestart(){
        //Restart Game Option
        System.out.println("Press 1 to Restart. Press 0 to Return to Main Menu");

        int choice = input.nextInt();

        switch (choice){
            case 1:
                startGame();
                break;
            case 0:
                System.out.println("Returning to Main Menu...");
                break;
            default:
                System.out.println("Invalid Choice. Returning to Main Menu");
                break;

        }
    }
    /*public String formatHiddenWord(String hiddenWord){
        StringBuilder formatted = new StringBuilder();
        for(int i = 0; i < hiddenWord.length(); i++){
            formatted.append(hiddenWord.charAt(i)).append(" ");
        }
        return formatted.toString();
    } */


}

