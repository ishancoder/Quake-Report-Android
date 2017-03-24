package com.example.android.quakereport;

import android.net.Uri;

/**
 * Created by ishan on 24-07-2016.
 */
public class Earthquake {
    private String place;
    private double magnitude;
    private long time;
    private Uri websiteUrl;

    public Earthquake(String place, double magnitude, long time, Uri url) {
        this.place = place;
        this.magnitude = magnitude;
        this.time = time;
        this.websiteUrl = url;
    }

    public long getTime() {
        return time;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Earthquake && ((Earthquake) o).magnitude == this.magnitude
                    && ((Earthquake) o).time == (this.time)
                    && ((Earthquake) o).place.equals(this.place);
    }

    @Override
    public String toString() {
        return "Earthquake :- Place: " + place + ", Mag: " + magnitude + ", Time: " + time;
    }

    public Uri getWebsiteUrl() {
        return websiteUrl;
    }
}
