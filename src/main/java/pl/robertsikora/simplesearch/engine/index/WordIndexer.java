package pl.robertsikora.simplesearch.engine.index;

import java.util.Map;
import java.util.Set;

public interface WordIndexer {

    void indexWord(String source, String word);

    Set<String> getIndexedSources();

    Map<String, Integer> getIndexedSourcesByWord(String word);

    static WordIndexer simpleIndexer() {
        return new InMemoryWordIndexer();
    }
}
