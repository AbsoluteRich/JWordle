import javax.naming.OperationNotSupportedException;
import java.io.Console;
import java.util.Scanner;

public class WordSource {
    private WordSource() throws OperationNotSupportedException {
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
