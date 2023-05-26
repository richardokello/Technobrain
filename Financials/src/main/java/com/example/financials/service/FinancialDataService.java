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


//    public byte[] convertChartToByteArray(JFreeChart chart) {
//        byte[] chartBytes = null;
//        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
//            ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);
//            chartBytes = outputStream.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return chartBytes;
//    }

    // Implement other methods for calculations and filtering as per requirements
}