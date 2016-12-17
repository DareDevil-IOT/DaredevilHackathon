package com.drashti.navigation.services;


import android.location.Location;

import java.util.Set;

public class LocationHandler {
    private Set<Step> steps;

    public LocationHandler(Set<Step> steps) {
        this.steps = steps;
    }

    public void handle(Location location) {
        for (Step step : steps) {
            if(step.isNearStartLocation(location)){

            }
        }

    }
}
