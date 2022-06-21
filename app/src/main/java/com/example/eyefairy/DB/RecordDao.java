package com.example.eyefairy.DB;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface RecordDao {

    @Insert(onConflict = REPLACE)
    public void insert(RecordData data);

    @Query("SELECT * FROM table_name ORDER BY DATE DESC")
    public List<RecordData> getAll();

    @Delete
    public void delete(RecordData data);

    @Update
    public void update(RecordData data);

}
