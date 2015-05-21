package com.proofcalc.handgame.model.input;

import java.util.List;

public interface UserInputReader {

    Gesture readUserChoice(List<Gesture> availableChoices);

}
