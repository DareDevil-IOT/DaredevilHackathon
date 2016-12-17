package com.drashti.navigation.services;

/**
 * Created by sagarmaurya on 12/17/16.
 */
public class Navigator {
    private static Navigator navigator;
    private Navigator() {
    }
    public static Navigator getInstance(){
        if(navigator == null){
            navigator = new Navigator();
        }
        return navigator;
    }

    public void notify(double average) {
        System.out.println("notified----------------------");
    }
}
