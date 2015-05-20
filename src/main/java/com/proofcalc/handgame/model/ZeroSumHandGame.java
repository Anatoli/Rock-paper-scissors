package com.proofcalc.handgame.model;

import com.proofcalc.handgame.model.strategy.Outcome;
import com.proofcalc.handgame.model.strategy.WinningStrategy;

import java.util.List;

import static com.proofcalc.handgame.model.strategy.Outcome.LOSE;
import static com.proofcalc.handgame.model.strategy.Outcome.TIE;
import static com.proofcalc.handgame.model.strategy.Outcome.WIN;

abstract public class ZeroSumHandGame {
    private Player player1;
    private Player player2;

    public ZeroSumHandGame() {
    }

    public ZeroSumHandGame setPlayer1(Player player1) {
        this.player1 = player1;
        return this;
    }

    public ZeroSumHandGame setPlayer2(Player player2) {
        this.player2 = player2;
        return this;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public GameOutcome play() {
        List<Gesture> choiceOptions = getGameChoices();
        Gesture player1Choice = player1.getChoice(choiceOptions);
        Gesture player2Choice = player2.getChoice(choiceOptions);
        Outcome outcome = evaluateInputs(player1Choice, player2Choice);

        return new GameOutcome(player1Choice, player2Choice, outcome);
    }

    private Outcome evaluateInputs(Gesture player1Choice, Gesture player2Choice) {
        if (player1Choice == player2Choice) return TIE;

        else if (WinningStrategy.isWinningStrategy(player1Choice, player2Choice)) return WIN;

        else return LOSE;
    }

    protected abstract List<Gesture> getGameChoices();

}
