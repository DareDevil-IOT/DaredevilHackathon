package com.drashti.navigation.SpeechManipulator;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;

import java.util.Locale;

public class Speaker {
    private static Speaker speaker;
    private TextToSpeech tts;

    private Speaker(Context context) {
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.US);
                String defaultEngine = tts.getDefaultEngine();
                System.out.println(defaultEngine);
            }
        });
    }

    public static Speaker getInstance(Context context) {
        if (speaker == null) {
            speaker = new Speaker(context);
        }
        return speaker;

    }

    public static Speaker getInstance() {
        return speaker;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void speak(CharSequence textToSpeak) {
        while(tts.isSpeaking()){

        }
        tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, new Bundle(), "ajsf");

    }
}
