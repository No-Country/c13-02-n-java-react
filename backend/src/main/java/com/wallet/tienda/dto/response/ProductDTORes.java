package com.wallet.tienda.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private BrandDTORes brand;
    private CategoryDTORes category;

}