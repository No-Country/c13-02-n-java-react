package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.BrandDTOReq;
import com.wallet.tienda.dto.response.BrandDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandService {


    public Page<BrandDTORes> listAllBrands(Pageable pageable);

    public void save(BrandDTOReq brandDTOReq) throws NameExistsException;

    public BrandDTORes searchById(Long id) throws IdNotFoundException;

    public void update(BrandDTOReq brandDTOReq) throws NameExistsException, IdNotFoundException;

    public void delete(Long id);
}