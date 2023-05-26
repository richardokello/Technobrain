package com.example.financials.controller;

import com.example.financials.entity.FinancialData;
import com.example.financials.service.FinancialDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/financial-data")
public class FinancialDataController {
    private final FinancialDataService financialDataService;

    public FinancialDataController(FinancialDataService financialDataService) {
        this.financialDataService = financialDataService;
    }

    @GetMapping("fetch-filtered-data")
    public List<FinancialData> getAllFinancialData(LocalDate startDate, LocalDate endDate) {

        List<FinancialData> filteredData = financialDataService.getAllFinancialData(startDate, endDate);
        return filteredData;
    }
    @GetMapping()
    public String fetchDataFromEndpoint() {
        String endpointUrl = "http://shorturl.at/itz57";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(endpointUrl, String.class);
    }

}