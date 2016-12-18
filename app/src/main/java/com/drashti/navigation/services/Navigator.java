package com.drashti.navigation.services;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.drashti.navigation.SpeechManipulator.Speaker;

/**
 * Created by sagarmaurya on 12/17/16.
 */
public class Navigator {
    private static Navigator navigator;
    private int distance;
    private Navigator() {
    }
    public static Navigator getInstance(){
        if(navigator == null){
            navigator = new Navigator();
        }
        return navigator;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void notify(double average) {
        distance = Math.round(average/100.0) == 0 ? (int) Math.round(average) : (int) Math.round(average / 100.0);
        if(distance == 1)
            Speaker.getInstance().speak("Obstacle detected at " + distance +" meter");
        Speaker.getInstance().speak("Obstacle detected at " + distance +" centimeters");

    }
}
