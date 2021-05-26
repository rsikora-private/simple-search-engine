package pl.robertsikora.simplesearch.engine.index;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LineSpliteratorTest {

    @Test
    void shouldSplitToEmptyArrayForNullInput() {
        //when
        var words = LineSpliterator.splitToNormalizedWords(null);
        //then
        assertThat(words).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "some short testing line",
        "  some  short       testing line    ",
        "Some Short Testing Line",
    })
    void shouldSplitToNormalizedWordsForGivenLine(String input) {
        //when
        var words = LineSpliterator.splitToNormalizedWords(input);
        //then
        assertThat(words).containsExactly("some", "short", "testing", "line");
    }
}