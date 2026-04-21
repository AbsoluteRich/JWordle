import java.util.HashMap;

public class GuessHandler {
    private GuessHandler() {
    }

    public static void evaluate(Word guess, Word secretWord) {
        char currentLetter;
        char actualLetter;
        int letterFrequency;
        HashMap<Character, Integer> workingTable = new HashMap<>(secretWord.frequencyTable);

        // INCOMING: Insane messy nested if statements
        for (int i = 0; i < guess.wordArray.size(); i++) {
            currentLetter = guess.wordArray.get(i);
            actualLetter = secretWord.wordArray.get(i);
            letterFrequency = workingTable.getOrDefault(currentLetter, 0);

            if (secretWord.wordArray.contains(currentLetter)) {
                // Letter is in word
                if (actualLetter == currentLetter) {
                    // Letter is in word and in correct position
                    System.out.print(currentLetter);
                    workingTable.put(currentLetter, letterFrequency - 1);

                } else {
                    // Letter is in word, but INCORRECT position

                    if (letterFrequency > 0) {
                        // Incorrect position, but letter is duplicated
                        // These are reported until there are none left in the guess word
                        System.out.print("(" + currentLetter + ")");
                        workingTable.put(currentLetter, letterFrequency - 1);
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
