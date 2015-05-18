package com.proofcalc.handgame.model;

import java.util.List;

public class HumanPlayer extends Player {

    public HumanPlayer(List<Gesture> choices) {
        super(choices);
    }

    @Override
    public Gesture getChoice() {
        return getUserInput();
    }

    protected Gesture getUserInput() {
        return choices.get(0);
    }

}
