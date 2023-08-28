package com.wallet.tienda.ServicieBrand;

import com.wallet.tienda.model.Brand;

import java.util.List;

public interface BrandService {


    public List<Brand> listallbrands();

    public Brand savebrand(Brand brand);

    public Brand searchbrandbyid (Long id);

    public Brand updatebrand(Brand brand);

    public void Deletebrand(Brand brand);



}
