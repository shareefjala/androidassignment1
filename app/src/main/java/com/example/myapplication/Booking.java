// model for booking a hotel
package com.example.myapplication;

public class Booking {
    private final int imageResId;
    private String hotelName, date;
    private boolean breakfast, airportPickup;
    private String roomType;

    public Booking(String hotelName, String date, boolean breakfast, String roomType, boolean airportPickup, int imageResId) {
        this.hotelName = hotelName;
        this.date = date;
        this.breakfast = breakfast;
        this.roomType = roomType;
        this.airportPickup = airportPickup;
        this.imageResId = imageResId;
    }
    public String getHotelName() { return hotelName; }
    public int getImageResId() { return imageResId; }
    public String getDate() { return date; }
    public boolean hasBreakfast() { return breakfast; }
    public String getRoomType() { return roomType; }
    public boolean isAirportPickup() { return airportPickup; }
}
