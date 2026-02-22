package org.haiilo.config;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

public final class TestClock {
    public static final Instant FIXED_INSTANT = Instant.parse("2026-02-15T12:00:00Z");

    private TestClock() {
    }

    public static Clock fixedUtcClock() {
        return Clock.fixed(FIXED_INSTANT, ZoneOffset.UTC);
    }
}
