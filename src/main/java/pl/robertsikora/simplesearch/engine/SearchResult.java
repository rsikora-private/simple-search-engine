package pl.robertsikora.simplesearch.engine;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import pl.robertsikora.simplesearch.engine.ranking.SourceRank;

public final class SearchResult {

    private final List<SourceRank> ranks;

    public SearchResult(final List<SourceRank> ranks) {
        this.ranks = ranks;
    }

    public SearchResult sortDesc() {
        return new SearchResult(ranks.stream().sorted(Comparator.reverseOrder())
            .collect(Collectors.toUnmodifiableList()));
    }

    public SearchResult limit(final int maxSize) {
        return new SearchResult(ranks.stream().limit(maxSize).collect(Collectors.toUnmodifiableList()));
    }

    public List<SourceRank> getRanks() {
        return List.copyOf(ranks);
    }

    public void prettyPrint(final PrintStream out) {
        if (ranks.isEmpty() || ranks.stream().noneMatch(t -> t.getRank() > 0d)) {
            out.println("no matches found");
            return;
        }
        ranks.forEach(
            rank -> out.printf("%s : %s%n", rank.getSource(), rank.getRankAsPercentage())
        );
    }
}
