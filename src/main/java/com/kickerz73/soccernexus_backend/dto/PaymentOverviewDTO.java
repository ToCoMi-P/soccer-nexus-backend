package com.kickerz73.soccernexus_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PaymentOverviewDTO {
    private Long id;
    private String playerName;
    private String matchDayDescription;
    private String matchDayDate;
    private Double price;
    private String status;
    private String statusColor; // "yellow", "green", "red"
}
