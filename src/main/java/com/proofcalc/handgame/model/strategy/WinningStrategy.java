package com.proofcalc.handgame.model.strategy;


import com.proofcalc.handgame.model.Gesture;

import static com.proofcalc.handgame.model.Gesture.*;

public enum WinningStrategy {

    ScissorsCutsPaper(Scissors, Paper),
    PaperCoversRock(Paper, Rock),
    RockCrushesScissors(Rock, Scissors),

    RockCrushesLizard(Rock, Lizard),
    LizardPoisonsSpock(Lizard, Spock),
    SpockSmashesScissors(Spock, Scissors),
    ScissorsDecapitatesLizard(Scissors, Lizard),
    LizardEatsPaper(Lizard, Paper),
    PaperDisprovesSpock(Paper, Spock),
    SpockVaporizesRock(Spock, Rock);

    private Gesture first;
    private Gesture second;

    private WinningStrategy(Gesture first, Gesture second) {
        this.first = first;
        this.second = second;
    }

    private boolean equalByValues(Gesture first, Gesture second) {
        return this.first == first && this.second == second;
    }

    public static boolean isWinningStrategy(Gesture first, Gesture second) {
        for (WinningStrategy strategy : WinningStrategy.values()) {
            if (strategy.equalByValues(first, second))
                return true;
        }
        return false;
    }

}
