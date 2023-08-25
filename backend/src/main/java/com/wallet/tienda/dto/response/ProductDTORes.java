package com.wallet.tienda.dto.response;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Hidden
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTORes {

    private Long id;
    private String name;
    private Double price;
    private int stock;

}