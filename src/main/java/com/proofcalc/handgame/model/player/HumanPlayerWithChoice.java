package com.proofcalc.handgame.model.player;

import com.proofcalc.handgame.model.input.Gesture;

import java.util.List;

public class HumanPlayerWithChoice extends HumanPlayer {

    private Gesture choice;

    public HumanPlayerWithChoice(Gesture choice) {
        this.choice = choice;
    }

    @Override
    protected Gesture getUserInput(List<Gesture> availableChoices) {
        return choice;
    }
}
