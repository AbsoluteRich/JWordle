import javax.naming.OperationNotSupportedException;
import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Word {
    final public ArrayList<Character> wordArray;
    public HashMap<Character, Integer> frequencyTable = new HashMap<>();

    public Word(String word) {
        this.wordArray = Helper.convertToArrayList(word.toCharArray());
        this.generateFrequencyTable();
    }

    public Word(char[] word) {
        this.wordArray = Helper.convertToArrayList(word);
        this.generateFrequencyTable();
    }

    public static boolean compare(Word wordOne, Word wordTwo) {
        return wordOne.wordArray.equals(wordTwo.wordArray);
    }

    private void generateFrequencyTable() {
        for (Character character : this.wordArray) {
            this.frequencyTable.put(character, Helper.getCharacterFrequency(this.wordArray, character));
        }
    }

    public static class Source {
        private Source() throws OperationNotSupportedException {
            throw new OperationNotSupportedException("This is a class used to group methods, do not instantiate");
        }

        public static Word wordFromInput(Scanner userInput) {
            Console console = System.console();
            if (console == null) {
                return new Word(userInput.nextLine());
            } else {
                return new Word(console.readPassword());
            }
        }

        public static Word randomWord() {
            throw new UnsupportedOperationException("Function WIP");
        }

        public static Word currentWordle() {
            throw new UnsupportedOperationException("Function WIP");
        }
    }
}
