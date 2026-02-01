package com.kickerz73.soccernexus_backend.repository;

import com.kickerz73.soccernexus_backend.entity.MatchDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MatchDayRepository extends JpaRepository<MatchDayEntity, Long> {
    Optional<MatchDayEntity> findByDate(LocalDate date);
    Optional<MatchDayEntity> findFirstByDateAfterOrderByDateAsc(LocalDate date);
    Optional<MatchDayEntity> findByIsActiveTrue();
}
