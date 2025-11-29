// HotelAdapter.java (RecyclerView Adapter)
package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    private List<Hotel> hotelList;
    private Context context;

    public HotelAdapter(Context context, List<Hotel> hotels) {
        this.context = context;
        this.hotelList = hotels;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvLocation, tvPrice;
        public RatingBar ratingBar;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.hotelName);
            tvLocation = itemView.findViewById(R.id.hotelLocation);
            tvPrice = itemView.findViewById(R.id.hotelPrice);
            ratingBar = itemView.findViewById(R.id.hotelRating);
        }
    }

    @NonNull
    @Override
    public HotelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate item layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hotel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelAdapter.ViewHolder holder, int position) {
        final Hotel hotel = hotelList.get(position);
        holder.tvName.setText(hotel.getName());
        holder.tvLocation.setText(hotel.getLocation());
        holder.tvPrice.setText("$" + hotel.getPrice() + " / night");
        holder.ratingBar.setRating(hotel.getRating());
        // Item click opens details screen
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("name", hotel.getName());
                intent.putExtra("location", hotel.getLocation());
                intent.putExtra("price", hotel.getPrice());
                intent.putExtra("rating", hotel.getRating());
                intent.putExtra("description", hotel.getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }
}
