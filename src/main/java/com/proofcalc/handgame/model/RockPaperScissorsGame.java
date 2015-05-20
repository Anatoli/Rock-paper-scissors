package com.proofcalc.handgame.model;

import java.util.Arrays;
import java.util.List;

import static com.proofcalc.handgame.model.Gesture.Paper;
import static com.proofcalc.handgame.model.Gesture.Rock;
import static com.proofcalc.handgame.model.Gesture.Scissors;

public class RockPaperScissorsGame extends ZeroSumHandGame {

    public static final List<Gesture> GESTURES = Arrays.asList(Paper, Rock, Scissors);

    @Override
    public List<Gesture> getGameChoices() {
        return GESTURES;
    }

}
