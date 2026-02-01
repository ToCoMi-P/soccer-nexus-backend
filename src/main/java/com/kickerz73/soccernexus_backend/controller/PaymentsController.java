package com.kickerz73.soccernexus_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kickerz73.soccernexus_backend.entity.PaymentEntity;
import com.kickerz73.soccernexus_backend.enums.PaymentStatus;
import com.kickerz73.soccernexus_backend.service.PaymentService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class PaymentsController {

    @Autowired private PaymentService paymentService;

     // 🔥 NEUE ENDPOINTS
    @PostMapping("/payments/generate")
    public ResponseEntity<List<PaymentEntity>> generatePayments() {
        List<PaymentEntity> payments = paymentService.generatePaymentsFromApplies();
        return ResponseEntity.ok(payments);
    }
    
    @GetMapping("/payments/open")
    public ResponseEntity<List<PaymentEntity>> getOpenPayments() {
        List<PaymentEntity> payments = paymentService.getAllOpenPayments();
        return ResponseEntity.ok(payments);
    }
    
    @PutMapping("/payments/{paymentId}/status")
    public ResponseEntity<PaymentEntity> updatePaymentStatus(
            @PathVariable Long paymentId,
            @RequestBody PaymentStatus status) {
        PaymentEntity payment = paymentService.updatePaymentStatus(paymentId, status);
        return ResponseEntity.ok(payment);
    }
    
}
