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


    public static Result evaluate(int wordIndex, Word guess, Word secretWord) {
        char currentLetter = guess.charAt(wordIndex);
        char actualLetter = secretWord.charAt(wordIndex);
        HashMap<Character, Integer> frequencyTable = secretWord.getFrequencyTable();
        int letterFrequency = frequencyTable.getOrDefault(currentLetter, 0);

        if (secretWord.contains(currentLetter)) {
            // Letter is in word

            if (actualLetter == currentLetter) {
                // Letter is in word and in correct position
                frequencyTable.put(currentLetter, letterFrequency - 1);
                return Result.CORRECT;
            } else {
                if (letterFrequency >= 0) {
                    // Letter is in word, but incorrect position
                    // Duplicate letters will be handled another time
                    frequencyTable.put(currentLetter, letterFrequency - 1);
                    return Result.MISPLACED;
                } else {
                    //
                    return Result.WRONG;
                }
            }
        } else {
            // Letter is NOT in word
            return Result.WRONG;
        }
    }
}
