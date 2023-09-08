package com.wallet.tienda.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTORes {

    private Double totalAmountSaleDay;
    private Double totalAmountSaleMonthAndYear;
    private Double totalAmountSaleYear;
    private Double totalCost;
    private Double totalSale;

}
