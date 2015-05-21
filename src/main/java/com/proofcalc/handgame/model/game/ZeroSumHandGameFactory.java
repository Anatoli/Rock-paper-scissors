package com.proofcalc.handgame.model.game;

import com.proofcalc.handgame.exception.ZeroSumHandGameException;
import com.proofcalc.handgame.model.input.DefaultUserInputReader;
import com.proofcalc.handgame.model.input.Gesture;
import com.proofcalc.handgame.model.player.ComputerPlayer;
import com.proofcalc.handgame.model.player.HumanPlayerInteractive;
import com.proofcalc.handgame.model.player.HumanPlayerWithChoice;
import com.proofcalc.handgame.model.player.Player;

import java.util.List;

import static com.proofcalc.handgame.model.player.PlayerConfig.ComputerVsComputer;

public class ZeroSumHandGameFactory {

    public static ZeroSumHandGame getGameBy(GameConfig gameConfig) {
        ZeroSumHandGame game;
        switch (gameConfig.getGameType()) {
            case RockPaperScissors:
                game = new RockPaperScissorsGame();
                break;
            case RockPaperScissorsLizardSpock:
                game = new RockPaperScissorsLizardSpockGame();
                break;
            default:
                throw new ZeroSumHandGameException("Not supported game: " + gameConfig.getGameType().name());
        }

        List<Gesture> gameChoices = game.getGameChoices();
        return game
                .setPlayer1(assembleMainPlayer(gameConfig, gameChoices))
                .setPlayer2(assembleVsPlayer(gameConfig, gameChoices));

    }

    private static Player assembleMainPlayer(final GameConfig gameConfig, List<Gesture> gameChoices) {
        Player player;
        if (gameConfig.getPlayerConfig() == ComputerVsComputer) {
            player = new ComputerPlayer();
        } else {
            if (gameConfig.hasUserChoice()) {
                player = new HumanPlayerWithChoice(gameConfig.getUserChoice());
            } else {
                player = new HumanPlayerInteractive(new DefaultUserInputReader());
            }
        }
        return player;
    }

    private static Player assembleVsPlayer(final GameConfig gameConfig, List<Gesture> gameChoices) {
        return new ComputerPlayer();
    }

}
