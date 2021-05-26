package pl.robertsikora.simplesearch.engine.index;

public class LineSpliterator {

    private static final String REGEX = "\\s+";

    private LineSpliterator() {
    }

    public static String[] splitToNormalizedWords(final String line) {
        if (line == null) {
            return new String[0];
        }
        return line.trim().toLowerCase().split(REGEX);
    }
}
