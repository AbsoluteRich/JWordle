import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class WordSource {
    private static final File wordList = new File("valid-wordle-words.txt");
    private static final Random rng = new Random();

    private WordSource() {
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
        long lineCount;
        long randomLine;
        String word;

        try (Stream<String> file = Files.lines(wordList.toPath())) {
            // https://stackoverflow.com/a/35523560
            lineCount = file.count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        randomLine = rng.nextLong(lineCount);

        try (Stream<String> file = Files.lines(wordList.toPath())) {
            word = file.skip(randomLine).findFirst().orElse(null);

            if (word == null) {
                throw new RuntimeException("Randomly found word is empty");
            }

            return new Word(word);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Word currentWordle() {
        throw new UnsupportedOperationException("Function WIP");
    }
}
