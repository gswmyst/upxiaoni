package com.example.upxiaoni;

import java.util.Comparator;

public class SortByRound implements Comparator<Score> {
    @Override
    public int compare(Score o1, Score o2) {
        if (o1.getRound()>o2.getRound()){
            return 1;
        }
        return -1;
    }
}
