package src;

import java.util.Scanner;
import java.util.TreeSet;

public class Game {
    private boolean validateInput = true;
    private final Dictionary dictionary = new Dictionary();
    final static private String YELLOW = "\u001B[33m";
    final static private String GREEN = "\u001B[32m";
    final static private String RESET = "\u001B[0m";

    public void begin() {
        System.out.println("Welcome to Wordle! How would you like your words today?");
        System.out.println("Would you like to 'input' your own word, choose a 'random' word, or take on the 'current' Wordle?");

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
                    validateInput = false;
                    return dictionary.wordFromInput(userInput);
                }

                case "random" -> {
                    return dictionary.randomWord();
                }

                case "current" -> {
                    return dictionary.currentWordle();
                }

                default -> System.out.println("Invalid input! Possible choices are 'input', 'random' or 'current'");
            }
        } while (true);
    }

    private void startGame(Word secretWord, Scanner userInput) {
        int attempts = 6;
        final int maxAttempts = attempts;
        Word guess;
        GuessHandler.Result[] results;
        GuessHandler.Result result;
        char currentLetter;
        TreeSet<Character> usedLetters = new TreeSet<>();
        String compiledLetters;

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

                if (validateInput && !dictionary.wordList.contains(guess.getWord())) {
                    // Words are not validated if they are given by user input, since they could be anything
                    System.out.println("Not allowed! (Word is not in dictionary)");
                } else {
                    results = GuessHandler.evaluateWord(guess, secretWord);

                    for (int i = 0; i < guess.length(); i++) {
                        currentLetter = guess.charAt(i);

                        usedLetters.add(currentLetter);
                        result = results[i];

                        switch (result) {
                            case WRONG -> System.out.print("X");
                            case CORRECT -> System.out.print(GREEN + currentLetter + RESET);
                            case MISPLACED -> System.out.print(YELLOW + currentLetter + RESET);
                        }
                    }
                }
            }

            attempts--;
            compiledLetters = usedLetters.toString();
            System.out.printf("\nAttempts remaining: %d/%d\n", attempts, maxAttempts);
            System.out.printf("Used letters: %s\n", compiledLetters.substring(1, compiledLetters.length() - 1));
        }

        if (attempts <= 0) {
            System.out.printf("You lose! The word was %s", secretWord.getWord());
        }
    }
}
