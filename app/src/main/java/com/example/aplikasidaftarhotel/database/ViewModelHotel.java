package com.example.aplikasidaftarhotel.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aplikasidaftarhotel.models.Hotel;

import java.util.List;

public class ViewModelHotel extends AndroidViewModel {

    private HotelRepository mRepository;
    private LiveData<List<Hotel>> mAllHotel;

    public ViewModelHotel(@NonNull Application application) {
        super(application);
        mRepository = new HotelRepository(application);
        mAllHotel = mRepository.getAllHotel();
    }


    public LiveData<List<Hotel>> getAllHotel() {
        return mAllHotel;
    }

    public void insertHotel(List<Hotel> hotels) {
        mRepository.insertHotel(hotels);
    }

}
