package zero_sum_hand_game;

import com.proofcalc.handgame.model.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;

public class DifferentGameEachTimeSteps {

    private ZeroSumHandGame game;

    @Given("^User selects (\\w+) and (\\w+)$")
    public void userSelectsRockPaperScissors(String gameType, String playerConfig) throws Throwable {
        GameConfig gameConfig = GameConfig.from(GameType.valueOf(gameType), PlayerConfig.valueOf(playerConfig));
        this.game = ZeroSumHandGameFactory.getGameBy(gameConfig);
    }

    @Then("^The game instance is of (\\w+)$")
    public void theGameInstanceIsOfGameInstance(String gameInstance) throws Throwable {
        assertEquals(game.getClass().getSimpleName(), gameInstance);
    }

    @Then("^Player is a (\\w+)$")
    public void playerIsA(String playerInstance) throws Throwable {
        assertEquals(this.game.player1.getClass().getSimpleName(), playerInstance);
    }

    @Then("^Versus Player is a (\\w+)$")
    public void playerIsAHumanPlayer(String playerInstance) throws Throwable {
        assertEquals(this.game.player2.getClass().getSimpleName(), playerInstance);
    }

}