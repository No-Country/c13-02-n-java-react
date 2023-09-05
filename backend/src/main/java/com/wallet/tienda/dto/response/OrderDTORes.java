package com.wallet.tienda.dto.response;

import com.wallet.tienda.model.CustomerUser;
import com.wallet.tienda.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTORes {
    private Long id;
    private String name;
    private List<Product> reservedProducts;
    private CustomerUser user;
}
