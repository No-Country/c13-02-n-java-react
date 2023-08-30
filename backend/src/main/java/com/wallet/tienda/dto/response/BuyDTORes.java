package com.wallet.tienda.dto.response;

import com.wallet.tienda.model.BoughtProduct;
import com.wallet.tienda.model.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyDTORes {
    private Long id;
    private double totalPrice;
    private List<BoughtProduct> purchasedProducts;
    private Provider provider;
    private LocalDateTime purchaseDate;
}
