package src;

import java.util.HashMap;

public class GuessHandler {
    private GuessHandler() {
    }

    final static private String YELLOW = "\u001B[33m";
    final static private String GREEN = "\u001B[32m";
    final static private String RESET = "\u001B[0m";

    public static void evaluate(Word guess, Word secretWord) {
        char currentLetter;
        char actualLetter;
        HashMap<Character, Integer> frequencyTable = secretWord.getFrequencyTable();
        int letterFrequency;

        // INCOMING: Insane messy nested if statements
        for (int i = 0; i < guess.length(); i++) {
            currentLetter = guess.charAt(i);
            actualLetter = secretWord.charAt(i);
            letterFrequency = frequencyTable.getOrDefault(currentLetter, 0);

            if (secretWord.contains(currentLetter)) {
                // Letter is in word

                if (actualLetter == currentLetter) {
                    // Letter is in word and in correct position
                    System.out.print(GREEN + currentLetter + RESET);
                    frequencyTable.put(currentLetter, letterFrequency - 1);

                } else {
                    if (letterFrequency >= 0) {
                        // Letter is in word, but incorrect position
                        // Duplicate letters will be handled another time
                        System.out.print(YELLOW + currentLetter + RESET);
                        frequencyTable.put(currentLetter, letterFrequency - 1);

                    } else {
                        //
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
