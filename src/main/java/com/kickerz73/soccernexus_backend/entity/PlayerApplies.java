package com.kickerz73.soccernexus_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class PlayerApplies {

    private @Id @GeneratedValue Long id;

    private @NonNull @OneToOne PlayerEntity player;
    private @NonNull String instant;
    private @NonNull LocalDate date;
}
