// Scanner, substring, loops ANSI color codes

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("WORDLE!");

        DictionaryWordle dictionaryWordle = new DictionaryWordle();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Wordle!");

        int tries = 6;
        int wordLenght = 5;

        String secretWord = DictionaryWordle.getRandomWord();

        //Colors
        String backgroundGreen = "\u001b[42m";
        String backgroundYellow = "\u001b[43m";
        String resetColor = "\u001b[0m";

        //FOR DEVELOPEMENT:
        //    String secretWord1 = "shake";
        //    String guessx = "easte";

        String correct = secretWord.toUpperCase();
        boolean isValidWord = false;


        //Loop for six guesses
        String guess = null;
        for (tries = 6; tries > 0; tries--) {

            System.out.println("You have " + tries + " left.");
            System.out.println("Please guess the word: ");
            guess = scanner.nextLine().toUpperCase();

            //Test if the word has 5 letters and exists in the dictionary
            while (isValidWord == false) {
                guess = guess.toLowerCase();
                if (!(guess.length() == wordLenght)) {

                    System.out.println("Please enter a " + wordLenght + " letter word");
                    guess = scanner.nextLine().toUpperCase();

                } else if (!DictionaryWordle.isValid(guess)) {
                    System.out.println("Please enter a valid word");
                    guess = scanner.nextLine().toUpperCase();
                } else {
                    guess = guess.toUpperCase();
                    isValidWord = true;

                }
            }

            isValidWord = false;

            //Loop to iterate trough each letter

            for (int i = 0; i < wordLenght; i++) {

                if (guess.substring(i, i + 1).equals(correct.substring(i, i + 1))) {

                    //Letter matches

                    System.out.print(backgroundGreen + guess.substring(i, i + 1) + resetColor);

                } else if (correct.indexOf(guess.substring(i, i + 1)) > -1) {

                    //Letter is in word, but different location
                    System.out.print(backgroundYellow + guess.substring(i, i + 1) + resetColor);

                } else {
                    System.out.print(guess.substring(i, i + 1));
                }

            }
            System.out.println();
            if (guess.equals(correct)) {
                System.out.println("Correct! You win!");
                break;
            }
        }
        if (!guess.equals(correct)) {
            System.out.println("No more tries. You loose!");
        }
    }
}