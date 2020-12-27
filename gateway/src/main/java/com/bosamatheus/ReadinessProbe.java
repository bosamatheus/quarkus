package com.bosamatheus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@Readiness
public class ReadinessProbe implements HealthCheck {

    @Inject
    @RestClient
    private TimeService service;

    @Override
    public HealthCheckResponse call() {
        if (service.getTime() == null) {
            return HealthCheckResponse.down("Wait");
        }
        return HealthCheckResponse.up("I am alive");
    }
}
