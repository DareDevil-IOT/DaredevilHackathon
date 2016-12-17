package com.drashti.navigation.services;


import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;

public class ObstacleDetector {
    public static final int WINDOW_SIZE = 3;
    private final DataInputStream inputStream;
    private final LinkedList<Integer> windowFrame = new LinkedList<>();
    private int threshold = 100;
    private Navigator navigator = Navigator.getInstance();
    private boolean isObstacleDetected = false;
    private long timer = 0;

    public ObstacleDetector(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startListening() throws IOException {
        int datum = 0;
        String dataStream = null;
        while (true) {
            if (inputStream.available() > 0) {
                try {
                    dataStream = inputStream.readLine().trim();
                    datum = Integer.parseInt(dataStream);
                    System.out.println(datum);
                    manageDataFrame(datum);
                } catch (NumberFormatException e) {
                    System.out.println(dataStream);
                    System.out.println(e.getStackTrace());
                }
            }

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void manageDataFrame(int datum) {
        System.out.println("Frame " + windowFrame);
        if (windowFrame.size() == WINDOW_SIZE)
            windowFrame.remove();
        windowFrame.add(datum);
        double average = averageOfWindowFrame();
        checkForObstacle(average);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkForObstacle(double average) {
        System.out.println(average);

        if (average <= threshold) {
            if (isObstacleDetected != true) {
                navigator.notify(average);
                timer = 0;
                isObstacleDetected = true;
            } else {
                System.out.println("obstacle already detected");
                if (timer == 0) {
                    timer = System.currentTimeMillis();
                } else {
                    int timeDiff = (int) ((System.currentTimeMillis() - timer) / 1000);
                    System.out.println("time diff  "+timeDiff);
                    if (timeDiff > 5) {
                        navigator.notify(average);
                        timer = 0;
                    }
                }

            }
            isObstacleDetected = true;
        }

    }

    private double averageOfWindowFrame() {
        double sum = 0;
        for (int index = 0; index < windowFrame.size(); index++) {
            sum += windowFrame.get(index);
        }
        return sum / windowFrame.size();
    }


}





