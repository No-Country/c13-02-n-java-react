package com.wallet.tienda.dto.response;

import com.wallet.tienda.dto.request.SoldProductDTOReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoldProductDTORes {
    private Long id;
    private Double price;
    private int quantity;
    private ProductDTORes product;
}
