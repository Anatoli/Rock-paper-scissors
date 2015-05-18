package com.proofcalc.handgame.model;

import com.proofcalc.handgame.model.Gesture;
import com.proofcalc.handgame.model.strategy.Outcome;

public class GameOutcome {
    public final Gesture player1Choice;
    public final Gesture player2Choice;
    public final Outcome outcome;

    public GameOutcome(Gesture player1Choice, Gesture player2Choice, Outcome outcome) {
        this.player1Choice = player1Choice;
        this.player2Choice = player2Choice;
        this.outcome = outcome;
    }
}
