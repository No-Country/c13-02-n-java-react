package com.wallet.tienda.dto.request;

import com.wallet.tienda.model.Provider;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyDTOReq {
    private Long id;
    @NotNull(message = "Debe asignar un producto vendido")
    private List<BoughtProductDTOReq> purchasedProducts;
    @NotNull(message = "Debe asignar un proovedor")
    private Provider provider;
}
