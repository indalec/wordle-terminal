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

        String correct = "shake"
        String guess = "paste";

    }
}