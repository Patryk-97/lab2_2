package edu.iis.mto.similarity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimilarityFinderBehaviourTests {

    private SimilarityFinder similarityFinder;
    private SequenceSearcherMock sequenceSearcherMock;
    private static int[] notNullSet = {1, 2, 3};
    private static final int SEARCH_METHOD_CALL_NO = notNullSet.length;

    @BeforeEach
    void setup() {
        sequenceSearcherMock = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcherMock);
    }

    @Test
    void checkIfFirstSetIsNullSecondNotNull() {
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(null, notNullSet));
    }

    @Test
    void checkIfFirstSetIsNotNullSecondNull() {
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(notNullSet, null));
    }

    @Test
    void checkIfBothSetsAreNull() {
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(null, null));
    }

    @Test
    void checkIfBothSetsAreNotNull() {
        assertDoesNotThrow(() -> similarityFinder.calculateJackardSimilarity(notNullSet, notNullSet));
    }

    @Test
    void verifyHowManyTimesSearchMethodIsCalledForNotNullSets() {
        similarityFinder.calculateJackardSimilarity(notNullSet, notNullSet);
        assertThat(sequenceSearcherMock.getSearchMethodCallNumber(), is(equalTo(SEARCH_METHOD_CALL_NO)));
    }
}
