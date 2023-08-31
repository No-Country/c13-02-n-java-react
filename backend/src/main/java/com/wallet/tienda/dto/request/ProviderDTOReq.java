package com.wallet.tienda.dto.request;

import com.wallet.tienda.model.CustomerUser;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDTOReq {
    private Long id;
    @NotNull(message = "No puede estar vacio")
    private String name;
    @NotNull(message = "No puede estar vacio")
    private List<CustomerUser> users;
}