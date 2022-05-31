package com.example.eyefairy.DB;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface AlarmDao {
    @Insert(onConflict = REPLACE)
    public void insert(AlarmData data);

    @Query("SELECT * FROM table_name")
    public List<AlarmData> getAll();

    @Delete
    public void delete(AlarmData data);

    @Update
    public void update(AlarmData data);

}
