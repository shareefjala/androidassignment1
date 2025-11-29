// BookingAdapter.java (for displaying bookings)
package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {
    private List<Booking> bookingList;

    public BookingAdapter(List<Booking> bookings) {
        this.bookingList = bookings;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHotel, tvDate;
        public ViewHolder(View itemView) {
            super(itemView);
            tvHotel = itemView.findViewById(R.id.textBookingHotel);
            tvDate = itemView.findViewById(R.id.textBookingDate);
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
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }
}
