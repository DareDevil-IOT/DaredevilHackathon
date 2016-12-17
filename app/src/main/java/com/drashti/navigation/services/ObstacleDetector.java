package com.drashti.navigation.services;


import java.io.DataInputStream;

public class ObstacleDetector {
    private final DataInputStream inputStream;

    public ObstacleDetector(DataInputStream inputStream) {
        this.inputStream = inputStream;
        /*
        if(inputStream.available() > 0){
            inputStream.readInt();
        }

         */
    }
}
