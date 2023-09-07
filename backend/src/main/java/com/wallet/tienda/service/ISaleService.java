package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.ProductDTOReq;
import com.wallet.tienda.dto.request.SaleDTOReq;
import com.wallet.tienda.dto.response.SaleDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISaleService {
    void save(SaleDTOReq saleDTOReq, ProductDTOReq productDTOReq) throws Exception;
    SaleDTORes getById(Long id) throws IdNotFoundException;
    Page<SaleDTORes> getAll(Pageable pageable);
    void update(SaleDTOReq saleDTOReq) throws IdNotFoundException;
    void deleteById(Long id);
}
