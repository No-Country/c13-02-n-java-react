package com.wallet.tienda.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTOReq {

    private Long id;
    @NotNull(message = "No puede estar vacio")
    private String name;
    @DecimalMin(value = "0.0", message = "El valor mínimo ingresado debe ser 0.0")
    @NotNull(message = "No puede estar vacio")
    private Double price;
    @NotNull(message = "No puede estar vacio")
    private int stock;
    private String imageUrl;
    @NotNull(message = "No puede estar vacio")
    private BrandDTOReq brand;
    @NotNull(message = "No puede estar vacio")
    private CategoryDTOReq category;

}