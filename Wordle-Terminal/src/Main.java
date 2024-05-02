import java.util.Scanner;

public class Main {
    private static int maxAttempts = 6;
    private static int wordLength = 5;
    private static String[][] grid = new String[maxAttempts][wordLength];

    public static void main(String[] args) {
        // Colors
        String backgroundGreen = "\u001b[42m";
        String backgroundYellow = "\u001b[43m";
        String resetColor = "\u001b[0m";



        System.out.println("WORDLE!");

        initializeGrid();

     //   DictionaryWordle dictionaryWordle = new DictionaryWordle();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Wordle!");

        String secretWord = DictionaryWordle.getRandomWord();
        char[] secretWordArray = secretWord.toUpperCase().toCharArray();



        boolean isValidWord = false;

        // Loop for six guesses
        String guess = null;
        for (int tries = maxAttempts; tries > 0; tries--) {

            System.out.println("You have " + tries + " tries left.");

            printGrid();

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


            updateGrid(maxAttempts - tries, guess, secretWordArray, backgroundGreen, backgroundYellow, resetColor);

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

    private static void initializeGrid() {
        for (int i = 0; i < maxAttempts; i++) {
            for (int j = 0; j < wordLength; j++) {
                grid[i][j] = ".";
            }
        }
    }

    private static void printGrid() {
        for (int i = 0; i < maxAttempts; i++) {
            for (int j = 0; j < wordLength; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }

    private static void updateGrid(int attempt, String guess, char[] secretWordArray, String bgGreen, String bgYellow, String reset) {
        char[] guessArray = guess.toCharArray();
        for (int i = 0; i < wordLength; i++) {
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
