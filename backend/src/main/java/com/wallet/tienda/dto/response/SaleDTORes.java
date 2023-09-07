package com.wallet.tienda.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTORes {
    private Long id;
    private Double price;
    private LocalDateTime saleDate;
    private List<SoldProductDTORes> soldProducts;
}
