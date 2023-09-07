package com.wallet.tienda.dto.request;

import com.wallet.tienda.model.Provider;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyDTOReq {
    private Long id;
    @DecimalMin(value = "0.0", message = "El valor mínimo ingresado debe ser 0.0")
    @NotNull(message = "No puede estar vacio")
    private Double totalPrice;
    @NotNull(message = "Debe asignar un proovedor")
    private Provider provider;
}
