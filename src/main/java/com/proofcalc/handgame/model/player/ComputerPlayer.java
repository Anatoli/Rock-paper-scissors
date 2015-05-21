package com.proofcalc.handgame.model.player;

import com.proofcalc.handgame.model.input.Gesture;

import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {

    @Override
    public Gesture getChoice(List<Gesture> availableChoices) {
        int randomIndex = randInt(0, availableChoices.size() - 1);
        return availableChoices.get(randomIndex);
    }

    private Random rand = new Random();

    protected int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

}
