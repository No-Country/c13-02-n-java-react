package com.wallet.tienda.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordDTOReq {

    @Size(min = 8, max = 50, message = "debe contener un minimo de 8 y un maximo de 50 caracteres")
    private String password;
    @Size(min = 8, max = 50, message = "debe contener un minimo de 8 y un maximo de 50 caracteres")
    private String confirmPassword;
    private String token;
}
