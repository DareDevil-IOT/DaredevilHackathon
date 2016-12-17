package com.drashti.navigation.utils;

import android.location.Location;

public class StepOfPath {
    private final Distance distance;
    private final Duration duration;
    private final EndLocation end_location;
    private final String html_instructions;
    private final StartLocation start_location;
    private final String travel_mode;
    private boolean isStepComplete;

    public Distance getDistance() {
        return distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public EndLocation getEnd_location() {
        return end_location;
    }

    public String getHtml_instructions() {
        return html_instructions;
    }

    public StartLocation getStart_location() {
        return start_location;
    }

    public String getTravel_mode() {
        return travel_mode;
    }

    public StepOfPath(Distance distance,
                      Duration duration,
                      EndLocation end_location,
                      String html_instructions,
                      StartLocation start_location,
                      String travel_mode) {

        this.distance = distance;
        this.duration = duration;
        this.end_location = end_location;
        this.html_instructions = html_instructions;
        this.start_location = start_location;
        this.travel_mode = travel_mode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StepOfPath that = (StepOfPath) o;

        if (distance != null ? !distance.equals(that.distance) : that.distance != null)
            return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null)
            return false;
        if (end_location != null ? !end_location.equals(that.end_location) : that.end_location != null)
            return false;
        if (html_instructions != null ? !html_instructions.equals(that.html_instructions) : that.html_instructions != null)
            return false;
        if (start_location != null ? !start_location.equals(that.start_location) : that.start_location != null)
            return false;
        return travel_mode != null ? travel_mode.equals(that.travel_mode) : that.travel_mode == null;

    }

    @Override
    public int hashCode() {
        int result = distance != null ? distance.hashCode() : 0;
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (end_location != null ? end_location.hashCode() : 0);
        result = 31 * result + (html_instructions != null ? html_instructions.hashCode() : 0);
        result = 31 * result + (start_location != null ? start_location.hashCode() : 0);
        result = 31 * result + (travel_mode != null ? travel_mode.hashCode() : 0);
        return result;
    }

    public boolean isNearStartLocation(Location location) {
        Location startLocation = new Location("");
        startLocation.setLatitude(start_location.getLat());
        startLocation.setLongitude(start_location.getLng());
        return location.distanceTo(startLocation) < 1f;
    }

    public boolean isNearEndLocation(Location location) {
        Location endLocation = new Location("");
        endLocation.setLatitude(end_location.getLat());
        endLocation.setLongitude(end_location.getLng());
        return location.distanceTo(endLocation) < 1f;
    }


    public boolean isStepComplete() {
        return isStepComplete;
    }

    public void setStepComplete(boolean stepComplete) {
        isStepComplete = stepComplete;
    }

}
