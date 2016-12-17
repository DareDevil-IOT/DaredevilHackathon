package com.drashti.navigation.services;

import com.drashti.navigation.textToSpeech.Speaker;

/**
 * Created by sagarmaurya on 12/17/16.
 */
public class Navigator {
    private static Navigator navigator;
    private Navigator() {
    }
    public static Navigator getInstance(){
        if(navigator == null){
            navigator = new Navigator();
        }
        return navigator;
    }

    public void notify(double average) {
        Speaker.getInstance().speak("Obstacle detected at " + average/100.0);
    }
}