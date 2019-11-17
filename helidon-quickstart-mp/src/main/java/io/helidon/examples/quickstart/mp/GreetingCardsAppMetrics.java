package io.helidon.examples.quickstart.mp;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

import org.eclipse.microprofile.metrics.annotation.Gauge;

/**
 * GreetingCardsAppMetrics
 */
@ApplicationScoped
public class GreetingCardsAppMetrics {

    private AtomicLong startTime = new AtomicLong(0);

    public void onStartUp(@Observes @Initialized(ApplicationScoped.class) Object init) {
        startTime = new AtomicLong(System.currentTimeMillis());
    }

    @Gauge(unit="TimeSeconds")
    public long appUpTimeSeconds() {
        return Duration.ofMillis(System.currentTimeMillis() - startTime.get()).getSeconds();
    }
}