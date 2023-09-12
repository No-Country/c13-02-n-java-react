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
public class SoldProductService implements ISoldProductService {

    @Autowired
    private ISoldProductRepository soldProductRepository;
    @Autowired
    private ISaleRepository saleRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(SoldProductDTOReq soldProductDTOReq) throws Exception {
        Product product = productRepository.findById(soldProductDTOReq.getProduct().getId()).orElseThrow(
                () -> new IdNotFoundException("No se encontro el producto")
        );
        SoldProduct soldProduct = modelMapper.map(soldProductDTOReq, SoldProduct.class);
        soldProduct.setPrice(soldProduct.getProduct().getPrice());
        calculateStock(soldProduct, product);
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
        return new PageImpl<>(soldProductDTORes, pageable , soldProducts.getTotalElements());
    }

    @Override
    public void deleteById(Long id) throws IdNotFoundException {
        if (!soldProductRepository.existsById(id)) {
            throw new IdNotFoundException("El producto vendido con id " + id + " no se encuentra registrado en base de datos");
        }
        soldProductRepository.deleteById(id);
    }

    //AJUSTA EL STOCK DEL PRODUCTO COMPRADO
    public void calculateStock(SoldProduct soldProduct, Product product) throws StockNotFoundException {
        if (product != null && product.getStock() >= soldProduct.getQuantity()){
            product.setStock(product.getStock()-soldProduct.getQuantity());
            productRepository.save(product);
        }else{
            throw new StockNotFoundException("No hay suficiente stock del producto " + product.getName());
        }
    }
}
