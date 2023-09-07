package com.wallet.tienda.dto.response;

import com.wallet.tienda.model.*;
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
    private String imageUrl;
    private CustomerUser user;
    private Brand brand;
    private Category category;

}