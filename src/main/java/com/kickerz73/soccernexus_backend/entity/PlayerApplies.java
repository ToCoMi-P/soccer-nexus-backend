package com.kickerz73.soccernexus_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.util.Pair;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    private @NonNull Instant instant;
    private @NonNull LocalDate date;
}
