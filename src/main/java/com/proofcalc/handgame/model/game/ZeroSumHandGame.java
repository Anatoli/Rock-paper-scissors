package com.proofcalc.handgame.model.game;

import com.proofcalc.handgame.model.input.Gesture;
import com.proofcalc.handgame.model.player.Player;

import java.util.List;

import static com.proofcalc.handgame.model.game.Outcome.LOSE;
import static com.proofcalc.handgame.model.game.Outcome.TIE;
import static com.proofcalc.handgame.model.game.Outcome.WIN;

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

    private Outcome evaluateInputs(Gesture firstThrow, Gesture secondThrow) {
        return getGameRules().evaluateThrow(firstThrow, secondThrow);
    }

    protected abstract List<Gesture> getGameChoices();

    protected abstract GameRules getGameRules();

    protected static class GameRules {
        private Outcome[][] rules;

        private GameRules(int gameSize) {
            init(gameSize);
        }

        public Outcome evaluateThrow(Gesture firstThrow, Gesture secondThrow) {
            Outcome outcome = rules[firstThrow.ordinal()][secondThrow.ordinal()];
            return outcome != null ? outcome : LOSE;
        }

        private void init(int gameSize) {
            this.rules = new Outcome[gameSize][gameSize];
            for (int i = 0; i < gameSize; i++) {
                rules[i] = new Outcome[gameSize];
                rules[i][i] = TIE;
            }
            this.rules.toString();
        }
    }

    protected static class GameRulesBuilder {
        private GameRules gameRules;

        public GameRulesBuilder setGameSize(int gameSize) {
            this.gameRules = new GameRules(gameSize);
            return this;
        }

        public GameRulesBuilder addRule(Gesture winnerGesture, Gesture defeatedGesture) {
            this.gameRules.rules[winnerGesture.ordinal()][defeatedGesture.ordinal()] = WIN;
            return this;
        }

        public GameRules build() {
            return this.gameRules;
        }


    }

}
