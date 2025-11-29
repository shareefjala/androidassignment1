package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import java.util.*;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rv;
    Button btnSearch, btnBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rv = findViewById(R.id.recyclerHotels);
        btnSearch = findViewById(R.id.buttonSearch);
        btnBookings = findViewById(R.id.buttonBookings);

        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("Oceanview Resort", "Miami", 180, 4.5f, "Beachfront hotel."));
        hotels.add(new Hotel("Mountain Lodge", "Aspen", 220, 4.7f, "Cozy mountain stay."));
        hotels.add(new Hotel("City Inn", "New York", 150, 4.0f, "Central location."));

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new HotelAdapter(this, hotels));

        btnSearch.setOnClickListener(v -> startActivity(new Intent(this, SearchActivity.class)));
        btnBookings.setOnClickListener(v -> startActivity(new Intent(this, BookingsActivity.class)));
    }
}
