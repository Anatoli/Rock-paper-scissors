package com.proofcalc.handgame.rest;

import com.proofcalc.handgame.model.GameConfig;
import com.proofcalc.handgame.model.GameOutcome;
import com.proofcalc.handgame.model.Gesture;
import com.proofcalc.handgame.util.JsonConverter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameInputEvaluatorResourceTest extends RestJerseyTest {

    @Test
    public void evaluatesUserInput() {
        GameConfig gameConfig = GameConfig.from("RockPaperScissors", "HumanVsComputer", "Paper");

        ClientResponse clientResponse = postRequest(gameConfig);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());
        String responseJson = clientResponse.getEntity(String.class);
        GameOutcome gameOutcome = JsonConverter.fromJson(responseJson, GameOutcome.class);

        assertEquals(Gesture.Paper, gameOutcome.player1Choice);
        assertNotNull(gameOutcome.outcome);
    }

    private ClientResponse postRequest(GameConfig gameConfig) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource webResource = client.resource(getBaseURI());

        Form form = new Form();
        form.add("gameType", gameConfig.getGameType());
        form.add("playerConfig", gameConfig.getPlayerConfig());
        form.add("userChoice", gameConfig.getUserChoice());

        return webResource.path("service").path("game").path("input")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, form);
    }


}
