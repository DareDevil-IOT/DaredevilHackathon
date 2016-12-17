package com.drashti.navigation.utils;

public class Duration {
    private final String text;
    private final int value;

    public String getText() {
        return text;
    }

    public int getValue() {
        return value;
    }

    public Duration(String text, int value) {
        this.text = text;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Duration duration = (Duration) o;

        if (value != duration.value) return false;
        return text != null ? text.equals(duration.text) : duration.text == null;

    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + value;
        return result;
    }
}
