import java.io.Console;
import java.util.ArrayList;

public class WordSource {
    public static ArrayList<Character> wordFromInput() {
        Console console = System.console();
        return Helper.convertToArrayList(console.readPassword());
    }

    public static void randomWord() {
        throw new UnsupportedOperationException("Function WIP");
    }

    public static void currentWordle() {
        throw new UnsupportedOperationException("Function WIP");
    }
}
