// SearchActivity.java
package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText etName, etMinPrice, etMinRating;
    private Button btnSearch;
    private RecyclerView recyclerView;
    private HotelAdapter adapter;
    private List<Hotel> allHotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        etName = findViewById(R.id.editTextSearchName);
        etMinPrice = findViewById(R.id.editTextMinPrice);
        etMinRating = findViewById(R.id.editTextMinRating);
        btnSearch = findViewById(R.id.buttonDoSearch);
        recyclerView = findViewById(R.id.recyclerViewSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample hotel data (same as Home)
        allHotels = new ArrayList<>();
        allHotels.add(new Hotel("Oceanview Resort", "Miami, FL", 180.0, 4.5f, "Beachfront resort with ocean view."));
        allHotels.add(new Hotel("Mountain Lodge", "Aspen, CO", 220.0, 4.7f, "Cozy lodge in the mountains."));
        allHotels.add(new Hotel("City Center Inn", "New York, NY", 150.0, 4.0f, "Hotel in the heart of the city."));
        allHotels.add(new Hotel("Desert Oasis", "Phoenix, AZ", 130.0, 3.8f, "Relaxing oasis with desert views."));
        allHotels.add(new Hotel("Lakeside Hotel", "Chicago, IL", 200.0, 4.2f, "Comfortable stay by the lake."));

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameFilter = etName.getText().toString().toLowerCase().trim();
                String minPriceStr = etMinPrice.getText().toString().trim();
                String minRatingStr = etMinRating.getText().toString().trim();
                double minPrice = 0;
                float minRating = 0;
                if (!TextUtils.isEmpty(minPriceStr)) {
                    try { minPrice = Double.parseDouble(minPriceStr); } catch (NumberFormatException e) { minPrice = 0; }
                }
                if (!TextUtils.isEmpty(minRatingStr)) {
                    try { minRating = Float.parseFloat(minRatingStr); } catch (NumberFormatException e) { minRating = 0; }
                }
                // Filter hotels
                List<Hotel> results = new ArrayList<>();
                for (Hotel hotel : allHotels) {
                    boolean matches = true;
                    if (!nameFilter.isEmpty() && !hotel.getName().toLowerCase().contains(nameFilter)) {
                        matches = false;
                    }
                    if (hotel.getPrice() < minPrice) {
                        matches = false;
                    }
                    if (hotel.getRating() < minRating) {
                        matches = false;
                    }
                    if (matches) results.add(hotel);
                }
                // Update RecyclerView
                adapter = new HotelAdapter(SearchActivity.this, results);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
