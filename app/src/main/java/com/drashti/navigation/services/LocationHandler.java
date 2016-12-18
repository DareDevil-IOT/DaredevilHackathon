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
    private int nextStepIndex;
    private long instructionTime;

    public LocationHandler(List<StepOfPath> pathSet, Context context) {
        steps = pathSet;
        this.context = context;
        speaker = Speaker.getInstance();
        nextStepIndex = 1;
        speaker.speak(steps.get(0).getHtml_instructions());
    }

    public void handle(Location location) {

        System.out.println("Next step  "+ nextStepIndex);
        if (nextStepIndex > steps.size()) {
            //to do
            return;
        }

        StepOfPath nextStep = null;
        if (nextStepIndex < steps.size()) {
            nextStep = steps.get(this.nextStepIndex);
        }
        StepOfPath currentStep = steps.get(this.nextStepIndex - 1);
        float distanceFromEnd = location.distanceTo(currentStep.endLocation());
        float distanceFromStart = location.distanceTo(currentStep.startLocation());
        float distance = currentStep.getDistance().getValue();

        System.out.println(" handle  distance " + distance + "  frm end  " + distanceFromEnd + "  frm start  " + distanceFromStart);
        if (nextStepIndex < steps.size() && nextStep.isNearStartLocation(location)) {
            System.out.println("Inside near start point");

            speaker.speak(nextStep.getHtml_instructions().replaceAll("\\<.*?>", " "));
            instructionTime = System.currentTimeMillis();
            this.nextStepIndex++;

        } else if (currentStep.isOnJourney(location) && (System.currentTimeMillis() - instructionTime) / 1000 > 3) {
            speaker.speak("Continue on same path for " + location.distanceTo(currentStep.endLocation()));
            instructionTime = System.currentTimeMillis();
        }

    }
}
