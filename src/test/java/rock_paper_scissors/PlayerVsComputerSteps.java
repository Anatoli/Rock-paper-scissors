package rock_paper_scissors;

import com.proofcalc.handgame.model.*;
import com.proofcalc.handgame.model.strategy.Outcome;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class PlayerVsComputerSteps {

    private ZeroSumHandGame game;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;

    private List<Gesture> gameChoices = RockPaperScissorsGame.GESTURES;

    @Given("^A human player plays vs computer player$")
    public void aHumanPlayerPlaysVsComputerPlayer() throws Throwable {
        this.humanPlayer = spy(new HumanPlayer(gameChoices));
        this.computerPlayer = spy(new ComputerPlayer(gameChoices));
        this.game = new RockPaperScissorsGame(this.humanPlayer, this.computerPlayer);
    }

    @When("^Human player choose (\\w+)$")
    public void humanPlayerChoose(String playerChoice) throws Throwable {
        when(this.humanPlayer.getChoice()).thenReturn(Gesture.valueOf(playerChoice));
    }

    @And("^Computer player choose (\\w+)$")
    public void computerPlayerChoose(String computerChoice) throws Throwable {
        when(this.computerPlayer.getChoice()).thenReturn(Gesture.valueOf(computerChoice));
    }

    @Then("^The outcome of the game should be (\\w+)$")
    public void theOutcomeOfTheGameShouldBe(String gameOutcome) throws Throwable {
        Assert.assertEquals("Wrong expected outcome", this.game.play().outcome, Outcome.valueOf(gameOutcome.toUpperCase()));
    }

}
