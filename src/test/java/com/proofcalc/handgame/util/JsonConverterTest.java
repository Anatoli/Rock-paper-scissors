package com.proofcalc.handgame.util;

import com.proofcalc.handgame.model.game.GameOutcome;
import com.proofcalc.handgame.model.input.Gesture;
import com.proofcalc.handgame.model.game.Outcome;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonConverterTest {

    @Test
    public void convertsGameOutputToJson() {
        assertEquals("{\"player1Choice\":\"Paper\",\"player2Choice\":\"Rock\",\"outcome\":\"WIN\"}",
                JsonConverter.toJson(new GameOutcome(Gesture.Paper, Gesture.Rock, Outcome.WIN)));
    }
}
