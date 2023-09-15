package com.wallet.tienda.dto.request;

import com.wallet.tienda.model.Provider;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyDTOReq {
    private Long id;
    @NotNull(message = "Debe asignar un proovedor")
    private Provider provider;
    private LocalDateTime purchaseDate;
    private List<BoughtProductDTOReq> purchasedProducts;

}
