package io.helidon.examples.quickstart.mp;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

/**
 * Custom Health Check (Time live for the Microservice)
 */
@Liveness
@ApplicationScoped
public class GreetLivenessCheck implements HealthCheck {

    /**
     * Build the Health Check response to show the time alive of the service
     */
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("LivenessCheck")
            .up()
            .withData("time", System.currentTimeMillis())
            .build();
    }
}