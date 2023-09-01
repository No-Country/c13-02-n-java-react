package com.wallet.tienda.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTOReq {
    private Long id;
    @NotNull(message = "Debe asignar un nombre")
    private String name;
    @NotNull(message = "Debe asignar un producto")
    private List<ProductDTOReq> reservedProducts;
}
