package com.wallet.tienda.dto.response;

import com.wallet.tienda.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTORes {

    private Long id;
    private String name;
}
