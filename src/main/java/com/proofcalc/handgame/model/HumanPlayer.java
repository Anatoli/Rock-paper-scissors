package com.proofcalc.handgame.model;

import java.util.List;

public abstract class HumanPlayer extends Player {

    @Override
    public Gesture getChoice(List<Gesture> availableChoices) {
        return getUserInput(availableChoices);
    }

    abstract protected Gesture getUserInput(List<Gesture> availableChoices);

}
