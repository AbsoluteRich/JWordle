import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    public void begin() {
        System.out.println("Welcome to Wordle! How would you like your words today?");

        try (Scanner userInput = new Scanner(System.in)) {
            ArrayList<Character> secret = getWordFromUser(userInput);
            startGame(secret, userInput);
        }
    }

    private ArrayList<Character> getWordFromUser(Scanner userInput) {
        String preferredSource;

        do {
            preferredSource = userInput.nextLine();

            // Enhanced switch (or rule-switch): Instead of using repetitive colons and
            // break statements, an arrow can be used, and whatever is in the braces will
            // automatically break upon completion
            // Compare to an enhanced for (or for-each)
            // Just like standard for loops, standard switches have their own use cases
            switch (preferredSource) {
                case "input" -> {
                    System.out.println("Please enter your secret word:");
                    return WordSource.wordFromInput();
                }

                case "random" -> {
                    return WordSource.randomWord();
                }

                case "current" -> {
                    return WordSource.currentWordle();
                }
            }
        } while (true);
    }

    private void startGame(ArrayList<Character> secretWord, Scanner userInput) {
        int attempts = 6;
        HashMap<Character, Integer> frequencyTable = new HashMap<>();
        ArrayList<Character> guess;

        for (Character character : secretWord) {
            frequencyTable.put(character, Helper.getCharacterFrequency(secretWord, character));
        }

        System.out.printf("Please enter your first attempt. (Word has %d characters)\n", secretWord.size());

        while (attempts > 0) {
            guess = Helper.convertToArrayList(userInput.nextLine().toCharArray());

            if (secretWord.equals(guess)) {
                System.out.println("Congratulations!");
                break;
            }

            if (guess.size() != secretWord.size()) {
                // If the sizes aren't correct, this would result in an index out of bounds
                // error
                System.out.printf("Not allowed! (Word has %d characters)\n", secretWord.size());
            } else {
                // This is where the meat of the program lies, proceed to the source file for this class to learn more
                GuessHandler.evaluate(guess, secretWord, frequencyTable);
                System.out.println();
            }

            attempts--;
            System.out.printf("\nAttempts remaining: %d/6\n", attempts);
        }
    }
}
