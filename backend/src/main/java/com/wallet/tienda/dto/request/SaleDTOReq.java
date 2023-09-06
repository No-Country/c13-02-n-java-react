package com.wallet.tienda.dto.request;

import com.wallet.tienda.model.CustomerUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTOReq {
    private Long id;
    private CustomerUser user;
}
