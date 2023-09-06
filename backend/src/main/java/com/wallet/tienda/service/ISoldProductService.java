package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.SoldProductDTOReq;
import com.wallet.tienda.dto.response.SoldProductDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISoldProductService {
    void save(SoldProductDTOReq soldProductDTOReq) throws Exception;
    SoldProductDTORes getById(Long id) throws IdNotFoundException;
    Page<SoldProductDTORes> getAll(Pageable pageable);
    void update(SoldProductDTOReq soldProductDTOReq) throws IdNotFoundException;
    void deleteById(Long id) throws IdNotFoundException;
}
