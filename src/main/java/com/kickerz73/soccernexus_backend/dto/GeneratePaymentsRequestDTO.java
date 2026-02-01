package com.kickerz73.soccernexus_backend.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class GeneratePaymentsRequestDTO {
    private LocalDate matchDayDate;
}
