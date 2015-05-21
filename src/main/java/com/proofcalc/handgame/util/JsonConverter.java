package com.proofcalc.handgame.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverter {

    public static String toJson(Object outcome) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(outcome);
    }

    public static <T> T fromJson(String responseJson, Class<T> outputClass) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(responseJson, outputClass);
    }
}
