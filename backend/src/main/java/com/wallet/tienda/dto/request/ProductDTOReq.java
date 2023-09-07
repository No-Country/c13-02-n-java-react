package com.wallet.tienda.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[0-9 ]+$", message = "Debe contener solo numeros o espacios")
    @NotNull(message = "No puede estar vacio")
    private int stock;
    @NotNull(message = "No puede estar vacio")
    private BrandDTOReq brand;
    @NotNull(message = "No puede estar vacio")
    private CategoryDTOReq category;
    @NotNull(message = "No puede estar vacio")
    private OrderDTOReq order;
    @NotNull(message = "No puede estar vacio")
    private ProviderDTOReq provider;


}