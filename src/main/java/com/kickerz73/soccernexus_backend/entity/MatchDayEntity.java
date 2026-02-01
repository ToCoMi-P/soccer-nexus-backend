package com.kickerz73.soccernexus_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kickerz73.soccernexus_backend.enums.MatchLocation;

@Entity
@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor @NoArgsConstructor @ToString
public class MatchDayEntity {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    
    @NonNull private LocalDate date;
    @Column(precision = 10, scale = 2) private BigDecimal price;
    @NonNull @Enumerated(EnumType.STRING) private MatchLocation matchLocation;
    private boolean isActive;

    //private String description;
    
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
