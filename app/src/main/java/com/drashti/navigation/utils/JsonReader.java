package com.drashti.navigation.utils;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonReader {

    public static JsonParser parseJson(Reader reader) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(reader, JsonParser.class);
    }

}
