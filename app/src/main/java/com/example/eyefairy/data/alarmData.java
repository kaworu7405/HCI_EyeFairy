package com.example.eyefairy.data;

public class alarmData {

    private String name;
    private String interval;
    private String day;
    private int hour;
    private int min;

    public alarmData(String name, String interval, String day, int hour, int min){

        this.name = name;
        this.day = day;
        this.interval = interval;
        this.hour = hour;
        this.min = min;

    }

    public String getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public String getInterval() {
        return interval;
    }

    public String getName() {
        return name;
    }

    public int getMin() {
        return min;
    }
}
