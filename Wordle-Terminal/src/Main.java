import java.util.Scanner;

public class Main {
    private static final int MAX_ATTEMPTS = 6;
    private static final int WORD_LENGTH = 5;
    private static String[][] grid = new String[MAX_ATTEMPTS][WORD_LENGTH];

    public static void main(String[] args) {
        System.out.println("WORDLE!");
        initializeGrid();

        DictionaryWordle dictionaryWordle = new DictionaryWordle();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Wordle!");

        String secretWord = DictionaryWordle.getRandomWord();
        char[] secretWordArray = secretWord.toUpperCase().toCharArray();

        // Colors
        String backgroundGreen = "\u001b[42m";
        String backgroundYellow = "\u001b[43m";
        String resetColor = "\u001b[0m";

        boolean isValidWord = false;

        // Loop for six guesses
        String guess = null;
        for (int tries = MAX_ATTEMPTS; tries > 0; tries--) {

            System.out.println("You have " + tries + " tries left.");
            printGrid();
            System.out.println("Please guess the word: ");
            guess = scanner.nextLine().toUpperCase();

            // Test if the word has 5 letters and exists in the dictionary
            while (!isValidWord) {
                if (guess.length() != WORD_LENGTH) {
                    System.out.println("Please enter a " + WORD_LENGTH + " letter word");
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
            updateGrid(MAX_ATTEMPTS - tries, guess, secretWordArray, backgroundGreen, backgroundYellow, resetColor);
            char[] guessArray = guess.toCharArray();

            // Loop to iterate through each letter
            for (int i = 0; i < WORD_LENGTH; i++) {
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

    private static void initializeGrid() {
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            for (int j = 0; j < WORD_LENGTH; j++) {
                grid[i][j] = ".";
            }
        }
    }

    private static void printGrid() {
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            for (int j = 0; j < WORD_LENGTH; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }

    private static void updateGrid(int attempt, String guess, char[] secretWordArray, String bgGreen, String bgYellow, String reset) {
        char[] guessArray = guess.toCharArray();
        for (int i = 0; i < WORD_LENGTH; i++) {
            boolean isLetterInWord = false;
            for (char letter : secretWordArray) {
                if (guessArray[i] == letter) {
                    isLetterInWord = true;
                    break;
                }
            }
            if (guessArray[i] == secretWordArray[i]) {
                grid[attempt][i] = bgGreen + guessArray[i] + reset;
            } else if (isLetterInWord) {
                grid[attempt][i] = bgYellow + guessArray[i] + reset;
            } else {
                grid[attempt][i] = String.valueOf(guessArray[i]);
            }
        }
    }
}
