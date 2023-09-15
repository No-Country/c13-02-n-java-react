package com.wallet.tienda.service;

import com.wallet.tienda.dto.response.ReportDTORes;

import java.util.Map;

public interface IReportService {
    ReportDTORes balance();

    Map<String, Double> balanceOfMonths();
}
