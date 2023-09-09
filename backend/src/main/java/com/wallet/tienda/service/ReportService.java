package com.wallet.tienda.service;

import com.wallet.tienda.dto.response.ReportDTORes;
import com.wallet.tienda.model.Buy;
import com.wallet.tienda.model.Sale;
import com.wallet.tienda.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReportService implements IReportService{

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private IBuyRepository buyRepository;

    @Autowired
    private IExpenseRepository expenseRepository;


    @Override
    public ReportDTORes balance(){

        var total = 0.0;
        var report = new ReportDTORes();
        var buys = buyRepository.findAll();

        //ganancias brutas del dia
        for (Sale sale: saleRepository.findAll()) {
            if (sale.getSaleDate().toLocalDate().equals(LocalDate.now())){
                total += sale.getPrice();
            }
        }
        report.setTotalSaleDay(total);
        total = 0.0;

        //ganancias brutas anuales
        for (Sale sale: saleRepository.findAll()) {
                total += sale.getPrice();
        }
        report.setTotalSaleDay(total);
        total = 0.0;

        //costos anuales
        for (Buy buy:buyRepository.findAll()) {
            total += buy.getTotalPrice();
        }
        report.setTotalCostYear(total);
        total = 0.0;

        //ganancias netas mensuales
        for (Sale sale: saleRepository.findAll()) {
            if (sale.getSaleDate().getMonth().equals(LocalDate.now().getMonth())){
                total += sale.getPrice();
            }
        }
        report.setTotalProfitMonth(total);

        return report;
    }
}
