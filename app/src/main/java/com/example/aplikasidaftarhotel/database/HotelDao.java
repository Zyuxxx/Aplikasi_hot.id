package com.example.aplikasidaftarhotel.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.aplikasidaftarhotel.models.Hotel;

import java.util.List;

@Dao
public interface HotelDao {

    @Query("SELECT * from hotel_table")
    LiveData<List<Hotel>> getAllHotel();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllHotel(List<Hotel> hotels);

    @Query("DELETE FROM hotel_table")
    void deleteAllHotel();

}
