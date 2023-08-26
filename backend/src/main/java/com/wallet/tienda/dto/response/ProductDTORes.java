package com.wallet.tienda.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTORes {

    private Long id;
    private String name;
    private Double price;
    private int stock;

}