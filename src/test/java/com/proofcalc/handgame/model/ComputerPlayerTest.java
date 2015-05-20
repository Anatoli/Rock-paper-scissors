package com.proofcalc.handgame.model;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ComputerPlayerTest {

    @Test
    public void shouldReturnRandomChoices() {
        List<Gesture> gestures = RockPaperScissorsGame.GESTURES;
        ComputerPlayer computerPlayer = new ComputerPlayer();
        Map<Gesture, Integer> counter = initCounter(gestures);
        int sampleCount = 10000;
        for (int i = 0; i < sampleCount; i++) {
            Gesture gesture = computerPlayer.getChoice(gestures);
            counter.put(gesture, counter.get(gesture) + 1);
        }

        for (Gesture gesture : gestures) {
            double gestureHitsPct = ((double) counter.get(gesture) / sampleCount) * 100;
            assertTrue("Hits should have linear distribution, between 30 & 36% (33.33)",
                    gestureHitsPct > 30 && gestureHitsPct < 36);
        }

    }

    private Map<Gesture, Integer> initCounter(List<Gesture> gestures) {
        Map<Gesture, Integer> counter = new HashMap();
        for (Gesture gesture : gestures) {
            counter.put(gesture, 0);
        }
        return counter;
    }
}
