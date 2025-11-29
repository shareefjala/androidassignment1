package com.example.myapplication;

public class BookingItem {

    public String hotel, date, roomType;
    public boolean breakfast, airport;

    public BookingItem(String hotel, String date, String roomType, boolean breakfast, boolean airport) {
        this.hotel = hotel;
        this.date = date;
        this.roomType = roomType;
        this.breakfast = breakfast;
        this.airport = airport;
    }
}
