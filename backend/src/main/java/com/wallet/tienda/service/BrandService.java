package com.wallet.tienda.service;

import com.wallet.tienda.dto.response.BrandDTORes;
import com.wallet.tienda.model.Brand;

import java.util.List;

public interface BrandService {


    public List<BrandDTORes> listallbrands();

    public Brand savebrand(Brand brand);

    public Brand searchbrandbyid (Long id);

    public Brand updatebrand(Brand brand);

    public void Deletebrand(Brand brand);



}
