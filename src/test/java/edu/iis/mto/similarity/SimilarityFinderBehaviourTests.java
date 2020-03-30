package edu.iis.mto.similarity;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimilarityFinderBehaviourTests {

    private SimilarityFinder similarityFinder;
    private SequenceSearcherMock sequenceSearcherMock;
    private static int[] notNullSet = {1, 2, 3};

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

    }

    @Test
    void checkIfBothSetsAreNull() {

    }

    @Test
    void checkIfBothSetsAreNotNull() {

    }

    @Test
    void verifyHowManyTimesSearchMethodIsCalled() {

    }
}
