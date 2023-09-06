package com.wallet.tienda.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoldProductDTORes {
    private Long id;
    private Double price;
    private int quantity;
    private SaleDTORes sale;
    private ProductDTORes product;
}
