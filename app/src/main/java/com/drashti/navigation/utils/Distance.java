package com.drashti.navigation.utils;

public class Distance {
    private final String text;
    private final int value;

    public String getText() {
        return text;
    }

    public float getValue() {
        return value;
    }

    public Distance(String text, int Value) {
        this.text = text;
        this.value = Value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Distance distance = (Distance) o;

        if (value != distance.value) return false;
        return text != null ? text.equals(distance.text) : distance.text == null;

    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + value;
        return result;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "text='" + text + '\'' +
                ", value=" + value +
                '}';
    }
}
