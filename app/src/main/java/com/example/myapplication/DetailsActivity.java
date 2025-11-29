// DetailsActivity.java
package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class DetailsActivity extends AppCompatActivity {

    private TextView tvName, tvLocation, tvPrice, tvRating, tvDescription;
    private TextView tvDate;
    private CheckBox cbBreakfast;
    private RadioGroup rgRoomType;
    private Switch switchAirport;
    private Button btnBook;
    private ImageView imageHotel;

    private int year, month, day;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Initialize views
        imageHotel   = findViewById(R.id.imageHotel);
        tvName       = findViewById(R.id.textHotelName);
        tvLocation   = findViewById(R.id.textHotelLocation);
        tvPrice      = findViewById(R.id.textHotelPrice);
        tvRating     = findViewById(R.id.textHotelRating);
        tvDescription= findViewById(R.id.textHotelDesc);
        tvDate       = findViewById(R.id.textCheckinDate);
        cbBreakfast  = findViewById(R.id.checkBoxBreakfast);
        rgRoomType   = findViewById(R.id.radioGroupRoomType);
        switchAirport= findViewById(R.id.switchAirport);
        btnBook      = findViewById(R.id.buttonBook);

        // Hotel info from intent
        String name        = getIntent().getStringExtra("name");
        String location    = getIntent().getStringExtra("location");
        double price       = getIntent().getDoubleExtra("price", 0);
        float rating       = getIntent().getFloatExtra("rating", 0f);
        String description = getIntent().getStringExtra("description");
        int imageResId     = getIntent().getIntExtra("imageResId", R.drawable.image1);

        tvName.setText(name);
        tvLocation.setText(location);
        tvPrice.setText("$" + price);
        tvRating.setText(rating + " ★");
        tvDescription.setText(description);
        imageHotel.setImageResource(imageResId);

        // Date picker
        tvDate.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();
            year  = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day   = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(
                    DetailsActivity.this,
                    (DatePicker dpView, int y, int m, int d) -> {
                        year = y;
                        month = m;
                        day = d;
                        String chosenDate = year + "-" + (month + 1) + "-" + day;
                        tvDate.setText(chosenDate);
                    },
                    year, month, day
            );
            dpd.show();
        });

        prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        btnBook.setOnClickListener(view -> {
            String date = tvDate.getText().toString();
            if (date.isEmpty()) {
                Toast.makeText(DetailsActivity.this, "Please select a date", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean breakfast = cbBreakfast.isChecked();
            int selectedId    = rgRoomType.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(DetailsActivity.this, "Please select room type", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton rb = findViewById(selectedId);
            String roomType = rb.getText().toString();
            boolean airportPickup = switchAirport.isChecked();

            try {
                String existing = prefs.getString("bookings", "[]");
                JSONArray array = new JSONArray(existing);

                JSONObject booking = new JSONObject();
                booking.put("hotel", name);
                booking.put("date", date);
                booking.put("breakfast", breakfast);
                booking.put("room", roomType);
                booking.put("airport", airportPickup);
                booking.put("imageResId", imageResId);

                array.put(booking);

                prefs.edit()
                        .putString("bookings", array.toString())
                        .apply();

                Toast.makeText(DetailsActivity.this, "Booked successfully!", Toast.LENGTH_SHORT).show();
                finish();

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(DetailsActivity.this, "Error saving booking", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
