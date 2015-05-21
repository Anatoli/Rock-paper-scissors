package rock_paper_scissors_lizard_spock;


import com.proofcalc.handgame.model.game.RockPaperScissorsLizardSpockGame;
import com.proofcalc.handgame.model.game.ZeroSumHandGame;
import com.proofcalc.handgame.model.input.Gesture;
import com.proofcalc.handgame.model.player.ComputerPlayer;
import com.proofcalc.handgame.model.player.HumanPlayer;
import com.proofcalc.handgame.model.player.HumanPlayerWithChoice;
import com.proofcalc.handgame.model.game.Outcome;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class PaperRockScissorsLizardSpockSteps {

    private ZeroSumHandGame game;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;

    private List<Gesture> gameChoices = RockPaperScissorsLizardSpockGame.GESTURES;


    @Given("^Extended Human vs Computer game ")
    public void extendedHumanVsComputerGame() throws Throwable {
        this.computerPlayer = spy(new ComputerPlayer());
    }

    @When("^Human user choice (\\w+)$")
    public void userChooses(String userChoice) throws Throwable {
        this.humanPlayer = new HumanPlayerWithChoice(Gesture.valueOf(userChoice));
    }

    @When("^Computer user choice (\\w+)$")
    public void computerChoice(String computerChoice) throws Throwable {
        when(this.computerPlayer.getChoice(gameChoices)).thenReturn(Gesture.valueOf(computerChoice));
    }

    @Then("^The game ends as (\\w+)$")
    public void theGameEndsAsWin(String gameOutcome) throws Throwable {
        this.game = new RockPaperScissorsLizardSpockGame()
                .setPlayer1(this.humanPlayer)
                .setPlayer2(this.computerPlayer);

        Assert.assertEquals(this.game.play().outcome, Outcome.valueOf(gameOutcome.toUpperCase()));
    }
}
