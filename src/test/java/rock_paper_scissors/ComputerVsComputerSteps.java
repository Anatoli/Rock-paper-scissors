package rock_paper_scissors;

import com.proofcalc.handgame.model.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

;

public class ComputerVsComputerSteps {

    private GameType gameType;
    private PlayerConfig playerConfig;

    @Given("^Rock Paper Scissor game$")
    public void rockPaperScissorGame() throws Throwable {
        this.gameType = GameType.RockPaperScissors;
    }

    @Given("^Computer plays vs another computer$")
    public void computerPlaysVsAnotherComputer() throws Throwable {
        this.playerConfig = PlayerConfig.ComputerVsComputer;
    }

    @Then("^The game ends with an outcome$")
    public void theGameEndsWithAnOutcome() throws Throwable {
        ZeroSumHandGame game = ZeroSumHandGameFactory.getGameBy(GameConfig.from(this.gameType, this.playerConfig));
        Assert.assertNotNull(game.play());
    }
}
