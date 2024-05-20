package com.kickerz73.soccernexus_backend.repository;

import com.kickerz73.soccernexus_backend.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
}
