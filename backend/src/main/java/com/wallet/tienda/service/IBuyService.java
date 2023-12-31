package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.BuyDTOReq;
import com.wallet.tienda.dto.response.BuyDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBuyService {

    void saveBuy(BuyDTOReq buyDTOReq) throws Exception;
    BuyDTORes getBuyById(Long buyId) throws IdNotFoundException;
    Page<BuyDTORes> getBuys(Pageable pageable);
    void updateBuy(BuyDTOReq buyDTOReq) throws IdNotFoundException;
    void deleteBuy(Long buyId);
}
