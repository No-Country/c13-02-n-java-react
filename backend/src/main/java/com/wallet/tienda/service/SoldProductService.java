package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.SoldProductDTOReq;
import com.wallet.tienda.dto.response.SoldProductDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.model.SoldProduct;
import com.wallet.tienda.repository.IProductRepository;
import com.wallet.tienda.repository.ISaleRepository;
import com.wallet.tienda.repository.ISoldProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoldProductService implements ISoldProductService {

    private final ISoldProductRepository soldProductRepository;
    private final ISaleRepository saleRepository;
    private final IProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public void save(SoldProductDTOReq soldProductDTOReq) throws Exception {
        SoldProduct soldProduct = modelMapper.map(soldProductDTOReq, SoldProduct.class);
        var product = productRepository.findById(soldProduct.getProduct().getId()).orElseThrow(() -> new IdNotFoundException("El producto con id " + soldProduct.getProduct().getId() + " no se encuentra registrado"));
        soldProduct.setPrice(product.getPrice());
        soldProductRepository.save(soldProduct);
    }

    @Override
    public SoldProductDTORes getById(Long id) throws IdNotFoundException {
        var soldProduct = soldProductRepository.findById(id).orElseThrow(() -> new IdNotFoundException("El producto vendido con id " + id + " no se encuentra registrado en base de datos"));
        return modelMapper.map(soldProduct, SoldProductDTORes.class);
    }

    @Override
    public Page<SoldProductDTORes> getAll(Pageable pageable) {
        var soldProducts =  soldProductRepository.findAll(pageable);
        var soldProductDTORes = soldProducts
                .stream()
                .map(sp -> modelMapper.map(sp, SoldProductDTORes.class))
                .toList();
        return new PageImpl<>(soldProductDTORes, pageable , soldProductDTORes.size());
    }

    @Override
    public void deleteById(Long id) throws IdNotFoundException {
        if (!soldProductRepository.existsById(id)) {
            throw new IdNotFoundException("El producto vendido con id " + id + " no se encuentra registrado en base de datos");
        }
        soldProductRepository.deleteById(id);
    }
}
