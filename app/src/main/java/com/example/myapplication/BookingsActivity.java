// BookingsActivity.java
package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookingsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookingAdapter adapter;
    private List<Booking> bookingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        recyclerView = findViewById(R.id.recyclerViewBookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookingList = new ArrayList<>();
        loadBookingsFromPrefs();

        adapter = new BookingAdapter(this, bookingList);
        recyclerView.setAdapter(adapter);
    }

    private void loadBookingsFromPrefs() {
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String json = prefs.getString("bookings", "[]");

        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String hotel = obj.getString("hotel");
                String date = obj.getString("date");
                boolean breakfast = obj.getBoolean("breakfast");
                String room = obj.getString("room");
                boolean airport = obj.getBoolean("airport");
                int imageResId = obj.optInt("imageResId", R.drawable.image1); // default if missing

                bookingList.add(new Booking(
                        hotel,
                        date,
                        breakfast,
                        room,
                        airport,
                        imageResId
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
