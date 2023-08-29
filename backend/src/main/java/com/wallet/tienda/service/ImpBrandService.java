package com.wallet.tienda.service;

import com.wallet.tienda.dto.response.BrandDTORes;
import com.wallet.tienda.model.Brand;
import com.wallet.tienda.repository.RepositoryBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpBrandService implements BrandService{

 @Autowired
    RepositoryBrand repositoryBrand;

    @Override
    public List<BrandDTORes> listallbrands() {
        return repositoryBrand.findAll();
    }

    @Override
    public Brand savebrand(Brand brand) {
        return repositoryBrand.save(brand);
    }

    @Override
    public Brand searchbrandbyid(Long id) {
        return repositoryBrand.findById(id).get();
    }

    @Override
    public Brand updatebrand(Brand brand) {
        return repositoryBrand.save(brand);
    }

    @Override
    public void Deletebrand(Brand brand) {
      repositoryBrand.delete(brand);
    }
}
