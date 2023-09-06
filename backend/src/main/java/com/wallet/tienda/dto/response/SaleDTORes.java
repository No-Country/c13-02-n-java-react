package com.wallet.tienda.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTORes {
    private Long id;
    private Double price;
    private UserDTORes user;
    private List<SoldProductDTORes> soldProducts;
}
