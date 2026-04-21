import java.util.HashMap;

public class Word {
    final private String word;
    final private HashMap<Character, Integer> frequencyTable = new HashMap<>();

    public Word(String newWord) {
        this.word = newWord;
        this.generateFrequencyTable();
    }

    public Word(char[] newWord) {
        this.word = String.valueOf(newWord);
        this.generateFrequencyTable();
    }

    public static boolean equals(Word wordOne, Word wordTwo) {
        // Tank u John Programming
        return wordOne.getWord().equals(wordTwo.getWord());
    }

    private void generateFrequencyTable() {
        // Jury-rigged from a school project
        char[] characterList = this.word.toCharArray();

        for (char character : characterList) {
            this.frequencyTable.put(character, frequencyTable.getOrDefault(character, 0) + 1);
        }
    }

    public String getWord() {
        return this.word;
    }

    public HashMap<Character, Integer> getFrequencyTable() {
        return this.frequencyTable;
    }

    public int length() {
        return this.word.length();
    }

    public char charAt(int index) {
        return this.word.charAt(index);
    }

    public boolean contains(char characterToFind) {
        return this.word.contains(String.valueOf(characterToFind));
    }
}
