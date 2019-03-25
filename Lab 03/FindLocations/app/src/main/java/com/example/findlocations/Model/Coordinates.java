package com.example.findlocations.Model;

public class Coordinates {

    private double longitude;
    private double latitude;
    public Coordinates()
    {
        this.longitude = 0.0;
        this.latitude = 0.0;
    }

    public Coordinates(double longitude, double latitude)
    {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
