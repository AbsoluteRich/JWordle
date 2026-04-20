import java.util.ArrayList;

public class Helper {
    public static ArrayList<Character> convertToArrayList(char[] primitiveArray) {
        ArrayList<Character> newArray = new ArrayList<>();

        for (char character : primitiveArray) {
            newArray.add(character);
        }

        return newArray;
    }

    // Currently, unused
    // public void printIterable(Iterable<?> array) {
    //     for (Object element : array) {
    //         System.out.print(element);
    //     }
    // }

    // Jury-rigged from a school project
    public static int getCharacterFrequency(ArrayList<Character> characterList, Character characterToFind) {
        int frequency = 0;

        for (Character character : characterList) {
            // .toLowerCase() actually converts these into primitive char, so == is used instead of .equals()
            if (Character.toLowerCase(character) == (Character.toLowerCase(characterToFind))) {
                frequency++;
            }
        }

        return frequency;
    }
}
