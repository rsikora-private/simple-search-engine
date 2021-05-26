package pl.robertsikora.simplesearch.engine.index;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InMemoryWordIndexerTest {

    private final InMemoryWordIndexer underTest = new InMemoryWordIndexer();

    @Test
    void shouldProperlyIndexWords() {
        //given
        underTest.indexWord("file1.txt", "to");
        underTest.indexWord("file1.txt", "be");
        underTest.indexWord("file2.txt", "not");
        underTest.indexWord("file2.txt", "or");
        underTest.indexWord("file2.txt", "or");
        //when
        //then
        assertThat(underTest.getIndexedSources())
            .hasSize(2)
            .contains("file1.txt", "file2.txt");

        assertThat(underTest.getIndexedSourcesByWord("to"))
            .containsEntry("file1.txt", 1);
        assertThat(underTest.getIndexedSourcesByWord("be"))
            .containsEntry("file1.txt", 1);
        assertThat(underTest.getIndexedSourcesByWord("not"))
            .containsEntry("file2.txt", 1);
        assertThat(underTest.getIndexedSourcesByWord("or"))
            .containsEntry("file2.txt", 2);
    }
}