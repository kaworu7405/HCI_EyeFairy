package com.example.eyefairy.DB;

import android.os.Parcel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_name")
public class AlarmData implements Serializable {
    public AlarmData(String name, int howManyTimes, int howManyDays, int intervalH, int intervalM,  String amPm, int hour, int min, String kind){
        this.name = name;
        this.howManyTimes = howManyTimes;
        this.howManyDays = howManyDays;
        this.intervalH = intervalH;
        this.intervalM = intervalM;
        this.hour = hour;
        this.min = min;
        this.amPm=amPm;
        this.kind=kind;
    }
    private static final long serialVersionUID = 1L;


    @PrimaryKey(autoGenerate = true)
    int key;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "intervalH")
    private int intervalH;
    @ColumnInfo(name = "intervalM")
    private int intervalM;
    @ColumnInfo(name = "howManyTimes")
    private int howManyTimes;
    @ColumnInfo(name="howManyDays")
    private int howManyDays;
    @ColumnInfo(name = "hour")
    private int hour;
    @ColumnInfo(name = "min")
    private int min;
    @ColumnInfo(name = "amPm")
    private String amPm;
    @ColumnInfo(name = "kind")
    private String kind;

    public String getAmPm() {
        return amPm;
    }
    public int getHowManyTimes() {
        return howManyTimes;
    }
    public int getHowManyDays()
    {
        return howManyDays;
    }
    public int getHour() {
        return hour;
    }
    public int getIntervalH() {
        return intervalH;
    }
    public int getIntervalM() {
        return intervalM;
    }
    public String getName() {
        return name;
    }
    public int getMin() {
        return min;
    }
    public int getKey(){return key;}
    public String getKind(){return kind;}


    public void setAmPm(String _ampm) {
        amPm=_ampm;
    }
    public void setHowManyTimes(int _howmany) {
        howManyTimes=_howmany;
    }
    public void setHour(int _hour) {
        hour=_hour;
    }
    public void setIntervalH(int _intervalH) {
        intervalH=_intervalH;
    }
    public void setKey(int _key){key=_key;}
    public void setIntervalM(int _intervalM) {
        intervalM=_intervalM;
    }
    public void setName(String _name) {
        name=_name;
    }
    public void setMin(int _min) {
        min=_min;
    }
    public void setKind(String _kind) {kind = _kind;}
    public void setHowManyDays(int _howManyDays){
        howManyDays=_howManyDays;
    }
}
