package com.proofcalc.handgame.model;

import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer(List<Gesture> choices) {
        super(choices);
    }

    @Override
    public Gesture getChoice() {
        int randomIndex = randInt(0, this.choices.size() - 1);
        return choices.get(randomIndex);
    }

    private Random rand = new Random();

    protected int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

}
