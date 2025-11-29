// home
package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        // hi {username} from sharedprefernces
        TextView tvGreeting = findViewById(R.id.tvGreeting);  // must exist in activity_home.xml

        SharedPreferences prefs = getSharedPreferences("LoginData", MODE_PRIVATE);
        String username = prefs.getString("username", "Guest");

        if (tvGreeting != null) {
            tvGreeting.setText("Hi, " + username + "!");
        }

        // random hotel data
        hotelList = new ArrayList<>();
        hotelList.add(new Hotel("Oceanview Resort", "Miami, FL", 180.0, 4.5f, "Beachfront resort with ocean view.",
                R.drawable.image1));
        hotelList.add(new Hotel("Mountain Lodge", "Aspen, CO", 220.0, 4.7f, "Cozy lodge in the mountains.",
                R.drawable.image1));
        hotelList.add(new Hotel("City Center Inn", "New York, NY", 150.0, 4.0f, "Hotel in the heart of the city.",
                R.drawable.image1));
        hotelList.add(new Hotel("Desert Oasis", "Phoenix, AZ", 130.0, 3.8f, "Relaxing oasis with desert views.",
                R.drawable.image1));
        hotelList.add(new Hotel("Lakeside Hotel", "Chicago, IL", 200.0, 4.2f, "Comfortable stay by the lake.",
                R.drawable.image1));

        // RecyclerView from project instructions
        recyclerView = findViewById(R.id.recyclerViewHotels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HotelAdapter(this, hotelList);
        recyclerView.setAdapter(adapter);

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
