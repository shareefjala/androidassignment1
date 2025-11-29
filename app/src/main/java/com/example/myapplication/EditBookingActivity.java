// EditBookingActivity.java
package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditBookingActivity extends AppCompatActivity {

    private TextView tvHotelName;
    private ImageView ivHotelImage;
    private EditText etDate, etRoomType;
    private CheckBox cbBreakfast, cbAirportPickup;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_booking); // you create this XML

        // Find views (these ids must exist in activity_edit_booking.xml)
        tvHotelName = findViewById(R.id.tvEditHotelName);
        ivHotelImage = findViewById(R.id.ivEditHotelImage);
        etDate = findViewById(R.id.etEditDate);
        etRoomType = findViewById(R.id.etEditRoomType);
        cbBreakfast = findViewById(R.id.cbEditBreakfast);
        cbAirportPickup = findViewById(R.id.cbEditAirportPickup);
        btnSave = findViewById(R.id.btnSaveBooking);
        btnCancel = findViewById(R.id.btnCancelEdit);

        // data from intent
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

        // read values and save
        btnSave.setOnClickListener(v -> {
            String newDate = etDate.getText().toString().trim();
            String newRoomType = etRoomType.getText().toString().trim();
            boolean newBreakfast = cbBreakfast.isChecked();
            boolean newAirportPickup = cbAirportPickup.isChecked();

            // toast
            Toast.makeText(
                    EditBookingActivity.this,
                    "Booking updated:\nDate: " + newDate +
                            "\nRoom: " + newRoomType,
                    Toast.LENGTH_SHORT
            ).show();

            finish();
        });

        // discard changes
        btnCancel.setOnClickListener(v -> finish());
    }
}
