package pl.robertsikora.simplesearch;

import static pl.robertsikora.simplesearch.engine.SimpleSearchEngine.createSimpleSearchEngine;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import pl.robertsikora.simplesearch.engine.SearchQuery;

public class ConsoleApp {

    private static final String SEARCH_PROMPT = "search>";
    private static final String QUIT_COMMAND = ":quit";
    private static final int RESULTS_LIMIT = 10;

    public static void main(String... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No directory given to index.");
        }

        doConsoleSearch(args[0], System.in, System.out);
    }

    static void doConsoleSearch(final String directory, final InputStream in, final PrintStream out) {

        final var simpleSearchEngine = createSimpleSearchEngine();

        simpleSearchEngine.indexDirectory(new File(directory));

        final var keyboard = new Scanner(in, StandardCharsets.UTF_8);

        while (true) {
            out.print(SEARCH_PROMPT + " ");
            final var searchText = keyboard.nextLine();
            if (QUIT_COMMAND.equals(searchText)) {
                break;
            }

            final var searchResult = simpleSearchEngine.searchByQuery(new SearchQuery(searchText));

            searchResult
                .sortDesc()
                .limit(RESULTS_LIMIT)
                .prettyPrint(out);
        }
    }

}
