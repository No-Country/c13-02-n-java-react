package com.wallet.tienda.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecoverPasswordDTOReq {

    @Email(message = "debe ingresar un email valido. Ej: example@example.com")
    @NotNull(message = "No puede estar vacio")
    private String email;
}
