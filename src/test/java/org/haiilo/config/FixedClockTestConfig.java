package org.haiilo.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.Clock;

@TestConfiguration
public class FixedClockTestConfig {

    @Bean
    @Primary
    public Clock fixedClock() {
        return TestClock.fixedUtcClock();
    }
}
