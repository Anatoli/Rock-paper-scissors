package com.proofcalc.handgame.model.input;

import com.proofcalc.handgame.model.Gesture;

import java.util.List;

public interface UserInputReader {

    Gesture readUserChoice(List<Gesture> availableChoices);

}
