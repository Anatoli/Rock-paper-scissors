package com.proofcalc.handgame.rest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PingResourceTest extends RestJerseyTest {

    @Test
    public void serviceSanityCheckTest() throws Exception {
        String responseMessage = webResource
                .path("ping")
                .get(String.class);

        assertEquals("pong", responseMessage);
    }
}
