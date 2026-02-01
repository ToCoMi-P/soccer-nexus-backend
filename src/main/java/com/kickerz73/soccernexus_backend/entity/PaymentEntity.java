package com.kickerz73.soccernexus_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kickerz73.soccernexus_backend.enums.PaymentStatus;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentEntity {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_day_id")
    private MatchDayEntity matchDay;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    @Column(precision = 10, scale = 2) private BigDecimal price;
    
    @NonNull @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING_REVIEW;
    
    private LocalDateTime createdAt;
    private LocalDateTime reviewedAt;
    private LocalDateTime paidAt;

    public PaymentEntity(MatchDayEntity matchDay, PlayerEntity player, BigDecimal price) {
        this.matchDay = matchDay;
        this.player = player;
        this.price = price;
    }
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
