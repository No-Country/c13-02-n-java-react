package com.wallet.tienda.dto.response;

import com.wallet.tienda.model.Buy;
import com.wallet.tienda.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoughtProductDTORes {
    private Long id;
    private Double price;
    private int quantity;
    private Product product;
    private Buy buy;
}
