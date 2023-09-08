package com.wallet.tienda.service;

import com.wallet.tienda.dto.response.ReportDTORes;
import com.wallet.tienda.repository.IBuyRepository;
import com.wallet.tienda.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReportService implements IReportService{

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private IBuyRepository buyRepository;


    public ReportDTORes balance(LocalDate date){

        var buys = buyRepository.findAll();
        var sales = saleRepository.findAll();

        return new ReportDTORes();
    }
}
