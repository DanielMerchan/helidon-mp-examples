package io.helidon.examples.quickstart.mp;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

/**
 * Custom Helth Check to indicate the application is ready to be used
 */
@Readiness
@ApplicationScoped
public class GreetReadinessCheck implements HealthCheck {

    private AtomicLong readyTime = new AtomicLong(0);

    /**
     * Build the Health Check response to show if the service is ready or not
     */
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("ReadinessCheck")
            .state(isReady()).withData("time", readyTime.get()).build();
    }

    /**
     * Check if the Microservice is ready
     * @return true if passes 5 seconds
     */
    private boolean isReady() {
        return Duration.ofMillis(System.currentTimeMillis() - readyTime.get()).getSeconds() >=5 ;
    }

    public void onStartup(@Observes @Initialized(ApplicationScoped.class) Object init) {
        readyTime = new AtomicLong(System.currentTimeMillis());
    }
}