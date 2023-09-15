package com.wallet.tienda.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoldProductDTOReq {
    private Long id;
    @NotNull(message = "No puede estar vacio")
    private int quantity;
    private ProductDTOReq product;
}
