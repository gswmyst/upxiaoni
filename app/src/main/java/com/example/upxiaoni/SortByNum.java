package com.example.upxiaoni;

import java.util.Comparator;

public class SortByNum implements Comparator<Score> {
    @Override
    public int compare(Score o1, Score o2) {
        if (o1.getNum()<o2.getNum()){
            return 1;
        }
        return -1;
    }
}
