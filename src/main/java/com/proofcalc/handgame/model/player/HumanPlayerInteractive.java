package com.proofcalc.handgame.model.player;


import com.proofcalc.handgame.model.input.Gesture;
import com.proofcalc.handgame.model.input.UserInputReader;

import java.util.List;

public class HumanPlayerInteractive extends HumanPlayer {

    private UserInputReader userInputReader;

    public HumanPlayerInteractive(UserInputReader userInputReader) {
        this.userInputReader = userInputReader;
    }

    @Override
    protected Gesture getUserInput(List<Gesture> availableChoices) {
        return userInputReader.readUserChoice(availableChoices);
    }
}
