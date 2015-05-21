package rock_paper_scissors;

import com.proofcalc.handgame.model.game.RockPaperScissorsGame;
import com.proofcalc.handgame.model.game.ZeroSumHandGame;
import com.proofcalc.handgame.model.input.Gesture;
import com.proofcalc.handgame.model.player.ComputerPlayer;
import com.proofcalc.handgame.model.player.HumanPlayer;
import com.proofcalc.handgame.model.player.HumanPlayerWithChoice;
import com.proofcalc.handgame.model.game.Outcome;
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
        this.computerPlayer = spy(new ComputerPlayer());
    }

    @When("^Human player choose (\\w+)$")
    public void humanPlayerChoose(String playerChoice) throws Throwable {
        this.humanPlayer = new HumanPlayerWithChoice(Gesture.valueOf(playerChoice));
    }

    @And("^Computer player choose (\\w+)$")
    public void computerPlayerChoose(String computerChoice) throws Throwable {
        when(this.computerPlayer.getChoice(gameChoices)).thenReturn(Gesture.valueOf(computerChoice));
    }

    @Then("^The outcome of the game should be (\\w+)$")
    public void theOutcomeOfTheGameShouldBe(String gameOutcome) throws Throwable {
        this.game = new RockPaperScissorsGame()
                .setPlayer1(this.humanPlayer)
                .setPlayer2(this.computerPlayer);
        Assert.assertEquals("Wrong expected outcome", this.game.play().outcome, Outcome.valueOf(gameOutcome.toUpperCase()));
    }

}
