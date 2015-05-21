package com.proofcalc.handgame.model.game;

import com.proofcalc.handgame.model.input.Gesture;

import java.util.Arrays;
import java.util.List;

import static com.proofcalc.handgame.model.input.Gesture.Paper;
import static com.proofcalc.handgame.model.input.Gesture.Rock;
import static com.proofcalc.handgame.model.input.Gesture.Scissors;

public class RockPaperScissorsGame extends ZeroSumHandGame {

    public static final List<Gesture> GESTURES = Arrays.asList(Paper, Rock, Scissors);

    private static final GameRules GAME_RULES = new GameRulesBuilder()
            .setGameSize(GESTURES.size())
            .addRule(Scissors, Paper)
            .addRule(Paper, Rock)
            .addRule(Rock, Scissors)
            .build();

    @Override
    public List<Gesture> getGameChoices() {
        return GESTURES;
    }

    @Override
    protected GameRules getGameRules() {
        return GAME_RULES;
    }
}
