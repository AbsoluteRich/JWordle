import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.OperationNotSupportedException;

public class GuessHandler {
    private GuessHandler() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This is a class used to group methods, do not instantiate");
    }

    public static void evaluate(ArrayList<Character> guess, ArrayList<Character> secretWord,
            HashMap<Character, Integer> frequencyTable) {
        char currentLetter;
        char actualLetter;
        int letterFrequency;

        // INCOMING: Insane messy nested if statements
        for (int i = 0; i < guess.size(); i++) {
            currentLetter = guess.get(i);
            actualLetter = secretWord.get(i);
            letterFrequency = frequencyTable.getOrDefault(currentLetter, 0);

            if (secretWord.contains(currentLetter)) {
                // Letter is in word
                if (actualLetter == currentLetter) {
                    // Letter is in word and in correct position
                    System.out.print(currentLetter);
                    frequencyTable.put(currentLetter, letterFrequency - 1);

                } else {
                    // Letter is in word, but INCORRECT position

                    if (letterFrequency > 0) {
                        // Incorrect position, but letter is duplicated
                        // These are reported until there are none left in the guess word
                        System.out.print("(" + currentLetter + ")");
                        frequencyTable.put(currentLetter, letterFrequency - 1);
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
    }
}
