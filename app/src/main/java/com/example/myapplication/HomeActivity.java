// HomeActivity.java
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HotelAdapter adapter;
    private List<Hotel> hotelList;
    private Button btnSearch, btnBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Sample hotel data (could be from static method or array)
        hotelList = new ArrayList<>();
        hotelList.add(new Hotel("Oceanview Resort", "Miami, FL", 180.0, 4.5f, "Beachfront resort with ocean view."));
        hotelList.add(new Hotel("Mountain Lodge", "Aspen, CO", 220.0, 4.7f, "Cozy lodge in the mountains."));
        hotelList.add(new Hotel("City Center Inn", "New York, NY", 150.0, 4.0f, "Hotel in the heart of the city."));
        hotelList.add(new Hotel("Desert Oasis", "Phoenix, AZ", 130.0, 3.8f, "Relaxing oasis with desert views."));
        hotelList.add(new Hotel("Lakeside Hotel", "Chicago, IL", 200.0, 4.2f, "Comfortable stay by the lake."));

        // Set up RecyclerView
        recyclerView = findViewById(R.id.recyclerViewHotels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HotelAdapter(this, hotelList);
        recyclerView.setAdapter(adapter);

        // Buttons for Search and View Bookings
        btnSearch = findViewById(R.id.buttonSearch);
        btnBookings = findViewById(R.id.buttonViewBookings);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        btnBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BookingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
