package com.example.aplikasidaftarhotel.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.aplikasidaftarhotel.R;

import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;

public class DetailActivity extends AppCompatActivity {


    TextView nama_hotel;
    TextView nomor_telepon;
    TextView alamat_hotel;
    ImageView gambar_hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nama_hotel = findViewById(R.id.detail_nama_hotel);
        nomor_telepon = findViewById(R.id.detail_nomor_telepon);
        alamat_hotel = findViewById(R.id.detail_alamat_hotel);
        gambar_hotel = findViewById(R.id.detail_gambar_hotel);

        Glide.with(DetailActivity.this)
                .load(getIntent().getStringExtra("gambar_url"))
                .placeholder(R.drawable.img_error)
                .into(gambar_hotel);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            alamat_hotel.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }

        nama_hotel.setText(getIntent().getStringExtra("nama"));
        nomor_telepon.setText(getIntent().getStringExtra("nomor_telp"));
        alamat_hotel.setText(getIntent().getStringExtra("alamat"));

    }


}
