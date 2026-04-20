import javax.naming.OperationNotSupportedException;

public class GuessHandler {
    private GuessHandler() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This is a class used to group methods, do not instantiate");
    }

    public static void evaluate(Word guess, Word secretWord) {
        char currentLetter;
        char actualLetter;
        int letterFrequency;

        // INCOMING: Insane messy nested if statements
        for (int i = 0; i < guess.wordArray.size(); i++) {
            currentLetter = guess.wordArray.get(i);
            actualLetter = secretWord.wordArray.get(i);
            letterFrequency = secretWord.frequencyTable.getOrDefault(currentLetter, 0);

            if (secretWord.wordArray.contains(currentLetter)) {
                // Letter is in word
                if (actualLetter == currentLetter) {
                    // Letter is in word and in correct position
                    System.out.print(currentLetter);
                    secretWord.frequencyTable.put(currentLetter, letterFrequency - 1);

                } else {
                    // Letter is in word, but INCORRECT position

                    if (letterFrequency > 0) {
                        // Incorrect position, but letter is duplicated
                        // These are reported until there are none left in the guess word
                        System.out.print("(" + currentLetter + ")");
                        secretWord.frequencyTable.put(currentLetter, letterFrequency - 1);
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
