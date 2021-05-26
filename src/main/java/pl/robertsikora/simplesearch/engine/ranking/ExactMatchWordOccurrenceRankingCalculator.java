package pl.robertsikora.simplesearch.engine.ranking;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import pl.robertsikora.simplesearch.engine.index.WordIndexer;

final class ExactMatchWordOccurrenceRankingCalculator implements RankingCalculator {

    private final WordIndexer wordIndexer;

    ExactMatchWordOccurrenceRankingCalculator(final WordIndexer wordIndexer) {
        this.wordIndexer = wordIndexer;
    }


    @Override
    public SourceRank getRank(final String source, Set<String> searchWords) {
        final var wordsOccurrencePerSource = searchWords
            .stream()
            .flatMap(searchWord -> wordIndexer.getIndexedSourcesByWord(searchWord).keySet().stream())
            .filter(key -> key.equals(source)).count();

        return new SourceRank(source, calculateRank(wordsOccurrencePerSource, searchWords.size()));
    }

    private double calculateRank(final long wordOccurrence, final int searchWordsSize) {
        return wordOccurrence / (double) searchWordsSize;
    }

}
