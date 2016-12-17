package com.drashti.navigation.services;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.drashti.navigation.SpeechManipulator.Speaker;

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void notify(double average) {
        Speaker.getInstance().speak("Obstacle detected at " + Math.round(average/100.0)+" meters");
    }
}
