// Scanner, substring, loops ANSI color codes

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("WORDLE!");

        DictionaryWordle dictionaryWordle = new DictionaryWordle();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Wordle!");
        int tries = 0;
        int maxTries = 6;
        String secretWord = DictionaryWordle.getRandomWord();

        //Colors
        String backgroundGreen = "\u001b[42m";
        String backgroundYellow = "\u001b[43m";
        String resetColor = "\u001b[0m";

//FOR DEVELOPEMENT:
        //    String secretWord1 = "shake";
        String correct = secretWord.toUpperCase();
//        String guessx = "easte";


        //Loop for six guesses
        String guess = null;
        for (int j = 0; j < 6; j++) {

            System.out.println("Please guess the word: ");
            guess = scanner.nextLine().toUpperCase();

            //Test if the word has 5 letters existing
            while (!(guess.length() == 5 &&  DictionaryWordle.isValid(guess))) {
                if (!(guess.length() == 5)) {

                    System.out.println("Please enter a 5 letter word");
                    guess = scanner.nextLine().toUpperCase();

                }
                else if (!DictionaryWordle.isValid(guess)) {
                    System.out.println("Please enter a valid word");
                    guess = scanner.nextLine().toUpperCase();
                }


            }

            //Test if the word exists in the dictionary:


            //Loop to iterate trough each letter

            for (int i = 0; i < 5; i++) {

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
            System.out.println("Correct! You win!");
        }
    }
}