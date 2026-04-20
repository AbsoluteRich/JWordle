import java.io.Console;
import java.util.ArrayList;
import javax.naming.OperationNotSupportedException;

public class WordSource {
    private WordSource() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This is a class used to group methods, do not instantiate");
    }

    public static ArrayList<Character> wordFromInput() {
        Console console = System.console();
        return Helper.convertToArrayList(console.readPassword());
    }

    public static ArrayList<Character> randomWord() {
        throw new UnsupportedOperationException("Function WIP");
    }

    public static ArrayList<Character> currentWordle() {
        throw new UnsupportedOperationException("Function WIP");
    }
}