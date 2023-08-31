package com.wallet.tienda.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTOReq {
    private Long id;
    @NotNull(message = "No puede estar vacio")
    private String name;

}