import java.util.Scanner;

public class Game {
    public void begin() {
        System.out.println("Welcome to Wordle! How would you like your words today?");

        try (Scanner userInput = new Scanner(System.in)) {
            Word secret = getWordFromUser(userInput);
            startGame(secret, userInput);
        }
    }

    private Word getWordFromUser(Scanner userInput) {
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
                    return WordSource.wordFromInput(userInput);
                }

                case "random" -> {
                    return WordSource.randomWord();
                }

                case "current" -> {
                    return WordSource.currentWordle();
                }

                default -> System.out.println("Invalid input! Possible choices are 'input', 'random' or 'current'");
            }
        } while (true);
    }

    private void startGame(Word secretWord, Scanner userInput) {
        int attempts = 5;
        Word guess;

        System.out.printf("Please enter your first attempt. (Word has %d characters)\n", secretWord.length());

        while (attempts > 0) {
            guess = new Word(userInput.nextLine());

            if (Word.equals(secretWord, guess)) {
                System.out.println("Congratulations!");
                break;
            }

            if (guess.length() != secretWord.length()) {
                // If the sizes aren't correct, this would result in an index out of bounds
                // error
                System.out.printf("Not allowed! (Word has %d characters)\n", secretWord.length());
            } else {
                // This is where the meat of the program lies, proceed to the source file for this class to learn more
                GuessHandler.evaluate(guess, secretWord);
                System.out.println();
            }

            attempts--;
            System.out.printf("\nAttempts remaining: %d/6\n", attempts);
        }

        if (attempts <= 0) {
            System.out.printf("You lose! The word was %s", secretWord.getWord());
        }
    }
}
