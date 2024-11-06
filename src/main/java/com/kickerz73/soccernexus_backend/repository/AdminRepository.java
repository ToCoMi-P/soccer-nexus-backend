package com.kickerz73.soccernexus_backend.repository;

import com.kickerz73.soccernexus_backend.entity.AdminEntity;
import com.kickerz73.soccernexus_backend.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
}
