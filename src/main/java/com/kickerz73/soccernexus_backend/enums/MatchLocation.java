package com.kickerz73.soccernexus_backend.enums;

import java.math.BigDecimal;

public enum MatchLocation {
    RUEHME(new BigDecimal("60.00")),
    ALOHA(new BigDecimal("90.00"));
    
    private final BigDecimal price;
    
    MatchLocation(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getPrice() { return price; }
}
