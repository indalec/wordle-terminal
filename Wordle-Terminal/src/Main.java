import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("WORDLE!");

        DictionaryWordle dictionaryWordle = new DictionaryWordle();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Wordle!");

        int tries = 6;
        int wordLength = 5;

        String secretWord = DictionaryWordle.getRandomWord();
        char[] secretWordArray = secretWord.toUpperCase().toCharArray();

        // Colors
        String backgroundGreen = "\u001b[42m";
        String backgroundYellow = "\u001b[43m";
        String resetColor = "\u001b[0m";

        boolean isValidWord = false;

        // Loop for six guesses
        String guess = null;
        for (tries = 6; tries > 0; tries--) {

            System.out.println("You have " + tries + " tries left.");
            System.out.println("Please guess the word: ");
            guess = scanner.nextLine().toUpperCase();

            // Test if the word has 5 letters and exists in the dictionary
            while (!isValidWord) {
                if (guess.length() != wordLength) {
                    System.out.println("Please enter a " + wordLength + " letter word");
                } else if (!DictionaryWordle.isValid(guess.toLowerCase())) {
                    System.out.println("Please enter a valid word");
                } else {
                    isValidWord = true;
                }

                if (!isValidWord) {
                    guess = scanner.nextLine().toUpperCase();
                }
            }

            isValidWord = false;
            char[] guessArray = guess.toCharArray();

            // Loop to iterate through each letter
            for (int i = 0; i < wordLength; i++) {
                boolean isLetterInWord = false;
                for (char letter : secretWordArray) {
                    if (guessArray[i] == letter) {
                        isLetterInWord = true;
                        break;
                    }
                }

                if (guessArray[i] == secretWordArray[i]) {
                    // Letter matches
                    System.out.print(backgroundGreen + guessArray[i] + resetColor);
                } else if (isLetterInWord) {
                    // Letter is in word, but different location
                    System.out.print(backgroundYellow + guessArray[i] + resetColor);
                } else {
                    // Letter is not in word
                    System.out.print(guessArray[i]);
                }
            }
            System.out.println();

            if (guess.equals(secretWord.toUpperCase())) {
                System.out.println("Correct! You win!");
                break;
            }
        }

        if (!guess.equals(secretWord.toUpperCase())) {
            System.out.println("No more tries. You lose!");
        }

        scanner.close();
    }
}
