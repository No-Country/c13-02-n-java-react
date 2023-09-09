package com.wallet.tienda.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTORes {

    private Double totalSaleDay;
    private Double totalSaleYear;
    private Double totalCostYear;
    private Double totalProfitMonth;

}
