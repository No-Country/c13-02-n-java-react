package com.wallet.tienda.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoughtProductDTOReq {
    private Long id;
    @NotNull(message = "Debe asignar una compra")
    private BuyDTOReq buy;
    @NotNull(message = "Debe asignar un producto")
    private ProductDTOReq product;
}
