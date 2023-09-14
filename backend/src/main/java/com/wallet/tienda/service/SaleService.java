package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.SaleDTOReq;
import com.wallet.tienda.dto.request.SoldProductDTOReq;
import com.wallet.tienda.model.SoldProduct;
import com.wallet.tienda.dto.response.SaleDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.model.Sale;
import com.wallet.tienda.repository.IProductRepository;
import com.wallet.tienda.repository.ISaleRepository;
import com.wallet.tienda.repository.ISoldProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SaleService implements ISaleService{

    @Autowired
    private ISaleRepository saleRepository;
    @Autowired
    private ISoldProductRepository soldProductRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Guarda una venta en base de datos
     * @param saleDTOReq dto de venta
     * @throws Exception mensaje de excepcion
     */
    @Override
    public void save(SaleDTOReq saleDTOReq) throws Exception {
        Sale sale = modelMapper.map(saleDTOReq, Sale.class);
        sale.setPrice(this.calculatePrice(saleDTOReq));
        saleRepository.save(sale);
    }

    /**
     * Busca y devuelve una venta de BD por id
     * @param id numero de id de venta
     * @return dto de venta
     * @throws IdNotFoundException mensaje de excepcion de id no encontrado
     */
    @Override
    public SaleDTORes getById(Long id) throws IdNotFoundException {
        return modelMapper.map(saleRepository.findById(id).orElseThrow(() -> new IdNotFoundException("La venta con el id " + id + "no se encuentra en base de datos")), SaleDTORes.class);
    }

    /**
     * Devuelve una lista de ventas paginadas
     * @param pageable configuracion de paginacion
     * @return lista de ventas paginada
     */
    @Override
    public Page<SaleDTORes> getAll(Pageable pageable) {
        var sales =  saleRepository.findAll(pageable);
        var salesDtoRes = sales.stream().map(sale -> modelMapper.map(sale, SaleDTORes.class)).toList();
        return new PageImpl<>(salesDtoRes, pageable , sales.getTotalElements());
    }

    /**
     * Busca y actualiza una venta por id
     * @param saleDTOReq dto de venta
     * @throws IdNotFoundException mensaje de excepcion de id no encontrado
     */
    @Override
    public void update(SaleDTOReq saleDTOReq) throws IdNotFoundException {
        if (!saleRepository.existsById(saleDTOReq.getId())) {
            throw new IdNotFoundException("La venta con id " + saleDTOReq.getId() + " no existe en base de datos");
        }
        saleRepository.save(modelMapper.map(saleDTOReq, Sale.class));
    }

    /**
     * Elimina una venta por id
     * @param id numero de id de venta
     */
    @Override
    public void deleteById(Long id) {
        saleRepository.deleteById(id);
    }

    /** CALCULA EL PRECIO TOTAL DE LA COMPRA REALIZADA
     * @param saleDTOReq dto de venta
     * @return precio total de producto vendido
     * @throws IdNotFoundException mensaje de excepcion de id de producto comprado no encontrado
     */
    public Double calculatePrice(SaleDTOReq saleDTOReq) throws IdNotFoundException {
        if (saleDTOReq.getSoldProducts() == null) {
            return 0.0;
        }
        double price = 0.0;
        for (SoldProductDTOReq soldProductDTOReq : saleDTOReq.getSoldProducts()) {
            SoldProduct soldProduct = soldProductRepository.findById(soldProductDTOReq.getId()).orElseThrow(
                    () -> new IdNotFoundException("No se encontro el producto comprado")
            );
            if (soldProduct != null) {
                price += soldProduct.getPrice()*soldProduct.getQuantity();
            }
        }
        return price;
    }
}
