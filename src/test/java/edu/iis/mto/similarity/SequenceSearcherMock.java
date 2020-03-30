package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherMock implements SequenceSearcher {

    private int searchMethodCallNumber = 0;

    public int getSearchMethodCallNumber() {
        return searchMethodCallNumber;
    }

    @Override
    public SearchResult search(int key, int[] seq) {

        searchMethodCallNumber++;

        SearchResult.Builder builder = SearchResult.builder();
        builder.withFound(false)
               .withPosition(-1);

        for (int i = 0; i < seq.length; i++) {
            if (seq[i] == key) {
                builder.withFound(true)
                       .withPosition(i);
                break;
            }
        }

        return builder.build();
    }

}
