import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

public class Wordle {
    public static void main(String[] args) {
        Console console = System.console();

        int attempts = 6;
        ArrayList<Character> guess;

        System.out.println("Please enter your secret word:");
        char[] inputSecret = console.readPassword();
        ArrayList<Character> secretWord = Helper.convertToArrayList(inputSecret);

        System.out.printf("Please enter your first attempt. (Word has %d characters)\n", secretWord.size());

        try (Scanner userInput = new Scanner(System.in)) {
            while (attempts > 0) {
                guess = Helper.convertToArrayList(userInput.nextLine().toCharArray());

                if (secretWord.equals(guess)) {
                    System.out.println("\nCongratulations!");
                    break;
                }

                if (guess.size() != secretWord.size()) {
                    // If the sizes aren't correct, this would result in an index out of bounds
                    // error
                    System.out.println("Not allowed!");
                } else {
                    // INCOMING: Insane messy nested if statements
                    for (int i = 0; i < guess.size(); i++) {
                        if (secretWord.contains(guess.get(i))) {
                            // Letter is in word
                            if (secretWord.get(i).equals(guess.get(i))) {
                                // Letter is in word and in correct position
                                System.out.print(guess.get(i));
                            } else {
                                // Letter is in word but not correct position
                                System.out.print("(" + guess.get(i) + ")");
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
