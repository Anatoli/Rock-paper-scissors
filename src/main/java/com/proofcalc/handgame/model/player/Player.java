package com.proofcalc.handgame.model.player;

import com.proofcalc.handgame.model.input.Gesture;

import java.util.List;

public abstract class Player {

    public abstract Gesture getChoice(List<Gesture> availableChoices);

}
