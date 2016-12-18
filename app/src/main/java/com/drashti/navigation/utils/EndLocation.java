package com.drashti.navigation.utils;

public class EndLocation {
    private final double lat;
    private final double lng;

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public EndLocation(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EndLocation that = (EndLocation) o;

        if (Double.compare(that.lat, lat) != 0) return false;
        return Double.compare(that.lng, lng) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "EndLocation{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}

