package com.example.upxiaoni;

import java.text.SimpleDateFormat;

public class Score {
    private double value;
    private String time;

    public Score(String value, String time) {
        this.value=Double.parseDouble(value);
        long time1=Long.parseLong(time);
        long time2=time1*1000;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fmDate=simpleDateFormat.format(time2);
        this.time=fmDate;
    }

    public double getValue() {
        return value;
    }

    public String getTime() {
        return time;
    }
}
