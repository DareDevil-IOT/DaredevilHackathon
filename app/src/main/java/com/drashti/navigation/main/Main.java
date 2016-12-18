package com.drashti.navigation.main;

import com.drashti.navigation.directionApi.DirectionRetriever;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=13.0565149,80.2590649&destination=13.0568275,80.2599098&departure_time=1541202457&traffic_model=best_guess&language=english&mode=walking&region=ES&key=AIzaSyCWh8_CLW_xT2o8eUTfjF6TGNVvJtmAWNQ";
        DirectionRetriever retrieveDirection = new DirectionRetriever();
        retrieveDirection.downloadUrl(url);
    }
}
