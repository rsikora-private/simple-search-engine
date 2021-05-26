package pl.robertsikora.simplesearch.engine.ranking;

import java.util.Set;
import pl.robertsikora.simplesearch.engine.index.WordIndexer;

public interface RankingCalculator {

    SourceRank getRank(String source, Set<String> searchWords);

    static RankingCalculator simpleRankingCalculator(final WordIndexer wordIndexer) {
        return new ExactMatchWordOccurrenceRankingCalculator(wordIndexer);
    }
}
