package com.wallet.tienda.dto.request;

import com.wallet.tienda.model.CustomerUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTOReq {
    private Long id;
    private CustomerUser user;
    private List<SoldProductDTOReq> soldProducts;
}
