package com.proofcalc.handgame.model.input;

import java.util.List;

public class DefaultUserInputReader implements UserInputReader {

    @Override
    public Gesture readUserChoice(List<Gesture> availableChoices) {
        return availableChoices.get(0);
    }
}
