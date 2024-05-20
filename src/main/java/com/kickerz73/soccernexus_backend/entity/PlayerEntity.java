package com.kickerz73.soccernexus_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.web.bind.annotation.RequestAttribute;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class PlayerEntity {

    private @Id @GeneratedValue Long id;

    private @NonNull String vorname;
    private @NonNull String nachname;
}
