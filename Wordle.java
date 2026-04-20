import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Wordle {
    public static void main(String[] args) {
        Console console = System.console();

        int attempts = 6;
        Character currentLetter;
        Character actualLetter;
        int letterFrequency;

        ArrayList<Character> secretWord;
        HashMap<Character, Integer> secretFrequencyTable = new HashMap<>();

        ArrayList<Character> guess;
        HashMap<Character, Integer> guessFrequencyTable = new HashMap<>();

        System.out.println("Please enter your secret word:");
        secretWord = Helper.convertToArrayList(console.readPassword());
        // secretWord = Helper.convertToArrayList(userInput.nextLine().toCharArray());

        for (Character character : secretWord) {
            secretFrequencyTable.put(character, Helper.getCharacterFrequency(secretWord, character));
        }

        System.out.printf("Please enter your first attempt. (Word has %d characters)\n", secretWord.size());

        try (Scanner userInput = new Scanner(System.in)) {
            while (attempts > 0) {
                guess = Helper.convertToArrayList(userInput.nextLine().toCharArray());

                if (secretWord.equals(guess)) {
                    System.out.println("Congratulations!");
                    break;
                }

                for (Character character : guess) {
                    guessFrequencyTable.put(character, Helper.getCharacterFrequency(guess, character));
                }

                if (guess.size() != secretWord.size()) {
                    // If the sizes aren't correct, this would result in an index out of bounds
                    // error
                    System.out.printf("Not allowed! (Word has %d characters)", secretWord.size());
                } else {
                    // INCOMING: Insane messy nested if statements
                    for (int i = 0; i < guess.size(); i++) {
                        currentLetter = guess.get(i);
                        actualLetter = secretWord.get(i);
                        letterFrequency = secretFrequencyTable.get(currentLetter);

                        if (secretWord.contains(currentLetter)) {
                            // Letter is in word
                            if (actualLetter.equals(currentLetter)) {
                                // Letter is in word and in correct position
                                System.out.print(currentLetter);
                                secretFrequencyTable.put(currentLetter, letterFrequency - 1);

                            } else {
                                // Letter is in word, but INCORRECT position

                                if (letterFrequency > 0) {
                                    // Incorrect position, but letter is duplicated
                                    // These are reported until there are none left in the guess word
                                    System.out.print("(" + currentLetter + ")");
                                    secretFrequencyTable.put(currentLetter, letterFrequency - 1);
                                } else {
                                    // Duplicate character is never used in word
                                    System.out.print("X");
                                }
                            }
                        } else {
                            // Letter is NOT in word
                            System.out.print("X");
                        }
                    }
                    System.out.println();
                }

                attempts--;
                System.out.printf("\nAttempts remaining: %d/6\n", attempts);
            }
        }
    }
}
