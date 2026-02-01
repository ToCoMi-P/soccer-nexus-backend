package com.kickerz73.soccernexus_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kickerz73.soccernexus_backend.entity.PlayerEntity;
import com.kickerz73.soccernexus_backend.enums.PaymentStatus;
import com.kickerz73.soccernexus_backend.repository.PaymentRepository;

@Service
@Transactional
public class PlayerService {
    
    @Autowired private PaymentRepository paymentRepository;
    
    public boolean canPlayerRegister(PlayerEntity player) {
        return !paymentRepository.existsByPlayerAndStatusIn(
            player,
            List.of(PaymentStatus.PENDING_REVIEW, PaymentStatus.NOT_PAID)
        );
    }
}
