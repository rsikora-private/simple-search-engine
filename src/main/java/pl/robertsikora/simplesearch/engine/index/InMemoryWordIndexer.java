package pl.robertsikora.simplesearch.engine.index;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Simple indexer in memory. indexedWords - a map for each word stores source and number of occurrences in that source
 * Note: Current implementation is not thread-safety
 */

final class InMemoryWordIndexer implements WordIndexer {

    private final Map<String, Map<String, Integer>> indexedWords = new HashMap<>();
    private final Set<String> indexedSources = new HashSet<>();

    public void indexWord(final String source, final String word) {
        indexedWords.computeIfAbsent(word, key -> new HashMap<>());
        indexedWords.get(word).compute(source, (key, value) -> value == null ? 1 : value + 1);
        indexedSources.add(source);
    }

    @Override
    public Set<String> getIndexedSources() {
        return Set.copyOf(indexedSources);
    }

    @Override
    public Map<String, Integer> getIndexedSourcesByWord(String word) {
        return Map.copyOf(indexedWords.getOrDefault(word, Map.of()));
    }
}
