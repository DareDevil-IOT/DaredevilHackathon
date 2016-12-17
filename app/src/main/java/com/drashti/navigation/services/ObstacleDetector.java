package com.drashti.navigation.services;


import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;

public class ObstacleDetector {
    private final DataInputStream inputStream;
    private final Stack<Integer> windowFrame = new Stack();
    private int threshold = 200;
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

    private int convertToInt(int byte1, int byte2) {
        System.out.println("value 1 " + (0xFF & byte2 + (0xFF << 8) & byte1));
        return 0xFF & byte1 + (0xFF << 8) & byte2;
    }

    private void manageDataFrame(int datum) {
        if (windowFrame.size() == 5)
            windowFrame.pop();
        windowFrame.push(datum);
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





