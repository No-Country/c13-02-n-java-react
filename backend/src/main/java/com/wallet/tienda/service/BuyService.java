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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Clase de servicio de compras
 * @Autor Damian Della Corte
 */
@Service
public class BuyService implements IBuyService{

    @Autowired
    private IBuyRepository buyRepository;
    @Autowired
    private IBoughtProductRepository boughtProductRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Guarda una compra
     * @param buyDTOReq dto de compra
     * @throws Exception mensaje de excepcion de id de producto no encontrado
     */
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

    /**
     * Devuelve una compra por id
     * @param id numero de id de compra
     * @return dto de compra
     * @throws IdNotFoundException mensaje de excepcion de id de compra no encontrado
     */
    @Override
    public BuyDTORes getBuyById(Long id) throws IdNotFoundException {
        return modelMapper.map(buyRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("El id " + id + " no existe")), BuyDTORes.class);
    }

    /**
     * Devuelve lista de compras paginadas
     * @param pageable configuracion de paginacion
     * @return lista de compras pagindas
     */
    @Override
    public Page<BuyDTORes> getBuys(Pageable pageable) {
        var buys = buyRepository.findAll(pageable);
        var buysDTO = new ArrayList<BuyDTORes>();

        for (Buy buy : buys) {
            buysDTO.add(modelMapper.map(buy, BuyDTORes.class));
        }
        return new PageImpl<>(buysDTO, pageable, buys.getTotalElements());
    }

    /**
     * Actualiza una compra por id
     * @param buyDTOReq dto de compra
     * @throws IdNotFoundException mensaje de excepcion de id de compra no encontrado
     */
    @Override
    public void updateBuy(BuyDTOReq buyDTOReq) throws IdNotFoundException {
        if (!buyRepository.existsById(buyDTOReq.getId())) {
            throw new IdNotFoundException("El id " + buyDTOReq.getId() + " no existe");
        }
        var buyUpdate = modelMapper.map(buyDTOReq, Buy.class);
        buyRepository.save(buyUpdate);
    }

    /**
     * Elimina una compra por id
     * @param id numero de id de la compra
     */
    @Override
    public void deleteBuy(Long id){
        buyRepository.deleteById(id);
    }

    /**
     * Devuelve el precio total de la compra
     * @param boughtProduct producto comprado
     * @param totalPrice precio total
     * @return precio total de la compra
     */
    public Double calculateTotalPrice(BoughtProduct boughtProduct,double totalPrice) {
        if (boughtProduct != null) {
            totalPrice += boughtProduct.getPrice()*boughtProduct.getQuantity();
        }
        return totalPrice;
    }

    /**
     * Suma el stock de la compra al stock del producto
     * @param boughtProduct producto comprado
     * @throws IdNotFoundException mensaje de excepcion de id de producto comprado no encontrado
     */
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
