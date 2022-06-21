package com.example.eyefairy.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AlarmData.class}, version = 1, exportSchema = false)
public abstract class AlarmDB extends RoomDatabase {
    public abstract AlarmDao alarmDao();
    private static AlarmDB INSTANCE;

    public synchronized static AlarmDB getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AlarmDB.class, "alarmData")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE=null;
    }
}
