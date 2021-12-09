package com.example.aplikasidaftarhotel.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.aplikasidaftarhotel.models.Hotel;

import java.util.List;

public class HotelRepository {

    private HotelDao mHotelDao;
    private LiveData<List<Hotel>> mAllHotel;

    public HotelRepository(Application application) {
        HotelDatabase db = HotelDatabase.getDatabase(application);
        mHotelDao = db.hotelDao();
        mAllHotel = mHotelDao.getAllHotel();
    }

    LiveData<List<Hotel>> getAllHotel() {
        return mAllHotel;
    }

    public void insertHotel(List<Hotel> hotels) {
        HotelDatabase.databaseWriteExecutor.execute(() -> mHotelDao.insertAllHotel(hotels));
    }

}
