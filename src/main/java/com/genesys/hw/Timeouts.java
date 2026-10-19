package com.genesys.hw;

import java.time.Duration;

public enum Timeouts {
    // Optional popups (cookie/privacy banners) and fast SauceDemo pages
    SHORT(5),
    // Default element wait
    DEFAULT(10),
    // Deliberately slow flows, e.g. performance_glitch_user login
    LONG(15);

    private final int seconds;

    Timeouts(int seconds) {
        this.seconds = seconds;
    }

    public int seconds() {
        return seconds;
    }

    public Duration duration() {
        return Duration.ofSeconds(seconds);
    }
}
