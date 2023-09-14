package com.wallet.tienda.dto.request;

import com.wallet.tienda.model.CustomerUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTOReq {
    private Long id;
    private CustomerUser user;
    private LocalDateTime saleDate;
    private List<SoldProductDTOReq> soldProducts;
}
