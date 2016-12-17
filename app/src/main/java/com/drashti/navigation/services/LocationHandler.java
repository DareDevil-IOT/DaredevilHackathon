package com.drashti.navigation.services;


import android.location.Location;

import com.drashti.navigation.SpeechManipulator.Speaker;
import com.drashti.navigation.utils.JsonParser;
import com.drashti.navigation.utils.JsonReader;

import com.drashti.navigation.utils.StepOfPath;

import java.util.List;
import java.util.Set;

public class LocationHandler {
    private final Speaker speaker;
    private List<StepOfPath> steps;

    public LocationHandler(List<StepOfPath> pathSet) {
        steps = pathSet;
        speaker = Speaker.getInstance();
    }

    public void handle(Location location) {
        StepOfPath step;
        for (int stepIndex = 0; stepIndex < steps.size(); stepIndex++) {
            step = steps.get(stepIndex);
            if (steps.get(stepIndex).getHtml_instructions().toLowerCase().contains("turn")) {
                if (isTurnTaken(step.getHtml_instructions())) {
                    step.setStepComplete(true);
                    continue;
                }

            }
            if (step.isNearStartLocation(location)) {
                System.out.println("start speaking:- " + step.getHtml_instructions());
                speaker.speak(step.getHtml_instructions());
            }


        }

    }

    private boolean isTurnTaken(String instruction) {
        return false;
    }
}
