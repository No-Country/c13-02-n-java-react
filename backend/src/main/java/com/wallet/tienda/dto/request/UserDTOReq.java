package com.wallet.tienda.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOReq {

    private Long id;
    @Size(min = 3, max = 70)
    private String fullName;
    @Size(min = 3, max = 70)
    @Email()
    private String username;
    @Size(min = 8, max = 50)
    private String password;
    @Size(min = 8, max = 50)
    private String confirmPassword;
    private String photoUrl;
    private int phone;
    private String businessName;

}
