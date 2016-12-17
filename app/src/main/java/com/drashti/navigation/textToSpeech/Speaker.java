package com.drashti.navigation.textToSpeech;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import java.util.List;
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

    public static Speaker getInstance(Context context){
        if(speaker == null) {
            speaker = new Speaker(context);
        }
        return speaker;

    }

    public static Speaker getInstance(){
        return speaker;
    }


    public void speak(CharSequence textToSpeak) {
        tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, new Bundle(), "ajsf");
        while (tts.isSpeaking()){
            System.out.println("Do something or nothing while speaking..");
        }
    }
}
