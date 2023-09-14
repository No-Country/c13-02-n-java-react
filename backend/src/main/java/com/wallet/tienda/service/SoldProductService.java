package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.SoldProductDTOReq;
import com.wallet.tienda.dto.response.SoldProductDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.model.SoldProduct;
import com.wallet.tienda.repository.IProductRepository;
import com.wallet.tienda.repository.ISoldProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SoldProductService implements ISoldProductService {

    @Autowired
    private ISoldProductRepository soldProductRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Guarda un producto vendido en BD
     * @param soldProductDTOReq dto de producto vendido
     * @throws Exception mensaje de excepcion
     */
    @Override
    public void save(SoldProductDTOReq soldProductDTOReq) throws Exception {
        SoldProduct soldProduct = modelMapper.map(soldProductDTOReq, SoldProduct.class);
        var product = productRepository.findById(soldProduct.getProduct().getId()).orElseThrow(() -> new IdNotFoundException("El producto con id " + soldProduct.getProduct().getId() + " no se encuentra registrado"));
        soldProduct.setPrice(product.getPrice());
        soldProductRepository.save(soldProduct);
    }

    /**
     * Busca y devuelve un producto vendido por id
     * @param id numero de id de producto vendido
     * @return dto de producto vendido
     * @throws IdNotFoundException mensaje de excepcin de id de producto vendido no encontrado
     */
    @Override
    public SoldProductDTORes getById(Long id) throws IdNotFoundException {
        var soldProduct = soldProductRepository.findById(id).orElseThrow(() -> new IdNotFoundException("El producto vendido con id " + id + " no se encuentra registrado en base de datos"));
        return modelMapper.map(soldProduct, SoldProductDTORes.class);
    }

    /**
     * Devuelve una lista de productos vendidos paginada
     * @param pageable configuracion de paginado
     * @return lista de productos vendidos paginada
     */
    @Override
    public Page<SoldProductDTORes> getAll(Pageable pageable) {
        var soldProducts =  soldProductRepository.findAll(pageable);
        var soldProductDTORes = soldProducts
                .stream()
                .map(sp -> modelMapper.map(sp, SoldProductDTORes.class))
                .toList();
        return new PageImpl<>(soldProductDTORes, pageable , soldProducts.getTotalElements());
    }

    /**
     * Elimina un producto vendido por id
     * @param id numero de id de producto vendido
     * @throws IdNotFoundException mensaje de excepcion de id de producto no encontrado
     */
    @Override
    public void deleteById(Long id) throws IdNotFoundException {
        if (!soldProductRepository.existsById(id)) {
            throw new IdNotFoundException("El producto vendido con id " + id + " no se encuentra registrado en base de datos");
        }
        soldProductRepository.deleteById(id);
    }
}
