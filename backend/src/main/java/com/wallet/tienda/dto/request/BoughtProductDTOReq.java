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
public class BoughtProductDTOReq {
    private Long id;
    @DecimalMin(value = "0.0", message = "El valor mínimo ingresado debe ser 0.0")
    @NotNull(message = "No puede estar vacio")
    private Double price;
    @NotNull(message = "No puede estar vacio")
    private int quantity;
    @NotNull(message = "Debe asignar un producto")
    private ProductDTOReq product;
}
