package com.wallet.tienda.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOReq {

    private Long id;
    @Size(min = 3, max = 70, message = "debe contener un minimo de 3 y un maximo de 70 caracteres")
    private String fullName;
    @Size(min = 3, max = 70, message = "debe contener un minimo de 3 y un maximo de 70 caracteres")
    @Email()
    private String username;
    @Size(min = 8, max = 50, message = "debe contener un minimo de 8 y un maximo de 50 caracteres")
    private String password;
    @Size(min = 8, max = 50, message = "debe contener un minimo de 8 y un maximo de 50 caracteres")
    private String confirmPassword;
    private String photoUrl;
    @Pattern(regexp = "^[0-9]+$", message = "Debe contener solo numeros")
    private String phone;
    private String businessName;

}
