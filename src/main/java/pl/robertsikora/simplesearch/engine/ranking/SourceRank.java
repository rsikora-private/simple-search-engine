package pl.robertsikora.simplesearch.engine.ranking;

import static java.lang.String.format;

import java.util.Objects;

public final class SourceRank implements Comparable<SourceRank> {

    private final String source;
    private final Double rank;

    public SourceRank(String source, Double rank) {
        this.source = source;
        this.rank = rank;
    }

    public String getSource() {
        return source;
    }

    public Double getRank() {
        return rank;
    }

    public String getRankAsPercentage() {
        return format("%4.0f%%", rank * 100);
    }

    @Override
    public int compareTo(SourceRank sourceRank) {
        return Double.compare(rank, sourceRank.rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SourceRank that = (SourceRank) o;
        return Objects.equals(source, that.source) && Objects.equals(rank, that.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, rank);
    }

    @Override
    public String toString() {
        return "SourceRank{"
            + "source='"
            + source
            + '\''
            + ", rank="
            + rank
            + '}';
    }
}
