package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.ProductDTOReq;
import com.wallet.tienda.dto.request.SaleDTOReq;
import com.wallet.tienda.dto.request.SoldProductDTOReq;
import com.wallet.tienda.model.Product;
import com.wallet.tienda.model.SoldProduct;
import com.wallet.tienda.dto.response.SaleDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.model.Sale;
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
public class SaleService implements ISaleService{

    private final ISaleRepository saleRepository;
    private ISoldProductRepository soldProductRepository;
    private IProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public void save(SaleDTOReq saleDTOReq, ProductDTOReq productDTOReq) throws Exception {
        Sale sale = modelMapper.map(saleDTOReq, Sale.class);
        sale.setPrice(this.calculatePriceAndStock(saleDTOReq,productDTOReq));
        saleRepository.save(sale);
    }

    @Override
    public SaleDTORes getById(Long id) throws IdNotFoundException {
        return modelMapper.map(saleRepository.findById(id).orElseThrow(() -> new IdNotFoundException("La venta con el id " + id + "no se encuentra en base de datos")), SaleDTORes.class);
    }

    @Override
    public Page<SaleDTORes> getAll(Pageable pageable) {
        var sales =  saleRepository.findAll(pageable);
        var salesDtoRes = sales.stream().map(sale -> modelMapper.map(sale, SaleDTORes.class)).toList();
        return new PageImpl<>(salesDtoRes, pageable , salesDtoRes.size());
    }

    @Override
    public void update(SaleDTOReq saleDTOReq) throws IdNotFoundException {
        if (!saleRepository.existsById(saleDTOReq.getId())) {
            throw new IdNotFoundException("La venta con id " + saleDTOReq.getId() + " no existe en base de datos");
        }
        saleRepository.save(modelMapper.map(saleDTOReq, Sale.class));
    }

    @Override
    public void deleteById(Long id) {
        saleRepository.deleteById(id);
    }

    //CALCULA EL PRECIO TOTAL DE LA VENTA REALIZADA y AJUSTA EL STOCK
    public Double calculatePriceAndStock(SaleDTOReq saleDTOReq, ProductDTOReq productDTOReq) throws IdNotFoundException {
        if (saleDTOReq.getSoldProducts() == null) {
            return 0.0;
        }
        double price = 0.0;
        for (SoldProductDTOReq soldProductDTOReq : saleDTOReq.getSoldProducts()) {
            SoldProduct soldProduct = soldProductRepository.findById(soldProductDTOReq.getId()).orElseThrow(
                    () -> new IdNotFoundException("No se encontro el producto comprado")
            );
            Product product = productRepository.findById(productDTOReq.getId()).orElseThrow(
                    () -> new IdNotFoundException("No se encontro el producto")
            );
            if (soldProduct != null && product.getStock() >= soldProduct.getQuantity()) {
                price += soldProduct.getPrice()*soldProduct.getQuantity();
                if (product != null){
                    product.setStock(product.getStock()-soldProduct.getQuantity());
                }
            }else{
                new IdNotFoundException("No hay suficiente stock del producto seleccionado");
            }
        }
        return price;
    }
}