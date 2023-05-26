package com.example.financials.service;

import com.example.financials.Repository.FinancialDataRepository;
import com.example.financials.entity.FinancialData;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;



@Service
public class FinancialDataService {
    private final FinancialDataRepository financialDataRepository;

    public FinancialDataService(FinancialDataRepository financialDataRepository) {
        this.financialDataRepository = financialDataRepository;
    }

    public List<FinancialData> getAllFinancialData(LocalDate startDate, LocalDate endDate) {
        return financialDataRepository.findByDateBetween(startDate, endDate);
    }


}