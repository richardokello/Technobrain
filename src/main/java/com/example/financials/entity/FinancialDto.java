package com.example.financials.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialDto {
    private String symbol;
    private LocalDate date;
    private double price;
}
