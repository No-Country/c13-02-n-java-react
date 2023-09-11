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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Clase de servicio que realiza el crud de productos comprados
 * @Autor Damian Della Corte
 */
@Service
@RequiredArgsConstructor
public class BoughtProductService implements IBoughtProductService {

    private IBoughtProductRepository repository;
    private IBuyRepository buyRepository;
    private IProductRepository productRepository;
    private ModelMapper modelMapper;

    /**
     * Metodo para guardar un producto comprados y sus detalles, devuelve una excepcion de id no encotraqdo si no existe la compra o el producto relacionado
     * @param boughtProductDTO dto con la informacion del producto comprado
     * @throws IdNotFoundException mensaje de excepcion para id no encontrado
     */
    @Override
    public void saveBoughtProduct(BoughtProductDTOReq boughtProductDTO) throws IdNotFoundException {
        if (!buyRepository.existsById(boughtProductDTO.getBuy().getId())) {
            throw new IdNotFoundException("La compra ingresada no se encuentra registrada");
        }
        var productDB = productRepository.findById(boughtProductDTO.getProduct().getId())
                .orElseThrow(() -> new IdNotFoundException("El id " + boughtProductDTO.getProduct().getId() + " no existe"));
        repository.save(modelMapper.map(boughtProductDTO, BoughtProduct.class));
    }

    /**
     * Metodo para traer todos los productos comprados paginados
     * @param pageable configuracion de paginacion por default o suministrada por el usuario
     * @return lista de productos paginados
     */
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

    /**
     * Metodo para traer un producto comprado de base de datos por id
     * @param id numero de identificacion
     * @return dto de producto comprado
     * @throws IdNotFoundException mensaje de excepcion para id no encontrado
     */
    @Override
    public BoughtProductDTORes getBoughtProductById(Long id) throws IdNotFoundException {
        var boughtProduct = repository.findById(id).orElseThrow(
                () -> new IdNotFoundException("El producto con el id ingresado no se encuentra registado"));
        return modelMapper.map(boughtProduct, BoughtProductDTORes.class);
    }

    /**
     * Meodo para eliminar un producto comprado por id
     * @param id numero de identificacion
     */
    @Override
    public void deleteBoughtProduct(Long id) {
        repository.deleteById(id);
    }

}
