package pl.robertsikora.simplesearch;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.robertsikora.simplesearch.ConsoleApp.doConsoleSearch;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class ConsoleAppE2ETest {

    private static final String DIRECTORY_PATH = "src/test/resources";

    @Test
    void shouldSearchTextInGivenDirectoryAndPrintPrettyResults() {
        //given
        var in = new ByteArrayInputStream("to be or not to be\n:quit".getBytes());
        var out = new ByteArrayOutputStream();
        //when
        doConsoleSearch(DIRECTORY_PATH, in, new PrintStream(out));
        //then
        assertThat(out).hasToString(

            "search> file1.txt :  100%\n"
                + "file5.txt :   75%\n"
                + "file3.txt :   50%\n"
                + "file2.txt :   25%\n"
                + "file4.txt :    0%\n"
                + "search> "

        );
    }

    @Test
    void shouldPrintNoMatchesFoundWhenThereIsNoMatches() {
        //given
        var in = new ByteArrayInputStream("xxx\n:quit".getBytes());
        var out = new ByteArrayOutputStream();
        //when
        doConsoleSearch(DIRECTORY_PATH, in, new PrintStream(out));
        //then
        assertThat(out).hasToString(

            "search> no matches found\n"
                + "search> "

        );
    }

}