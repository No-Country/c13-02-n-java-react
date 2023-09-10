package com.wallet.tienda.service;

import com.wallet.tienda.dto.response.ReportDTORes;
import com.wallet.tienda.model.Buy;
import com.wallet.tienda.model.Sale;
import com.wallet.tienda.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

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
        var monthProfit = 0.0;
        var monthCost = 0.0;
        var report = new ReportDTORes();

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
        report.setTotalSaleYear(total);
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
               monthProfit += sale.getPrice();
            }
        }
        for (Buy buy: buyRepository.findAll()) {
            if (buy.getPurchaseDate().getMonth().equals(LocalDate.now().getMonth())){
                monthCost += buy.getTotalPrice();
            }
        }
        report.setTotalProfitMonth(monthProfit - monthCost);
        return report;
    }


    @Override
    public Map<String, Double> balanceOfMonths(){
        var year = LocalDate.now().getYear();
        var month = "";
        var totalProfit = 0.0;
        Map<String, Double> report = new LinkedHashMap<>();

        var sort = Sort.by(Sort.Direction.ASC, "saleDate");
        var sales = saleRepository.findAll(sort);

        for (Sale sale: sales) {
            if (month.equals("") || sale.getSaleDate().getYear() == year
            && sale.getSaleDate().getMonth().toString().equals(month)) {
                month = sale.getSaleDate().getMonth().toString();
                totalProfit += sale.getPrice();
            }else{
                report.put(month, totalProfit);
                month = sale.getSaleDate().getMonth().toString();
                totalProfit = sale.getPrice();
            }
        }
        if (!sales.isEmpty()){
            report.put(month, totalProfit);
        }
        return report;
    }


}
