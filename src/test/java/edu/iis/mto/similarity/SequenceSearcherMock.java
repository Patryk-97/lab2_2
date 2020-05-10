package edu.iis.mto.similarity;

import java.util.HashMap;
import java.util.Map;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherMock implements SequenceSearcher {

    private int searchMethodCallNumber = 0;
    private Map<Integer, Integer> secondSeq = new HashMap<>();

    public int getSearchMethodCallNumber() {
        return searchMethodCallNumber;
    }

    public void setSecondSeq(int[] secondSeq) {
        int i = 0;
        for (int elem : secondSeq) {
            this.secondSeq.put(elem, i++);
        }
    }

    @Override
    public SearchResult search(int key, int[] seq) {

        searchMethodCallNumber++;

        SearchResult.Builder builder = SearchResult.builder();

        if (secondSeq.containsKey(key)) {
            builder.withFound(true)
                   .withPosition(secondSeq.get(key));
        } else {
            builder.withFound(false)
                   .withPosition(-1);
        }

        return builder.build();
    }

}
