package com.drashti.navigation.services;


import android.location.Location;

import com.drashti.navigation.textToSpeech.Speaker;
import com.drashti.navigation.utils.JsonParser;
import com.drashti.navigation.utils.JsonReader;
import com.drashti.navigation.utils.StepOfPath;

import java.io.IOException;
import java.util.Set;

public class LocationHandler {
    private Set<StepOfPath> steps;
    private final Speaker speaker;

    public LocationHandler() {
        JsonReader reader = new JsonReader();
        try {
            reader.read("/Users/sarveshjain/Desktop/IOT/SPI/DaredevilHackathon/app/src/main/java/data/spi_path.json");
            JsonParser jsonParser = reader.parseJson();
            steps = jsonParser.getAllStep(0, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        speaker = Speaker.getInstance();
    }

    public void handle(Location location) {
        for (StepOfPath step : steps) {
            if (step.isNearStartLocation(location)) {
                speaker.speak(step.getHtml_instructions());
            }
        }

    }
}
