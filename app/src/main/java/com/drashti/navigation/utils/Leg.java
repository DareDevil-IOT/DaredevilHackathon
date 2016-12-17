package com.drashti.navigation.utils;

import java.util.HashSet;
import java.util.List;

public class Leg {
    private final Distance distance;
    private final Duration duration;
    private final String end_address;
    private final EndLocation end_location;
    private final String start_address;
    private final StartLocation start_location;
    private final List<StepOfPath> steps;

    public Distance getDistance() {
        return distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getEnd_address() {
        return end_address;
    }

    public EndLocation getEnd_location() {
        return end_location;
    }

    public String getStart_address() {
        return start_address;
    }

    public StartLocation getStart_location() {
        return start_location;
    }

    public List<StepOfPath> getSteps() {
        return steps;
    }


    public Leg(Distance distance,
               Duration duration,
               String end_address,
               EndLocation end_location,
               String start_address,
               StartLocation start_location,
               List<StepOfPath> steps) {


        this.distance = distance;
        this.duration = duration;
        this.end_address = end_address;
        this.end_location = end_location;
        this.start_address = start_address;
        this.start_location = start_location;
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Leg leg = (Leg) o;

        if (distance != null ? !distance.equals(leg.distance) : leg.distance != null) return false;
        if (duration != null ? !duration.equals(leg.duration) : leg.duration != null) return false;
        if (end_address != null ? !end_address.equals(leg.end_address) : leg.end_address != null) return false;
        if (end_location != null ? !end_location.equals(leg.end_location) : leg.end_location != null) return false;
        if (start_address != null ? !start_address.equals(leg.start_address) : leg.start_address != null) return false;
        if (start_location != null ? !start_location.equals(leg.start_location) : leg.start_location != null)
            return false;
        return steps != null ? steps.equals(leg.steps) : leg.steps == null;

    }

    @Override
    public int hashCode() {
        int result = distance != null ? distance.hashCode() : 0;
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (end_address != null ? end_address.hashCode() : 0);
        result = 31 * result + (end_location != null ? end_location.hashCode() : 0);
        result = 31 * result + (start_address != null ? start_address.hashCode() : 0);
        result = 31 * result + (start_location != null ? start_location.hashCode() : 0);
        result = 31 * result + (steps != null ? steps.hashCode() : 0);
        return result;
    }
}
