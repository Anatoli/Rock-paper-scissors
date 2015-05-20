package com.proofcalc.handgame.model;

public class GameConfig {
    private GameType gameType;
    private PlayerConfig playerConfig;
    private Gesture userChoice;

    private GameConfig(GameType gameType, PlayerConfig playerConfig) {
        this.gameType = gameType;
        this.playerConfig = playerConfig;
    }

    public static GameConfig from(GameType gameType, PlayerConfig playerConfig) {
        return new GameConfig(gameType, playerConfig);
    }

    public static GameConfig from(String gameType, String playerConfig, String userChoice) {
        GameConfig gameConfig = GameConfig.from(GameType.valueOf(gameType), PlayerConfig.valueOf(playerConfig));
        if (userChoice != null) gameConfig.setUserChoice(Gesture.valueOf(userChoice));
        return gameConfig;
    }

    public GameType getGameType() {
        return gameType;
    }

    public PlayerConfig getPlayerConfig() {
        return playerConfig;
    }

    public Gesture getUserChoice() {
        return userChoice;
    }

    public GameConfig setUserChoice(Gesture userChoice) {
        this.userChoice = userChoice;
        return this;
    }

    public boolean hasUserChoice() {
        return this.userChoice != null;
    }

}
