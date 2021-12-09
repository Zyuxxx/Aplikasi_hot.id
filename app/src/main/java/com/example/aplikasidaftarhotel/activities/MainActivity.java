package com.example.aplikasidaftarhotel.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.aplikasidaftarhotel.R;
import com.example.aplikasidaftarhotel.adapters.HotelAdapter;
import com.example.aplikasidaftarhotel.models.Hotel;
import com.example.aplikasidaftarhotel.database.ViewModelHotel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    HotelAdapter mainAdapter;
    RecyclerView idRecyclerView;
    ViewModelHotel mViewModelHotel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idRecyclerView = findViewById(R.id.idRecyclerView);

        mainAdapter = new HotelAdapter(this);
        idRecyclerView.setAdapter(mainAdapter);

        AndroidNetworking.initialize(getApplicationContext());
        mViewModelHotel = new ViewModelProvider(this).get(ViewModelHotel.class);

        ambilDataDariApi();

        mViewModelHotel.getAllHotel().observe(this, new Observer<List<Hotel>>() {

            @Override
            public void onChanged(List<Hotel> hotels) {
                mainAdapter.setHotelList(hotels);
            }
        });

        //Date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textView = findViewById(R.id.date);
        textView.setText(currentDate);

    }

    public void ambilDataDariApi() {

        List<Hotel> dataHotel = new ArrayList<>();
        AndroidNetworking.get("https://dev.farizdotid.com/api/purwakarta/hotel")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(MainActivity.this, "Loading ...", Toast.LENGTH_SHORT).show();

                            JSONArray jsonArray = response.getJSONArray("hotel");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                Hotel item = new Hotel(
                                        data.getInt("id"),
                                        data.getString("nama"),
                                        data.getString("alamat"),
                                        data.getString("nomor_telp"),
                                        data.getString("kordinat"),
                                        data.getString("gambar_url"));

                                dataHotel.add(item);
                            }

                            mViewModelHotel.insertHotel(dataHotel);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
