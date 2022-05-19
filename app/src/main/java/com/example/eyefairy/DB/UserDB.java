package com.example.eyefairy.DB;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserData.class}, version = 1, exportSchema = false)
public abstract class UserDB extends RoomDatabase
{
    public abstract UserDao userDao();
    private static UserDB INSTANCE;

    public synchronized static UserDB getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDB.class, "userData")
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