package com.kickerz73.soccernexus_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class AdminEntity {

    private @Id @GeneratedValue Long id;

    private @NonNull Integer maxPlayers;
}
