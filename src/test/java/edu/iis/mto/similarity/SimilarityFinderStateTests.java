package edu.iis.mto.similarity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimilarityFinderStateTests {

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

    @BeforeEach
    void setup() {
        sequenceSearcherMock = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcherMock);
    }

    @Test
    void calculateJaccardIndexForEmptySetsTest() {
        double jaccardIndex = similarityFinder.calculateJackardSimilarity(emptySet, emptySet);
        assertThat(jaccardIndex, is(equalTo(IDENTICAL_SETS)));
    }

    @Test
    void calculateJaccardIndexForFirstSetEmptySecondNotEmptyTest() {
        double jaccardIndex = similarityFinder.calculateJackardSimilarity(emptySet, multipleElementSet);
        assertThat(jaccardIndex, is(equalTo(DISJOINT_SETS)));
    }

    @Test
    void calculateJaccardIndexForFirstSetNotEmptySecondEmptyTest() {
        double jaccardIndex = similarityFinder.calculateJackardSimilarity(multipleElementSet, emptySet);
        assertThat(jaccardIndex, is(equalTo(DISJOINT_SETS)));
    }

    @Test
    void calculateJaccardIndexForIdenticalSetsTest() {

    }

    @Test
    void calculateJaccardIndexForSlightlyDifferentSetsTest() {

    }

    @Test
    void calculateJaccardIndexForSignificantlyDifferentSetsTest() {

    }
}
