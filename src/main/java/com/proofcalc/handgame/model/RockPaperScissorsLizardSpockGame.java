package com.proofcalc.handgame.model;

import java.util.Arrays;
import java.util.List;

import static com.proofcalc.handgame.model.Gesture.*;

public class RockPaperScissorsLizardSpockGame extends ZeroSumHandGame {

    public static final List<Gesture> GESTURES = Arrays.asList(Paper, Rock, Scissors, Lizard, Spock);

    public RockPaperScissorsLizardSpockGame(Player player1, Player player2) {
        super(player1, player2);
    }

}
