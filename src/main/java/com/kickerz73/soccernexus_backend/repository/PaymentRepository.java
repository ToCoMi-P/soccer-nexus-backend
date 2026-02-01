package com.kickerz73.soccernexus_backend.repository;

import com.kickerz73.soccernexus_backend.entity.PaymentEntity;
import com.kickerz73.soccernexus_backend.entity.PlayerEntity;
import com.kickerz73.soccernexus_backend.enums.PaymentStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    
    // Prüft ob Spieler offene Zahlungen hat
    boolean existsByPlayerAndStatusIn(PlayerEntity player, List<PaymentStatus> statuses);
    
    // Alle offenen Zahlungen eines Spieltags
    List<PaymentEntity> findByMatchDayIdAndStatusIn(Long matchDayId, List<PaymentStatus> statuses);
    
    // Alle Zahlungen eines Spielers
    List<PaymentEntity> findByPlayerOrderByCreatedAtDesc(PlayerEntity player);
    
    @Query("SELECT p FROM PaymentEntity p WHERE p.status IN :openStatuses ORDER BY p.createdAt ASC")
    List<PaymentEntity> findAllOpenPayments(List<PaymentStatus> openStatuses);
}
