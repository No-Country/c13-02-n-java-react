package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.BoughtProductDTOReq;
import com.wallet.tienda.model.BoughtProduct;
import com.wallet.tienda.model.Buy;
import com.wallet.tienda.dto.request.BuyDTOReq;
import com.wallet.tienda.dto.response.BuyDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.model.Product;
import com.wallet.tienda.repository.IBuyRepository;
import com.wallet.tienda.repository.IBoughtProductRepository;
import com.wallet.tienda.repository.IProductRepository;
import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuyService implements IBuyService{

    private IBuyRepository buyRepository;
    private IBoughtProductRepository boughtProductRepository;
    private IProductRepository productRepository;
    private ModelMapper modelMapper;

    //CREAR UNA COMPRA
    @Override
    public void saveBuy(BuyDTOReq buyDTOReq) throws Exception {
        Buy buy = modelMapper.map(buyDTOReq, Buy.class);
        if (buyDTOReq.getPurchasedProducts() == null) {
            buy.setTotalPrice(0.0);
        }
        double totalPrice = 0.0;
        for (BoughtProductDTOReq boughtProductDTOReq : buyDTOReq.getPurchasedProducts()) {
            BoughtProduct boughtProduct = boughtProductRepository.findById(boughtProductDTOReq.getId()).orElseThrow(
                    () -> new IdNotFoundException("No se encontro el producto comprado")
            );
            buy.setTotalPrice(calculateTotalPrice(boughtProduct, totalPrice));
            calculateStock(boughtProduct);
        }
        buyRepository.save(buy);
    }

    //MUESTRA UNA COMPRA POR ID
    @Override
    public BuyDTORes getBuyById(Long id) throws IdNotFoundException {
        return modelMapper.map(buyRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("El id " + id + " no existe")), BuyDTORes.class);
    }

    //LISTA COMPRAS PAGINADAS
    @Override
    public Page<BuyDTORes> getBuys(Pageable pageable) {
        var buys = buyRepository.findAll(pageable);
        var buysDTO = new ArrayList<BuyDTORes>();

        for (Buy buy : buys) {
            buysDTO.add(modelMapper.map(buy, BuyDTORes.class));
        }
        return new PageImpl<>(buysDTO, pageable, buys.getTotalElements());
    }

    //MODIFICA UNA COMPRA POR ID
    @Override
    public void updateBuy(BuyDTOReq buyDTOReq) throws IdNotFoundException {
        if (!buyRepository.existsById(buyDTOReq.getId())) {
            throw new IdNotFoundException("El id " + buyDTOReq.getId() + " no existe");
        }
        var buyUpdate = modelMapper.map(buyDTOReq, Buy.class);
        buyRepository.save(buyUpdate);
    }

    //ELIMINA UNA COMPRA POR ID
    @Override
    public void deleteBuy(Long id){
        buyRepository.deleteById(id);
    }

    //CALCULA EL PRECIO TOTAL DE LA COMPRA REALIZADA
    public Double calculateTotalPrice(BoughtProduct boughtProduct,double totalPrice) {
        if (boughtProduct != null) {
            totalPrice += boughtProduct.getPrice()*boughtProduct.getQuantity();
        }
        return totalPrice;
    }

    //AJUSTA EL STOCK DEL PRODUCTO COMPRADO
    public void calculateStock(BoughtProduct boughtProduct) throws IdNotFoundException {
        Product product = productRepository.findById(boughtProduct.getProduct().getId()).orElseThrow(
                () -> new IdNotFoundException("No se encontro el producto")
        );
        if (product != null){
            product.setStock(product.getStock()+boughtProduct.getQuantity());
            productRepository.save(product);
        }
    }

}
