// for displaying bookingss
package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    private final List<Booking> bookingList;
    private final Context context;

    public BookingAdapter(Context context, List<Booking> bookings) {
        this.context = context;
        this.bookingList = bookings;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHotel, tvDate;
        public ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHotel = itemView.findViewById(R.id.textBookingHotel);
            tvDate = itemView.findViewById(R.id.textBookingDate);
            ivImage = itemView.findViewById(R.id.bookingImage);
        }
    }

    @NonNull
    @Override
    public BookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.ViewHolder holder, int position) {
        Booking booking = bookingList.get(position);

        holder.tvHotel.setText(booking.getHotelName());
        holder.tvDate.setText(booking.getDate());
        holder.ivImage.setImageResource(booking.getImageResId());

        // -> edit booking
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditBookingActivity.class);
            intent.putExtra("hotelName", booking.getHotelName());
            intent.putExtra("date", booking.getDate());
            intent.putExtra("breakfast", booking.hasBreakfast());
            intent.putExtra("roomType", booking.getRoomType());
            intent.putExtra("airportPickup", booking.isAirportPickup());
            intent.putExtra("imageResId", booking.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }
}
