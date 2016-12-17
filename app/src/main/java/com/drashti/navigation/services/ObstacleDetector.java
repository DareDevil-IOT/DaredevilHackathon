package com.drashti.navigation.services;


import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;

public class ObstacleDetector {
    private final DataInputStream inputStream;
    private final LinkedList<Integer> windowFrame = new LinkedList<>();
    private int threshold = 100;
    private Navigator navigator = Navigator.getInstance();

    public ObstacleDetector(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }


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

    private void manageDataFrame(int datum) {
        System.out.println("Frame " + windowFrame);
        if (windowFrame.size() == 5)
            windowFrame.remove();
        windowFrame.add(datum);
        double average = averageOfWindowFrame();
        checkForObstacle(average);
    }

    private void checkForObstacle(double average) {
        System.out.println(average);

        if (average <= threshold) {
            navigator.notify(average);
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





