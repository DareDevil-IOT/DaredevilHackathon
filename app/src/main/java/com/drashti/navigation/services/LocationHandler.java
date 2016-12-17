package com.drashti.navigation.services;


import android.location.Location;

import com.drashti.navigation.SpeechManipulator.Speaker;
import com.drashti.navigation.utils.JsonParser;
import com.drashti.navigation.utils.JsonReader;

import com.drashti.navigation.utils.StepOfPath;

import java.util.Set;

public class LocationHandler {
    private final Speaker speaker;
    private Set<StepOfPath> steps;

    public LocationHandler(Set<StepOfPath> pathSet) {
        steps = pathSet;
        speaker = Speaker.getInstance();
    }

    public void handle(Location location) {
        for (StepOfPath step : steps) {
            if (step.isNearStartLocation(location)) {
                System.out.println("start speaking:- "+step.getHtml_instructions());
                speaker.speak(step.getHtml_instructions());
            }
        }

    }
}
