package com.domain.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class OtherService implements HealthIndicator {

    private boolean status = true;

    @Override
    public Health health() {
        if (isOtherServiceeHealthGood()) {
            return Health.up().withDetail("OtherService", "Service is Running").build();
        }
        return Health.down().withDetail("OtherService", "Service is not Available").build();
    }

    public boolean isOtherServiceeHealthGood() {
        // cek apakah database sehat
        return status = !status;
    }

}
