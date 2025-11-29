// Hotel.java (model class)
package com.example.myapplication;

public class Hotel {
    private String name, location, description;
    private double price;
    private float rating;
    private int imageResId;   // each hotel has image

    public Hotel(String name,
                 String location,
                 double price,
                 float rating,
                 String description,
                 int imageResId) {

        this.name = name;
        this.location = location;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public double getPrice() { return price; }
    public float getRating() { return rating; }
    public String getDescription() { return description; }
    public int getImageResId() { return imageResId; }
}
