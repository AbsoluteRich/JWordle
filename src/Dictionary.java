package src;

import java.io.Console;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Dictionary {
    public List<String> wordList;

    public Dictionary() {
        try {
            Path listPath = Path.of("valid-wordle-words.txt");
            wordList = Files.readAllLines(listPath);
        } catch (IOException error) {
            throw new RuntimeException("An error occurred while reading the Wordle dictionary.\n" + error);
        }
    }

    public Word wordFromInput(Scanner userInput) {
        Console console = System.console();
        if (console == null) {
            return new Word(userInput.nextLine());
        } else {
            return new Word(console.readPassword());
        }
    }

    public Word randomWord() {
        int randomLine;
        final Random rng = new Random();

        randomLine = rng.nextInt(wordList.size());
        return new Word(wordList.get(randomLine));
    }

    public Word currentWordle() {
        // https://www.baeldung.com/java-9-http-client
        HttpClient requester = HttpClient.newHttpClient();
        URI wordleApiPath;
        final LocalDate currentDate = LocalDate.now();
        String[] splicedResponse;

        try {
            wordleApiPath = new URI("https://www.nytimes.com/svc/wordle/v2/" + currentDate + ".json");
        } catch (URISyntaxException error) {
            throw new RuntimeException("An error occurred while loading the Wordle API.\n" + error);
        }

        HttpRequest request = HttpRequest.newBuilder(wordleApiPath).GET().build();

        try {
            HttpResponse<String> response = requester.send(request, HttpResponse.BodyHandlers.ofString());

            // I reaaaaaally don't want to add external dependencies in the form of a JSON library, so this is what I'm forced to do
            splicedResponse = response.body().split("solution");
            splicedResponse[1] = splicedResponse[1].substring(3);
            splicedResponse = splicedResponse[1].split("\"");

            return new Word(splicedResponse[0]);

        } catch (IOException error) {
            throw new RuntimeException("An error occurred while fetching the current Wordle.\n" + error);
        } catch (InterruptedException error) {
            throw new RuntimeException("The connection was interrupted while fetching the current Wordle.\n" + error);
        }
    }
}
