package com.proofcalc.handgame.rest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Component
@Scope
@Path("/")
public class PingResource {

    @GET
    @Path("ping")
    @Produces("application/json")
    public Response sanityCheck(@Context final HttpServletRequest request) {
        return Response.ok("pong").build();
    }

}
