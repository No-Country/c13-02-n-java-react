package com.wallet.tienda.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoldProductDTOReq {
    private Long id;
    private Double price;
    private int quantity;
    private SaleDTOReq sale;
    private ProductDTOReq product;
}
