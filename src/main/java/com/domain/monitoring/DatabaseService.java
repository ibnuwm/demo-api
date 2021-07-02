package com.domain.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService implements HealthIndicator {

    @Override
    public Health health() {
        if (isDatabaseHealthGood()) {
            return Health.up().withDetail("DatabaseService", "Service is Running").build();
        }
        return Health.down().withDetail("DatabaseService", "Service is not Available").build();
    }

    public boolean isDatabaseHealthGood() {
        // cek apakah database sehat
        return true;
    }

}
