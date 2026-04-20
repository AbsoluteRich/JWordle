import java.util.ArrayList;
import java.util.HashMap;

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
}
