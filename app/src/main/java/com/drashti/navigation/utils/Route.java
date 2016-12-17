package com.drashti.navigation.utils;

import java.util.List;

public class Route {
    private final Object bounds;
    private final Object copyrights;
    private final List<Leg> legs;
    private final Object waypoint_order;

    public Object getBounds() {
        return bounds;
    }

    public Object getCopyrights() {
        return copyrights;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public Object getWaypoint_order() {
        return waypoint_order;
    }

    public Route(Object bounds, Object copy_rights, List<Leg> legs, Object waypoint_order) {
        this.bounds = bounds;
        this.copyrights = copy_rights;
        this.legs = legs;
        this.waypoint_order = waypoint_order;
    }


}
