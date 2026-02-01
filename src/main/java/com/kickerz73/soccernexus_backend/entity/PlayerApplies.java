package com.kickerz73.soccernexus_backend.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class PlayerApplies {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    
    private @OneToOne @JoinColumn(name = "player_id", nullable = false) @NonNull PlayerEntity player;
    
    private @NonNull String instant;
    private @OneToOne @JoinColumn(name = "matchday_id", nullable = false) @NonNull MatchDayEntity matchDayEntity;
}
