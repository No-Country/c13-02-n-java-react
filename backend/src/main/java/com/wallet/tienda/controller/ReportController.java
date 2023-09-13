package com.wallet.tienda.controller;

import com.wallet.tienda.dto.response.ReportDTORes;
import com.wallet.tienda.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    @Autowired
    private IReportService reportService;

    @GetMapping()
    public ResponseEntity<ReportDTORes> getBalance(){
        return ResponseEntity.ok(reportService.balance());
    }

    @GetMapping("/months")
    public ResponseEntity<Map<String, Double>> getMonthBalance(){
        return ResponseEntity.ok(reportService.balanceOfMonths());
    }


}
