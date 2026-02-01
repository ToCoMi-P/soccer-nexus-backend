package com.kickerz73.soccernexus_backend.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kickerz73.soccernexus_backend.entity.PaymentEntity;
import com.kickerz73.soccernexus_backend.entity.PlayerApplies;
import com.kickerz73.soccernexus_backend.enums.MatchLocation;
import com.kickerz73.soccernexus_backend.enums.PaymentStatus;

import com.kickerz73.soccernexus_backend.repository.PaymentRepository;

@Service
@Transactional
public class PaymentService {

    @Autowired private AdminService adminService;
    @Autowired private PaymentRepository paymentRepository;
    @Autowired private PlayerAppliesService playerAppliesService;
    @Autowired private MatchDayService matchDayService;

    public PaymentEntity savePayment(PaymentEntity paymentEntity) {
        return paymentRepository.save(paymentEntity);
    }

    public void savePaymentList(List<PaymentEntity> payments) {
        payments.forEach(payment -> savePayment(payment));
    }

    // 🔥 NEUE FUNKTION: Anmeldungen → Zahlungen
    public List<PaymentEntity> generatePaymentsFromApplies() {
        List<PlayerApplies> activePlayers = playerAppliesService.getActivePlayerApplies();

        BigDecimal pricePerPlayer =  matchDayService.getActiveMatchDayLocation() == MatchLocation.ALOHA ? 
                            MatchLocation.ALOHA.getPrice().divide(BigDecimal.valueOf(activePlayers.size()), 2, RoundingMode.HALF_UP)  : 
                            MatchLocation.RUEHME.getPrice().divide(BigDecimal.valueOf(activePlayers.size()), 2, RoundingMode.HALF_UP)
                            ;

        List<PaymentEntity> payments = activePlayers
            .stream()
            .map (apply -> new PaymentEntity(matchDayService.getActiveMatchDay() , apply.getPlayer(), pricePerPlayer))
            .toList();

        savePaymentList(payments);

        matchDayService.closeActiveMatchDay();
        matchDayService.openNextActiveMatchDay();

        playerAppliesService.clearAllPlayerApplies();
        adminService.lockPlayerApplies();
        
        return payments;
    }
    
    public List<PaymentEntity> getAllOpenPayments() {
        List<PaymentEntity> payments = paymentRepository.findAllOpenPayments(
            List.of(PaymentStatus.PENDING_REVIEW, PaymentStatus.NOT_PAID)
        );
        return payments;
    }
    
    public PaymentEntity updatePaymentStatus(Long paymentId, PaymentStatus status) {
        PaymentEntity payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new RuntimeException("Payment not found"));
        
        payment.setStatus(status);
        payment.setReviewedAt(LocalDateTime.now());
        if (status == PaymentStatus.PAID) {
            payment.setPaidAt(LocalDateTime.now());
        }
        
        paymentRepository.save(payment);
        return payment;
    }    
}
