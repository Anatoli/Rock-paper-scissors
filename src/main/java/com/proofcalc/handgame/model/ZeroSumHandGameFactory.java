package com.proofcalc.handgame.model;

import com.proofcalc.handgame.exception.ZeroSumHandGameException;

import java.util.List;

import static com.proofcalc.handgame.model.GameType.RockPaperScissors;
import static com.proofcalc.handgame.model.PlayerConfig.ComputerVsComputer;
import static com.proofcalc.handgame.model.PlayerConfig.HumanVsComputer;

public class ZeroSumHandGameFactory {

    public static ZeroSumHandGame getGameBy(GameConfig gameConfig) {

        List<Gesture> gameChoices = gameConfig.getGameType() == RockPaperScissors ? RockPaperScissorsGame.GESTURES :
                RockPaperScissorsLizardSpockGame.GESTURES;
        Player player1 = assembleMainPlayer(gameConfig, gameChoices);
        Player player2 = assembleVsPlayer(gameChoices);

        switch (gameConfig.getGameType()) {
            case RockPaperScissors:
                return new RockPaperScissorsGame(player1, player2);
            case RockPaperScissorsLizardSpock:
                return new RockPaperScissorsLizardSpockGame(player1, player2);
            default:
                throw new ZeroSumHandGameException("Not supported game: " + gameConfig.getGameType().name());
        }
    }

    private static Player assembleMainPlayer(final GameConfig gameConfig, final List<Gesture> choices) {
        if (gameConfig.getPlayerConfig() == ComputerVsComputer) {
            return new ComputerPlayer(choices);
        } else {
            if (gameConfig.hasUserChoice()) {
                return new HumanPlayer(choices) {
                    @Override
                    protected Gesture getUserInput() {
                        return gameConfig.getUserChoice();
                    }
                };
            } else {
                new HumanPlayer(choices);
            }
        }
        return gameConfig.getPlayerConfig() == HumanVsComputer ? new HumanPlayer(choices) : new ComputerPlayer(choices);
    }

    private static Player assembleVsPlayer(List<Gesture> choices) {
        return new ComputerPlayer(choices);
    }

}
