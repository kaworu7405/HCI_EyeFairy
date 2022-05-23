package com.example.eyefairy.DB;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.net.URI;

@Entity(tableName= "UserData")
public class UserData implements Serializable {
    public void setUserData(String _userName, String _uri, int _surgery_year, int _surgery_month, int _surgery_day){
        userName=_userName;
        uri=_uri;
        surgery_year=_surgery_year;
        surgery_month=_surgery_month;
        surgery_day=_surgery_day;
    }

    public String getUserName(){
        return userName;
    }
    public Uri getURI() {return Uri.parse(uri); }
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "name")
    String userName;

    String uri;
    int surgery_year;
    int surgery_month;
    int surgery_day;
}
