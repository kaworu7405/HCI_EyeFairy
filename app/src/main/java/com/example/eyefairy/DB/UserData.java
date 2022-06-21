package com.example.eyefairy.DB;
import java.util.ArrayList;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.net.URI;

@Entity(tableName= "UserData")
public class UserData implements Serializable {
    public void setUserData(String _userName, int _surgery_year, int _surgery_month, int _surgery_day, String _surgery_ampm, int _surgery_hour, int _surgery_min){
        userName=_userName;
        surgery_year=_surgery_year;
        surgery_month=_surgery_month;
        surgery_day=_surgery_day;
        surgery_ampm=_surgery_ampm;
        surgery_hour=_surgery_hour;
        surgery_min=_surgery_min;

        //this.left_eye_sight = new ArrayList(); // 왼쪽 시력 정보 저장
        //this.right_eye_sight = new ArrayList(); //오른쪽 시력 정보 저장
    }

    public String getUserName(){
        return userName;
    }
    public int getSurgeryYear() {return surgery_year;}
    public int getSurgeryMonth() {return surgery_month;}
    public int getSurgeryDay() {return surgery_day;}

    public String getSurgeryAmPm() {return surgery_ampm;}
    public int getSurgery_hour() {return surgery_hour;}
    public int getSurgery_min() {return surgery_min;}

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "name")
    String userName;

    int surgery_year;
    int surgery_month;
    int surgery_day;

    String surgery_ampm;
    int surgery_hour;
    int surgery_min;

}
