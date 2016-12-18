package com.drashti.navigation.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import com.drashti.navigation.services.LocationHandler;


public class GpsListener implements LocationListener {
    private LocationHandler locationHandler;
    private Context mContext;

    public GpsListener(LocationHandler locationHandler, Context mContext) {
        this.locationHandler = locationHandler;
        this.mContext = mContext;
    }

    @Override
    public void onLocationChanged(Location location) {
        System.out.println("location is  "+location);
        Toast.makeText(mContext, "Your Location is - \nLat: " + location.getLatitude() + "\nLong: " + location.getLongitude(), Toast.LENGTH_LONG).show();
        locationHandler.handle(location);    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
