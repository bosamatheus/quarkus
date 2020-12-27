package com.bosamatheus;

import java.time.temporal.ChronoUnit;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/gateway")
public class GatewayResource {

    @Inject
    @RestClient
    private TimeService service;

    @CircuitBreaker(
        requestVolumeThreshold = 4, 
        failureRatio = 0.5, 
        delay = 2000, 
        successThreshold = 2
    )
    @Fallback(fallbackMethod = "fallback")
    @Timeout(unit = ChronoUnit.MILLIS, value = 500)
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String gateway() throws InterruptedException {
        //Thread.sleep(510);
        return "Gateway: " + service.getTime();
    }

    public String fallback() {
        return "Fallback\n";
    }
}
