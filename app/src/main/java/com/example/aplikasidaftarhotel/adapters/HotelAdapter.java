package com.example.aplikasidaftarhotel.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aplikasidaftarhotel.R;
import com.example.aplikasidaftarhotel.activities.DetailActivity;
import com.example.aplikasidaftarhotel.models.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    List<Hotel> dataHotel = new ArrayList<>();
    Context context;

    public HotelAdapter(Context ct) {
        this.context = ct;
    }

    public void setHotelList(List<Hotel> hotels) {
        this.dataHotel = hotels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {

        Hotel thisHotel = dataHotel.get(position);

        holder.bindTo(thisHotel);

    }

    @Override
    public int getItemCount() {
        if (this.dataHotel != null) {
            return dataHotel.size();
        }
        return 0;
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView namaHotel, alamatHotel;
        ImageView gambarHotel;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);

            namaHotel = itemView.findViewById(R.id.namaHotel);
            alamatHotel = itemView.findViewById(R.id.alamatHotel);
            gambarHotel = itemView.findViewById(R.id.gambar);

            itemView.setOnClickListener(this);

        }

        public void bindTo(Hotel currentHotel) {
            namaHotel.setText(currentHotel.getNama());
            alamatHotel.setText(currentHotel.getAlamat());

            Glide.with(context)
                 .load(currentHotel.getGambar_url())
                 .skipMemoryCache(true)
                 .placeholder(R.drawable.img_error)
                 .centerCrop()
                 .error(R.drawable.img_error)
                 .into(gambarHotel);
        }

        @Override
        public void onClick(View view) {
            Hotel currentHotel = dataHotel.get(getAdapterPosition());
            Intent detailIntent = new Intent(context, DetailActivity.class);
            detailIntent.putExtra("id",currentHotel.getId().toString());
            detailIntent.putExtra("nama", currentHotel.getNama());
            detailIntent.putExtra("alamat", currentHotel.getAlamat());
            detailIntent.putExtra("nomor_telp", currentHotel.getNomor_telp());
            detailIntent.putExtra("kordinat", currentHotel.getKordinat());
            detailIntent.putExtra("gambar_url", currentHotel.getGambar_url());

            context.startActivity(detailIntent);
        }
    }
}
