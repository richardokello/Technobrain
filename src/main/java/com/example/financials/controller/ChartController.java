package com.example.financials.controller;

import com.example.financials.entity.FinancialData;
import com.example.financials.service.FinancialDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

//@RestController
//@RequestMapping("/chart")
//public class ChartController {
//    @Autowired
//    private FinancialDataService financialDataService;
//
//    @GetMapping
//    public ResponseEntity<byte[]> generateChart(LocalDate startDate, LocalDate endDate) {
//        public ResponseEntity<byte[]> generateChart() {
//            List<FinancialData> financialDataList = financialDataService.getAllFinancialData(startDate,endDate);
//           // JFreeChart chart = /* Create your chart using financialDataList */;
//            byte[] chartBytes = convertChartToByteArray(chart);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_PNG);
//            headers.setContentLength(chartBytes.length);
//            return new ResponseEntity<>(chartBytes, headers, HttpStatus.OK);
//        }
//    }
//
//
//
//
//}
