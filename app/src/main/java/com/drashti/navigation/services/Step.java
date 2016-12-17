package com.drashti.navigation.services;

import android.location.Location;

public class Step {
    Location startLocation;
    Location endLocation;
    String message;

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isNearStartLocation(Location location) {
        return (startLocation.distanceTo(location)<1f);
    }
}
