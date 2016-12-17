package com.drashti.navigation.textToSpeech;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;

import java.util.Locale;

public class Speaker {
    final TextToSpeech tts;

    public Speaker(Context context) {
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.US);
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void speak(CharSequence textToSpeak){
        tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, new Bundle(), "ajsf");
    }
}
