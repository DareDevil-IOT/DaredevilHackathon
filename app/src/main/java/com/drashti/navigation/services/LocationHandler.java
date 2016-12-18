package com.drashti.navigation.services;


import android.content.Context;
import android.location.Location;

import com.drashti.navigation.SpeechManipulator.Speaker;
import com.drashti.navigation.utils.StepOfPath;

import java.util.List;

public class LocationHandler {
    private final Speaker speaker;
    private List<StepOfPath> steps;
    private Context context;
    private int nextStep;
    private long instructionTime;

    public LocationHandler(List<StepOfPath> pathSet, Context context) {
        steps = pathSet;
        this.context = context;
        speaker = Speaker.getInstance();
        nextStep = 1;
        speaker.speak(steps.get(0).getHtml_instructions());
    }

    public void handle(Location location) {
        System.out.println("Next step  "+nextStep);
        if (nextStep >= steps.size()) {
            //to do
            return;
        }

        StepOfPath nextStep = steps.get(this.nextStep);
        StepOfPath currentStep = steps.get(this.nextStep - 1);
        float distanceFromStart = location.distanceTo(currentStep.endLocation());
        float distanceFromEnd = location.distanceTo(currentStep.startLocation());
        float distance = currentStep.getDistance().getValue();

        System.out.println(" handle  distance " + distance + "  frm end  " + distanceFromEnd + "  frm start  " + distanceFromStart);
        if (nextStep.isNearStartLocation(location)) {
            System.out.println("Inside near start point");

            speaker.speak(nextStep.getHtml_instructions().replaceAll("\\<.*?>", " "));
            instructionTime = System.currentTimeMillis();
            this.nextStep++;

        } else if (currentStep.isOnJourney(location) && (System.currentTimeMillis() - instructionTime) / 1000 > 3) {
            speaker.speak("Continue on same path for " + location.distanceTo(currentStep.endLocation()));
            instructionTime = System.currentTimeMillis();
        }

    }
}
