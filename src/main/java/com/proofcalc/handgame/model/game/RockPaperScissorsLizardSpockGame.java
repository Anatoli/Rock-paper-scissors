package com.proofcalc.handgame.model.game;

import com.proofcalc.handgame.model.input.Gesture;

import java.util.Arrays;
import java.util.List;

import static com.proofcalc.handgame.model.input.Gesture.*;

public class RockPaperScissorsLizardSpockGame extends ZeroSumHandGame {

    public static final List<Gesture> GESTURES = Arrays.asList(Paper, Rock, Scissors, Lizard, Spock);

    private static final GameRules GAME_RULES = new GameRulesBuilder()
            .setGameSize(GESTURES.size())
            .addRule(Scissors, Paper)
            .addRule(Paper, Rock)
            .addRule(Rock, Scissors)
            .addRule(Rock, Lizard)
            .addRule(Lizard, Spock)
            .addRule(Spock, Scissors)
            .addRule(Scissors, Lizard)
            .addRule(Lizard, Paper)
            .addRule(Paper, Spock)
            .addRule(Spock, Rock)
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
