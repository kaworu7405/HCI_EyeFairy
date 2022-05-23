package com.example.eyefairy.DB;

import android.os.Parcel;

import java.io.Serializable;

public class AlarmData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int interval;
    private int howmany;
    private int hour;
    private int min;

    public AlarmData(String name, int interval, int howmany, int hour, int min){

        this.name = name;
        this.howmany = howmany;
        this.interval = interval;
        this.hour = hour;
        this.min = min;

    }

    public int getHowmany() {
        return howmany;
    }

    public int getHour() {
        return hour;
    }

    public int getInterval() {
        return interval;
    }

    public String getName() {
        return name;
    }

    public int getMin() {
        return min;
    }
}
