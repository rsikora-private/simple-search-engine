package pl.robertsikora.simplesearch.engine;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static pl.robertsikora.simplesearch.engine.index.LineSpliterator.splitToNormalizedWords;
import static pl.robertsikora.simplesearch.engine.index.WordIndexer.simpleIndexer;
import static pl.robertsikora.simplesearch.engine.ranking.RankingCalculator.simpleRankingCalculator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import pl.robertsikora.simplesearch.engine.index.WordIndexer;
import pl.robertsikora.simplesearch.engine.ranking.RankingCalculator;

public class SimpleSearchEngine {

    private static final Logger LOGGER = new Logger();

    private final WordIndexer wordIndexer;
    private final RankingCalculator rankingCalculator;

    public SimpleSearchEngine(WordIndexer wordIndexer,
        RankingCalculator rankingCalculator) {
        this.wordIndexer = wordIndexer;
        this.rankingCalculator = rankingCalculator;
    }

    public static SimpleSearchEngine createSimpleSearchEngine() {
        final var indexer = simpleIndexer();
        return new SimpleSearchEngine(indexer, simpleRankingCalculator(indexer));
    }

    public void indexDirectory(final File directory) {
        requireNonNull(directory, "directory is required!");

        if (!directory.exists()) {
            throw new IllegalArgumentException(format("Given directory: %s does not exist!", directory));
        }

        try (var directoryStream = Files.newDirectoryStream(directory.toPath(), "*.txt")) {
            var fileCounter = 0;
            for (final var filePath : directoryStream) {
                readFileContent(filePath);
                fileCounter++;
            }
            LOGGER.info(format("%d files read in directory %s", fileCounter, directory.getPath()));
        } catch (IOException ex) {
            LOGGER.error("Cannot read files from given directory!", ex);
            throw new IllegalStateException("Cannot read from given directory!");
        }
    }

    public SearchResult searchByQuery(final SearchQuery searchQuery) {
        requireNonNull(searchQuery, "search query is required!");

        LOGGER.info(format("searching by: %s", searchQuery));

        final var searchWords = searchQuery.words();

        final var sourceRanks = wordIndexer.getIndexedSources().stream()
            .map(source -> rankingCalculator.getRank(source, searchWords)).collect(Collectors.toUnmodifiableList());

        return new SearchResult(sourceRanks);
    }

    private void readFileContent(final Path filePath) throws IOException {
        try (final var lines = Files.lines(filePath)) {
            lines.flatMap(line -> Stream.of(splitToNormalizedWords(line)))
                .forEach(word -> wordIndexer.indexWord(filePath.getFileName().toString(), word));
        }
    }

}
