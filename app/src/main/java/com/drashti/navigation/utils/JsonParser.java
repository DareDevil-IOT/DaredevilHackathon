package com.drashti.navigation.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class JsonParser {

    private final Object geocoded_waypoints;
    private final List<Route> routes;
    private final String status;

    public Object getGeocoded_waypoints() {
        return geocoded_waypoints;
    }

    public String getStatus() {
        return status;
    }

    public List<Route> getAllRoute() {
        return routes;
    }

    public JsonParser(Object geocoded_waypoints, List<Route> routes, String status) {
        this.geocoded_waypoints = geocoded_waypoints;
        this.routes = routes;
        this.status = status;
    }


    public List<Leg> getLegsBoundOfGivenIndex(int i) {
        return routes.get(i).getLegs();
    }

    public Leg getParticularLegOfGivenIndexOfRoute(int routeIndex, int legIndex) {
        return getLegsBoundOfGivenIndex(routeIndex).get(legIndex);
    }

    public ArrayList<Leg> getAllLegsARoute(int routeIndex) {
        ArrayList<Leg> legs = new ArrayList<Leg>();
        for (Leg leg : routes.get(routeIndex).getLegs()) {
            legs.add(leg);
        }
        return legs;
    }

    public HashSet<StepOfPath> getAllStep(int routerIndex, int legIndex) {
        HashSet<StepOfPath> stepOfPaths = new HashSet<StepOfPath>();
        ArrayList<Leg> allLegsARoute = getAllLegsARoute(routerIndex);
        Leg leg = allLegsARoute.get(legIndex);
        HashSet<StepOfPath> steps = leg.getSteps();
        for (StepOfPath step : steps) {
            stepOfPaths.add(step);
        }
        return stepOfPaths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonParser that = (JsonParser) o;

        if (geocoded_waypoints != null ? !geocoded_waypoints.equals(that.geocoded_waypoints) : that.geocoded_waypoints != null)
            return false;
        if (routes != null ? !routes.equals(that.routes) : that.routes != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;

    }

    @Override
    public int hashCode() {
        int result = geocoded_waypoints != null ? geocoded_waypoints.hashCode() : 0;
        result = 31 * result + (routes != null ? routes.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

}
