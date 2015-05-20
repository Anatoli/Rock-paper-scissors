package com.proofcalc.handgame.model;

import java.util.List;

public abstract class Player {

    public abstract Gesture getChoice(List<Gesture> availableChoices);

}
