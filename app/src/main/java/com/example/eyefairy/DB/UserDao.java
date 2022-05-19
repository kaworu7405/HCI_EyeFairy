package com.example.eyefairy.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao
{
    @Insert(onConflict = REPLACE)
    public void insert(UserData user);

    @Query("SELECT * FROM UserData")
    public List<UserData> getAll();
}
