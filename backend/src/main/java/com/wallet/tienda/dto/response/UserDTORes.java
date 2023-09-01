package com.wallet.tienda.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTORes {

    private Long id;
    private String fullName;
    private String username;
    private String photoUrl;
    private int phone;
    private String businessName;
}
