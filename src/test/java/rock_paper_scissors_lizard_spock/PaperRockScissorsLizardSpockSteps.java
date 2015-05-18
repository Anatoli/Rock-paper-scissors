package rock_paper_scissors_lizard_spock;


import com.proofcalc.handgame.model.*;
import com.proofcalc.handgame.model.strategy.Outcome;
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
        this.humanPlayer = spy(new HumanPlayer(gameChoices));
        this.computerPlayer = spy(new ComputerPlayer(gameChoices));
        this.game = new RockPaperScissorsGame(this.humanPlayer, this.computerPlayer);
    }

    @When("^Human user choice (\\w+)$")
    public void userChooses(String userChoice) throws Throwable {
        when(this.humanPlayer.getChoice()).thenReturn(Gesture.valueOf(userChoice));
    }

    @When("^Computer user choice (\\w+)$")
    public void computerChoice(String computerChoice) throws Throwable {
        when(this.computerPlayer.getChoice()).thenReturn(Gesture.valueOf(computerChoice));
    }

    @Then("^The game ends as (\\w+)$")
    public void theGameEndsAsWin(String gameOutcome) throws Throwable {
        Assert.assertEquals(this.game.play().outcome, Outcome.valueOf(gameOutcome.toUpperCase()));
    }
}
