package pl.robertsikora.simplesearch.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static pl.robertsikora.simplesearch.engine.SimpleSearchEngine.createSimpleSearchEngine;

import java.io.File;
import org.junit.jupiter.api.Test;
import pl.robertsikora.simplesearch.engine.ranking.SourceRank;

class SimpleSearchEngineTest {

    private static final String DIRECTORY_PATH = "src/test/resources";

    private final SimpleSearchEngine underTest = createSimpleSearchEngine();

    @Test
    void shouldThrowExceptionWhenGivenDirectoryDoesNotExists() {
        //when
        var error = catchThrowable(() -> underTest.indexDirectory(new File("non_existing_directory")));
        //then
        assertThat(error)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Given directory: non_existing_directory does not exist!");
    }

    @Test
    void shouldSearchTextForGivenQueryAndReturnListOfRankScoreAgainstEachMatch() {
        //given
        underTest.indexDirectory(new File(DIRECTORY_PATH));
        //when
        var result = underTest.searchByQuery(new SearchQuery("to be or not to be"));
        //then
        assertThat(result.getRanks())
            .hasSize(5)
            .containsExactlyInAnyOrder(

                new SourceRank("file1.txt", 1.0d),
                new SourceRank("file2.txt", 0.25d),
                new SourceRank("file3.txt", 0.5d),
                new SourceRank("file4.txt", 0.0d),
                new SourceRank("file5.txt", 0.75d)

            );
    }

    @Test
    void shouldSearchTextForGivenQueryAndReturnLimitResultsTo3Items() {
        //given
        underTest.indexDirectory(new File(DIRECTORY_PATH));
        //when
        var result = underTest.searchByQuery(new SearchQuery("to be or not to be"))
            .limit(3);

        //then
        assertThat(result.getRanks())
            .hasSize(3);
    }

    @Test
    void shouldSearchTextForGivenQueryAndReturnSortedDescendingByRank() {
        //given
        underTest.indexDirectory(new File(DIRECTORY_PATH));
        //when
        var result = underTest.searchByQuery(new SearchQuery("to be or not to be"))
            .sortDesc();

        //then
        assertThat(result.getRanks())
            .containsExactly(

                new SourceRank("file1.txt", 1.0d),
                new SourceRank("file5.txt", 0.75d),
                new SourceRank("file3.txt", 0.5d),
                new SourceRank("file2.txt", 0.25d),
                new SourceRank("file4.txt", 0.0d)

            );
    }
}