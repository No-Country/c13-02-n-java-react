package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.BrandDTOReq;
import com.wallet.tienda.dto.response.BrandDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandService {


    public Page<BrandDTORes> listAllBrands(Pageable pageable);

    public void save(BrandDTOReq brandDTOReq);

    public BrandDTORes searchById(Long id);

    public void update(BrandDTOReq brandDTOReq) throws IdNotFoundException;

    public void delete(Long id);
}