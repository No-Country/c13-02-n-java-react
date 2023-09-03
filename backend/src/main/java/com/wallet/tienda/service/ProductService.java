package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.ProductDTOReq;
import com.wallet.tienda.dto.response.ProductDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import com.wallet.tienda.model.Product;
import com.wallet.tienda.repository.IProductRepository;
import com.wallet.tienda.util.IWordsConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IWordsConverter wordsConverter;
    @Autowired
    private ModelMapper modelMapper;

    //CREA UN PRODUCTO
    @Override
    public void saveProduct(ProductDTOReq productDTO) throws NameExistsException {
        if (productRepository.existsByName(productDTO.getName())) {
            throw new NameExistsException("El nombre " + productDTO.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        //convierte la primer letra de cada palabra en mayúscula
        productDTO.setName(wordsConverter.capitalizeWords(productDTO.getName()));

        productRepository.save(modelMapper.map(productDTO, Product.class));
    }

    //MUESTRA UN PRODUCTO POR ID
    @Override
    public ProductDTORes getProductById(Long productId) throws IdNotFoundException {
        return modelMapper.map(productRepository.findById(productId)
                .orElseThrow(() -> new IdNotFoundException("El id " + productId + " no exite. Ingrese un nuevo id")), ProductDTORes.class);
    }

    //LISTA PRODUCTOS PAGINADOS
    @Override
    public Page<ProductDTORes> getAllProducts(Pageable pageable) {
        var productsDB = productRepository.findAll(pageable);
        var productsDTO = new ArrayList<ProductDTORes>();
        //recorre la lista de productos de la DB, los convierte a DTO y los guarda en una listaDTO
        for (Product product : productsDB) {
            productsDTO.add(modelMapper.map(product, ProductDTORes.class));
        }
        return new PageImpl<>(productsDTO, pageable, productsDTO.size());
    }

    //ACTUALIZA UN PRODUCTO
    @Override
    public void updateProduct(ProductDTOReq productDTO) throws IdNotFoundException, NameExistsException {
        var productDB = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new IdNotFoundException("El id " + productDTO + " no existe. Ingrese un nuevo id"));
        //valida que el nombre del producto no exista y si existe que coincida con el producto encontrado
        if (!productDTO.getName().equals(productDB.getName()) && productRepository.existsByName(productDTO.getName())) {
            throw new NameExistsException("El nombre " + productDTO.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        //convierte la primer letra de cada palabra en mayúscula
        productDTO.setName(wordsConverter.capitalizeWords(productDTO.getName()));
        productRepository.save(modelMapper.map(productDTO, Product.class));
    }

    //ELIMINA UN PRODUCTO
    @Override
    public void deleteProduct(Long productID) {
        productRepository.deleteById(productID);
    }
}