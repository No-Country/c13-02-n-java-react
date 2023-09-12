package com.wallet.tienda.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDTOReq {
    private Long id;
    @NotNull(message = "No puede estar vacio")
    private String name;
    private int phone;
    @Email(message = "Debe contener un formato de email. Ej: example@example.com")
    private String email;
    private String web;
    @Size(max = 500, message = "Debe contener un mínimo de 10 y un máximo de 500 caracteres")
    private String description;

}