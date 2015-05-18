package com.proofcalc.handgame.model;

import com.proofcalc.handgame.model.strategy.Outcome;
import com.proofcalc.handgame.model.strategy.WinningStrategy;

import static com.proofcalc.handgame.model.strategy.Outcome.LOSE;
import static com.proofcalc.handgame.model.strategy.Outcome.TIE;
import static com.proofcalc.handgame.model.strategy.Outcome.WIN;

abstract public class ZeroSumHandGame {
    public final Player player1;
    public final Player player2;

    public ZeroSumHandGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public GameOutcome play() {
        Gesture player1Choice = player1.getChoice();
        Gesture player2Choice = player2.getChoice();
        Outcome outcome = evaluateInputs(player1Choice, player2Choice);

        return new GameOutcome(player1Choice, player2Choice, outcome);
    }

    private Outcome evaluateInputs(Gesture player1Choice, Gesture player2Choice) {
        if (player1Choice == player2Choice) return TIE;

        else if (WinningStrategy.isWinningStrategy(player1Choice, player2Choice)) return WIN;

        else return LOSE;
    }

}
