package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.BoughtProductDTOReq;
import com.wallet.tienda.dto.response.BoughtProductDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.model.BoughtProduct;
import com.wallet.tienda.repository.IBoughtProductRepository;
import com.wallet.tienda.repository.IBuyRepository;
import com.wallet.tienda.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BoughtProductService implements IBoughtProductService {

    private IBoughtProductRepository repository;
    private IBuyRepository buyRepository;
    private IProductRepository productRepository;
    private ModelMapper modelMapper;

    @Override
    public void saveBoughtProduct(BoughtProductDTOReq boughtProductDTO) throws IdNotFoundException {
        if (!buyRepository.existsById(boughtProductDTO.getBuy().getId())) {
            throw new IdNotFoundException("La compra ingresada no se encuentra registrada");
        }
        var productDB = productRepository.findById(boughtProductDTO.getProduct().getId())
                .orElseThrow(() -> new IdNotFoundException("El id " + boughtProductDTO.getProduct().getId() + " no existe"));
        repository.save(modelMapper.map(boughtProductDTO, BoughtProduct.class));
    }

    @Override
    public Page<BoughtProductDTORes> getAllBoughtProducts(Pageable pageable) {
        var boughtProducts = repository.findAll(pageable);
        var productsDTO = new ArrayList<BoughtProductDTORes>();
        for (BoughtProduct boughtProduct : boughtProducts) {
            var productDTO = modelMapper.map(boughtProduct, BoughtProductDTORes.class);
            productsDTO.add(productDTO);
        }
        return new PageImpl<>(productsDTO, pageable, boughtProducts.getTotalElements());
    }

    @Override
    public BoughtProductDTORes getBoughtProductById(Long id) throws IdNotFoundException {
        var boughtProduct = repository.findById(id).orElseThrow(
                () -> new IdNotFoundException("El producto con el id ingresado no se encuentra registado"));
        return modelMapper.map(boughtProduct, BoughtProductDTORes.class);
    }

    @Override
    public void deleteBoughtProduct(Long id) {
        repository.deleteById(id);
    }

}
