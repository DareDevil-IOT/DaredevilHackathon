package com.drashti.navigation.utils;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    private String directions;

    public JsonReader() {
        this.directions = null;
    }

    public void read(String path) throws IOException {
        com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();
        this.directions = jsonParser.parse(new FileReader(path)).toString();
    }

    public JsonParser parseJson() throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(directions, JsonParser.class);
    }

}
