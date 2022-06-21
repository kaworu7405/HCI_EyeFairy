
package com.example.eyefairy.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_name")
public class RecordData implements Serializable {
    public RecordData(String date, float leftEyeSight, float rightEyeSight){

        this.date = date;
        this.leftEyeSight=leftEyeSight;
        this.rightEyeSight=rightEyeSight;
    }
    private static final long serialVersionUID = 1L;


    @PrimaryKey(autoGenerate = true)
    int key;
    @ColumnInfo(name = "Date")
    private String date;
    @ColumnInfo(name = "leftEyeSight")
    private float leftEyeSight;
    @ColumnInfo(name = "rightEyeSight")
    private float rightEyeSight;

    public int getKey(){return key;}

    public String getDate() {
        return this.date;
    }
    public float getLeftEyeSight() {
        return this.leftEyeSight;
    }
    public float getRightEyeSight()
    {
        return this.rightEyeSight;
    }

    public void setDate(String _date) {date=_date;}
    public void setLeftEyeSight(float _leftEyeSight){leftEyeSight=_leftEyeSight;}
    public void setRightEyeSight(float _rightEyeSight) {rightEyeSight=_rightEyeSight;}
    public void setKey(int _key){key=_key;}

}
