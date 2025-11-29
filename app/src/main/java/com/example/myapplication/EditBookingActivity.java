package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EditBookingActivity extends AppCompatActivity {

    private TextView tvHotelName;
    private ImageView ivHotelImage;
    private EditText etDate, etRoomType;
    private CheckBox cbBreakfast, cbAirportPickup;
    private Button btnSave, btnCancel;

    private int bookingIndex; // position inside JSON array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_booking);

        tvHotelName = findViewById(R.id.tvEditHotelName);
        ivHotelImage = findViewById(R.id.ivEditHotelImage);
        etDate = findViewById(R.id.etEditDate);
        etRoomType = findViewById(R.id.etEditRoomType);
        cbBreakfast = findViewById(R.id.cbEditBreakfast);
        cbAirportPickup = findViewById(R.id.cbEditAirportPickup);
        btnSave = findViewById(R.id.btnSaveBooking);
        btnCancel = findViewById(R.id.btnCancelEdit);

        // READ INDEX FIRST
        bookingIndex = getIntent().getIntExtra("index", -1);

        String hotelName = getIntent().getStringExtra("hotelName");
        String date = getIntent().getStringExtra("date");
        boolean breakfast = getIntent().getBooleanExtra("breakfast", false);
        String roomType = getIntent().getStringExtra("roomType");
        boolean airportPickup = getIntent().getBooleanExtra("airportPickup", false);
        int imageResId = getIntent().getIntExtra("imageResId", 0);

        tvHotelName.setText(hotelName);
        etDate.setText(date);
        etRoomType.setText(roomType);
        cbBreakfast.setChecked(breakfast);
        cbAirportPickup.setChecked(airportPickup);

        if (imageResId != 0) {
            ivHotelImage.setImageResource(imageResId);
        }

        btnSave.setOnClickListener(v -> {
            String newDate = etDate.getText().toString().trim();
            String newRoomType = etRoomType.getText().toString().trim();
            boolean newBreakfast = cbBreakfast.isChecked();
            boolean newAirportPickup = cbAirportPickup.isChecked();

            if (bookingIndex < 0) {
                Toast.makeText(this, "Cannot find booking to update", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            try {
                SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                String json = prefs.getString("bookings", "[]");
                JSONArray array = new JSONArray(json);

                if (bookingIndex >= array.length()) {
                    Toast.makeText(this, "Booking index out of range", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }

                JSONObject obj = array.getJSONObject(bookingIndex);
                obj.put("date", newDate);
                obj.put("room", newRoomType);
                obj.put("breakfast", newBreakfast);
                obj.put("airport", newAirportPickup);

                prefs.edit()
                        .putString("bookings", array.toString())
                        .apply();

                Toast.makeText(this, "Booking updated", Toast.LENGTH_SHORT).show();
                finish();

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error updating booking", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
