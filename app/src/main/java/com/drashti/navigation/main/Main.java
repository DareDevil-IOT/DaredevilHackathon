package com.drashti.navigation.main;

import com.drashti.navigation.directionApi.DirectionRetriever;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=13.056470,80.258935&destination=13.056864,80.259909&departure_time=1541202457&traffic_model=best_guess&language=english&mode=walking&region=ES&key=AIzaSyCWh8_CLW_xT2o8eUTfjF6TGNVvJtmAWNQ";
        DirectionRetriever retrieveDirection = new DirectionRetriever();
        retrieveDirection.downloadUrl(url);
    }
}
