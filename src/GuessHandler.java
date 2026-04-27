package src;

import java.util.HashMap;

public class GuessHandler {
    private GuessHandler() {
    }

    public enum Result {
        CORRECT,
        MISPLACED,
        WRONG
    }

    public static Result[] evaluateWord(Word guess, Word secretWord) {
        Result[] results = new Result[5];
        char[] guessCharacters = guess.toCharArray();
        HashMap<Character, Integer> frequencyTable = secretWord.getFrequencyTable();
        char currentLetter;
        char actualLetter;
        int letterFrequency;

        // Uses two passes
        // The first identifies correct letters, removing them from the selection of incorrect letters

        for (int i = 0; i < guessCharacters.length; i++) {
            currentLetter = guess.charAt(i);
            actualLetter = secretWord.charAt(i);
            letterFrequency = frequencyTable.getOrDefault(currentLetter, 0);

            if (secretWord.contains(currentLetter) && actualLetter == currentLetter) {
                frequencyTable.put(currentLetter, letterFrequency - 1);
                results[i] = Result.CORRECT;
            }
        }

        // The second identifies incorrect letters, respecting duplicated letters in incorrect positions
        for (int i = 0; i < guessCharacters.length; i++) {
            if (results[i] == Result.CORRECT) {
                continue;
            }

            // Because the above if-statement filters out the correct letters, we don't need to check again
            currentLetter = guess.charAt(i);
            letterFrequency = frequencyTable.getOrDefault(currentLetter, -1);

            if (letterFrequency > 0) {
                frequencyTable.put(currentLetter, letterFrequency - 1);
                results[i] = Result.MISPLACED;
            } else {
                results[i] = Result.WRONG;
            }
        }

        return results;
    }
}
