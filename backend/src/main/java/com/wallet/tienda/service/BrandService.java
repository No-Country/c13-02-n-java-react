package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.BrandDTOReq;
import com.wallet.tienda.dto.response.BrandDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.model.Brand;
import com.wallet.tienda.repository.IBrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BrandService implements IBrandService{

    @Autowired
    private IBrandRepository brandRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<BrandDTORes> listAllBrands(Pageable pageable) {
        var brandRep = brandRepository.findAll(pageable);
        var brandDTORes = brandRep.stream().map((user) -> modelMapper.map(user, BrandDTORes.class)).toList();
        return new PageImpl<>(brandDTORes, pageable, brandRep.getTotalElements());
    }

    @Override
    public void save(BrandDTOReq brandDTOReq) {
        brandRepository.save(modelMapper.map(brandDTOReq, Brand.class));
    }

    @Override
    public BrandDTORes searchById(Long id) {
        return modelMapper.map(brandRepository.findById(id).get(), BrandDTORes.class);
    }

    @Override
    public void update(BrandDTOReq brandDTOReq) throws IdNotFoundException {
        if (!brandRepository.existsById(brandDTOReq.getId())){
            throw new IdNotFoundException("La marca no se encuentra registrada");
        }
        var brand = modelMapper.map(brandDTOReq, Brand.class);
        brand.setId(brandDTOReq.getId());
        brandRepository.save(brand);
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }
}
