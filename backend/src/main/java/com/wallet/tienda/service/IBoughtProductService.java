package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.BoughtProductDTOReq;
import com.wallet.tienda.dto.response.BoughtProductDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface IBoughtProductService {

    void saveBoughtProduct(BoughtProductDTOReq ticket) throws IdNotFoundException;
    Page<BoughtProductDTORes> getAllBoughtProducts(Pageable pageable);
    BoughtProductDTORes getById(Long id) throws IdNotFoundException;
    void delete(Long id);

}
