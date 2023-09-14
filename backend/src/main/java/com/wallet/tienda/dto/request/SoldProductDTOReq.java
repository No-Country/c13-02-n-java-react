package com.wallet.tienda.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoldProductDTOReq {
    private Long id;
    @NotBlank(message = "No puede estar vacio")
    private int quantity;
    @NotBlank(message = "No puede estar vacio")
    private ProductDTOReq product;
}
