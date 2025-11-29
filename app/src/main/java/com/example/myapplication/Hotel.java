// Hotel.java (model class)
package com.example.myapplication;

public class Hotel {
    private String name, location, description;
    private double price;
    private float rating;

    public Hotel(String name, String location, double price, float rating, String description) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.rating = rating;
        this.description = description;
    }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public double getPrice() { return price; }
    public float getRating() { return rating; }
    public String getDescription() { return description; }
}
