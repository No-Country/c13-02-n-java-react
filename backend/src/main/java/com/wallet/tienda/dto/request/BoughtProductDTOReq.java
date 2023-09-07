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
    @Pattern(regexp = "^[0-9 ]+$", message = "Debe contener solo numeros o espacios")
    @NotNull(message = "La cantidad mínima debe ser de 1")
    private int quantity;
    @NotNull(message = "Debe asignar una compra")
    private BuyDTOReq buy;
    @NotNull(message = "Debe asignar un producto")
    private ProductDTOReq product;
}
