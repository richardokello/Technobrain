package com.example.financials.Repository;

import com.example.financials.entity.FinancialData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialDataRepository extends JpaRepository<FinancialData, Long> {

    List<FinancialData> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
