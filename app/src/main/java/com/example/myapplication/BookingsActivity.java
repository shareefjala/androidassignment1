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

public class BookingsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BookingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        recyclerView = findViewById(R.id.recyclerViewBookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Booking> bookings = new ArrayList<>();
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String json = prefs.getString("bookings", "[]");
        try {
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String hotel = obj.getString("hotel");
                String date = obj.getString("date");
                boolean breakfast = obj.getBoolean("breakfast");
                String room = obj.getString("room");
                boolean airport = obj.getBoolean("airport");
                bookings.add(new Booking(hotel, date, breakfast, room, airport));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new BookingAdapter(bookings);
        recyclerView.setAdapter(adapter);
    }
}
