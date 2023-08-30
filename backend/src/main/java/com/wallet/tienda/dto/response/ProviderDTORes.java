package com.wallet.tienda.dto.response;

import com.wallet.tienda.model.CustomerUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDTORes {

    private Long id;
    private String name;
    private List<CustomerUser> users;
}
