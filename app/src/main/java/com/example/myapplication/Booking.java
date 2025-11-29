// Booking.java (model for a booking record)
package com.example.myapplication;

public class Booking {
    private String hotelName, date;
    private boolean breakfast, airportPickup;
    private String roomType;

    public Booking(String hotelName, String date, boolean breakfast, String roomType, boolean airportPickup) {
        this.hotelName = hotelName;
        this.date = date;
        this.breakfast = breakfast;
        this.roomType = roomType;
        this.airportPickup = airportPickup;
    }
    public String getHotelName() { return hotelName; }
    public String getDate() { return date; }
    public boolean hasBreakfast() { return breakfast; }
    public String getRoomType() { return roomType; }
    public boolean isAirportPickup() { return airportPickup; }
}
