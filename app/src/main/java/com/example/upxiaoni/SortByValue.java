package com.example.upxiaoni;

import java.util.Comparator;

public class SortByValue implements Comparator<Score> {
    @Override
    public int compare(Score o1, Score o2) {
        if (o1.getValue()>o2.getValue()){
            return 1;
        }
        return -1;
    }
}
