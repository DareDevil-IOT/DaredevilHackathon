package com.drashti.navigation.services;


import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;

public class ObstacleDetector {
    private final DataInputStream inputStream;
    private final Stack<Integer> windowFrame = new Stack();
    private int threshold = 200;
    private Navigator navigator = Navigator.getInstance();

    public ObstacleDetector(DataInputStream inputStream)  {
        this.inputStream = inputStream;
    }


    public void startListening() throws IOException {
        int datum = 0;
        while (true) {
            if (inputStream.available() > 0) {
                datum = inputStream.read();
                System.out.println(datum);

            }
            manageDataFrame(datum);
        }

    }

    private void manageDataFrame(int datum) {
        if (windowFrame.size() == 5)
            windowFrame.pop();
        windowFrame.push(datum);
        double average = averageOfWindowFrame();
        checkForObstacle(average);
    }

    private void checkForObstacle(double average) {
        //System.out.println(average);
        if (average <= threshold)
            navigator.notify(average);
    }

    private double averageOfWindowFrame() {
        double sum = 0;
        for (int index = 0; index < windowFrame.size(); index++) {
            sum += windowFrame.get(index);
        }
        return sum / windowFrame.size();
    }


}





