package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.Holder> {

    Context c;
    List<Hotel> data;

    public HotelAdapter(Context c, List<Hotel> data){
        this.c = c;
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.item_hotel, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder h, int pos) {
        Hotel hotel = data.get(pos);

        h.name.setText(hotel.name);
        h.location.setText(hotel.location);
        h.price.setText("$" + hotel.price);
        h.rating.setRating(hotel.rating);

        h.itemView.setOnClickListener(v -> {
            Intent i = new Intent(c, DetailsActivity.class);
            i.putExtra("name", hotel.name);
            i.putExtra("location", hotel.location);
            i.putExtra("price", hotel.price);
            i.putExtra("rating", hotel.rating);
            i.putExtra("desc", hotel.desc);
            c.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView name, location, price;
        RatingBar rating;

        Holder(View v){
            super(v);
            name = v.findViewById(R.id.hotelName);
            location = v.findViewById(R.id.hotelLocation);
            price = v.findViewById(R.id.hotelPrice);
            rating = v.findViewById(R.id.hotelRating);
        }
    }
}
