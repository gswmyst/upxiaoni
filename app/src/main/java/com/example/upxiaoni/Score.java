package com.example.upxiaoni;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

public class Score {
    private double value;
    private String time;
    private int round;
    private int num;
    private String mode;

    public Score(String value, String time,String round,String num,String mode) {
        BigDecimal bd=new BigDecimal(Double.parseDouble(value)/10);//成绩保留小数点后3位
        this.value=bd.setScale(3, RoundingMode.HALF_UP).doubleValue();
        long time1=Long.parseLong(time);
        long time2=time1*1000;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fmDate=simpleDateFormat.format(time2);
        this.time=fmDate;
        this.round=Integer.parseInt(round);
        this.num=Integer.parseInt(num);
        if (mode.equals("1"))
            this.mode="速桩";
        else
            this.mode="速滑";
    }
    public int getValLength(){
        String val=String.format("%.3f", value);
        return val.length();
    }
    public double getValue() {
        return value;
    }

    public String getTime() {
        return time;
    }

    public int getRound() {
        return round;
    }

    public int getNum() {
        return num;
    }

    public String getMode() {
        return mode;
    }

    public String getStringValue(){return String.format("%.3f", value);}
}
