package com.example.eyefairy.DB;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RecordData.class}, version = 1, exportSchema = false)
public abstract class RecordDB extends RoomDatabase
{
    public abstract RecordDao RecordDao();
    private static RecordDB INSTANCE;

    public synchronized static RecordDB getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RecordDB.class, "RecordData")
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