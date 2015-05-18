package com.proofcalc.handgame.model;

import java.util.List;

public abstract class Player {
    protected final List<Gesture> choices;

    public Player(List<Gesture> choices) {
        this.choices = choices;
    }

    public abstract Gesture getChoice();
}
