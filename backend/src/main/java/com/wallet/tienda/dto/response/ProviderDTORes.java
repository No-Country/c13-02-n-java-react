package com.wallet.tienda.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDTORes {

    private Long id;
    private String name;
    private int phone;
    private String email;
    private String web;
}
