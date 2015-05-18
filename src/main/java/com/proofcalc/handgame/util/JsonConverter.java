package com.proofcalc.handgame.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proofcalc.handgame.model.GameOutcome;

public class JsonConverter {

    public static String toJson(GameOutcome outcome) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(outcome);
    }
}
