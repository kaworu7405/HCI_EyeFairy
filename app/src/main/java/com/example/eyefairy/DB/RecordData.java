
package com.example.eyefairy.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_name")
public class RecordData implements Serializable {
    public RecordData(String Date, int leftEyeSight, int rightEyeSight){

        this.Date = Date;
        this.leftEyeSight=leftEyeSight;
        this.rightEyeSight=rightEyeSight;
    }
    private static final long serialVersionUID = 1L;


    @PrimaryKey(autoGenerate = true)
    int key;
    @ColumnInfo(name = "Date")
    private String Date;
    @ColumnInfo(name = "leftEyeSight")
    private int leftEyeSight;
    @ColumnInfo(name = "rightEyeSight")
    private int rightEyeSight;

    public String getDate() {
        return this.Date;
    }
    public int getLeftEyeSight() {
        return this.leftEyeSight;
    }
    public int getRightEyeSight()
    {
        return this.rightEyeSight;
    }

}
