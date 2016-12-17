package com.drashti.navigation.services;


import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import com.drashti.navigation.SpeechManipulator.Speaker;
import com.drashti.navigation.utils.StepOfPath;

import java.util.List;

public class LocationHandler{
    private final Speaker speaker;
    private List<StepOfPath> steps;
    private Context context;

    public LocationHandler(List<StepOfPath> pathSet, Context context) {
        steps = pathSet;
        this.context = context;
        speaker = Speaker.getInstance();
    }

    public void handle(Location location) {
        System.out.println(steps);
        for (StepOfPath step : steps) {

            while (!step.isStepComplete()) {
                float distanceFromStart = step.endLocation().distanceTo(location);
                float distanceFromEnd = step.startLocation().distanceTo(location);
                float distance = step.getDistance().getValue();
                System.out.println("distance " + distance + "  frm end  " + distanceFromEnd + "  frm start  " + distanceFromStart);
//                if (distanceFromStart > 0 && distanceFromEnd < distance) {
//                    System.out.println("start speaking..");
//                    speaker.speak(step.getHtml_instructions().replaceAll("\\<.*?>", " "));
//                } else {
//                    speaker.speak("I think, you are heading in wrong direction.");
//                }
                if (step.isNearEndLocation(location)) {
                    speaker.speak("Acheived current step ");
                    step.setStepComplete(true);
                }
            }

//
//            if (step.isNearStartLocation(location)) {
//                System.out.println("start speaking:- " + step.getHtml_instructions().replaceAll("\\<.*?>", " "));
//                speaker.speak(step.getHtml_instructions().replaceAll("\\<.*?>", " "));
//            }
        }

    }
}
