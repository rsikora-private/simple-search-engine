package pl.robertsikora.simplesearch.engine;

import static pl.robertsikora.simplesearch.engine.index.LineSpliterator.splitToNormalizedWords;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class SearchQuery {

    private final String query;

    public SearchQuery(final String query) {
        this.query = query;
    }

    public Set<String> words() {
        return new HashSet<>(Arrays.asList(splitToNormalizedWords(query)));
    }

    @Override
    public String toString() {
        return "SearchQuery{"
            + "query='"
            + query
            + '\''
            + '}';
    }
}
