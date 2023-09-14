package com.wallet.tienda.dto.request;

import com.wallet.tienda.model.CustomerUser;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTOReq {
    private Long id;
    private CustomerUser user;
    @NotBlank(message = "Debe asignar al menos un producto vendido")
    private LocalDateTime saleDate;
    private List<SoldProductDTOReq> soldProducts;
}
