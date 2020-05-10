package edu.iis.mto.similarity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimilarityFinderTests {

    private SimilarityFinder similarityFinder;
    private SequenceSearcherMock sequenceSearcherMock;
    private static int[] emptySet = new int[0];
    private static int[] multipleElementSet = {1, 2, 3};
    private static int[] slightlyDifferentMultipleElementSet = {1, 2, 4};
    private static int[] significantlyDifferentMultipleElementSet = {6, 3, 8};
    private static final double IDENTICAL_SETS = 1.0d;
    private static final double DISJOINT_SETS = 0.0d;
    private static final double SIGNIFICANTLY_DIFFERENT_SETS = 0.2d;
    private static final double SLIGHTLY_DIFFERENT_SETS = 0.5d;
    private static int[] notNullSet = {1, 2, 3};
    private static final int SEARCH_METHOD_CALL_NO = notNullSet.length;

    @BeforeEach
    void setup() {
        sequenceSearcherMock = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcherMock);
    }

    @Test
    void calculateJaccardIndexForEmptySetsTest() {
        sequenceSearcherMock.setSecondSeq(emptySet);
        double jaccardIndex = similarityFinder.calculateJackardSimilarity(emptySet, emptySet);
        assertThat(jaccardIndex, is(equalTo(IDENTICAL_SETS)));
    }

    @Test
    void calculateJaccardIndexForFirstSetEmptySecondNotEmptyTest() {
        sequenceSearcherMock.setSecondSeq(multipleElementSet);
        double jaccardIndex = similarityFinder.calculateJackardSimilarity(emptySet, multipleElementSet);
        assertThat(jaccardIndex, is(equalTo(DISJOINT_SETS)));
    }

    @Test
    void calculateJaccardIndexForFirstSetNotEmptySecondEmptyTest() {
        sequenceSearcherMock.setSecondSeq(emptySet);
        double jaccardIndex = similarityFinder.calculateJackardSimilarity(multipleElementSet, emptySet);
        assertThat(jaccardIndex, is(equalTo(DISJOINT_SETS)));
    }

    @Test
    void calculateJaccardIndexForNotEmptyIdenticalSetsTest() {
        sequenceSearcherMock.setSecondSeq(multipleElementSet);
        double jaccardIndex = similarityFinder.calculateJackardSimilarity(multipleElementSet, multipleElementSet);
        assertThat(jaccardIndex, is(equalTo(IDENTICAL_SETS)));
    }

    @Test
    void calculateJaccardIndexForSlightlyDifferentSetsTest() {
        sequenceSearcherMock.setSecondSeq(slightlyDifferentMultipleElementSet);
        double jaccardIndex = similarityFinder.calculateJackardSimilarity(multipleElementSet, slightlyDifferentMultipleElementSet);
        assertThat(jaccardIndex, is(equalTo(SLIGHTLY_DIFFERENT_SETS)));
    }

    @Test
    void calculateJaccardIndexForSignificantlyDifferentSetsTest() {
        sequenceSearcherMock.setSecondSeq(significantlyDifferentMultipleElementSet);
        double jaccardIndex = similarityFinder.calculateJackardSimilarity(multipleElementSet, significantlyDifferentMultipleElementSet);
        assertThat(jaccardIndex, is(equalTo(SIGNIFICANTLY_DIFFERENT_SETS)));
    }

    @Test
    void checkIfFirstSetIsNullSecondNotNullTest() {
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(null, notNullSet));
    }

    @Test
    void checkIfFirstSetIsNotNullSecondNullTest() {
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(notNullSet, null));
    }

    @Test
    void checkIfBothSetsAreNullTest() {
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(null, null));
    }

    @Test
    void checkIfBothSetsAreNotNullTest() {
        assertDoesNotThrow(() -> similarityFinder.calculateJackardSimilarity(notNullSet, notNullSet));
    }

    @Test
    void verifyHowManyTimesSearchMethodIsCalledForNotNullSetsTest() {
        similarityFinder.calculateJackardSimilarity(notNullSet, notNullSet);
        assertThat(sequenceSearcherMock.getSearchMethodCallNumber(), is(equalTo(SEARCH_METHOD_CALL_NO)));
    }
}
