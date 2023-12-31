package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.ProductDTOReq;
import com.wallet.tienda.dto.response.ProductDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import com.wallet.tienda.model.Product;
import com.wallet.tienda.repository.IBrandRepository;
import com.wallet.tienda.repository.ICategoryRepository;
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
    private IBrandRepository brandRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IWordsConverter wordsConverter;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Guarda un producto en base de datos
     * @param productDTO dto de producto
     * @throws NameExistsException mensaje de excepcion de nombre de producto ya existe
     */
    @Override
    public void saveProduct(ProductDTOReq productDTO) throws NameExistsException, IdNotFoundException {
        if (!brandRepository.existsById(productDTO.getBrand().getId())){
            throw new IdNotFoundException("La marca ingresada no se encuentra registrada");
        }
        if (!categoryRepository.existsById(productDTO.getCategory().getId())){
            throw new IdNotFoundException("La categoria ingresada no se encuentra registrada");
        }
        if (productRepository.existsByName(productDTO.getName())) {
            throw new NameExistsException("El nombre " + productDTO.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        //convierte la primer letra de cada palabra en mayúscula
        productDTO.setName(wordsConverter.capitalizeWords(productDTO.getName()));

        productRepository.save(modelMapper.map(productDTO, Product.class));
    }

    /**
     * Busca y devuelve un producto por id
     * @param productId numero de id de producto
     * @return dto de producto
     * @throws IdNotFoundException mensaje de excepcion de id de producto no encontrado
     */
    @Override
    public ProductDTORes getProductById(Long productId) throws IdNotFoundException {
        return modelMapper.map(productRepository.findById(productId)
                .orElseThrow(() -> new IdNotFoundException("El id " + productId + " no exite. Ingrese un nuevo id")), ProductDTORes.class);
    }

    /**
     * Devuelve una lista de productos paginados
     * @param pageable configuracion de paginacion
     * @return lista de productos paginados
     */
    @Override
    public Page<ProductDTORes> getAllProducts(Pageable pageable) {
        var productsDB = productRepository.findAll(pageable);
        var productsDTO = new ArrayList<ProductDTORes>();
        //recorre la lista de productos de la DB, los convierte a DTO y los guarda en una listaDTO
        for (Product product : productsDB) {
            productsDTO.add(modelMapper.map(product, ProductDTORes.class));
        }
        return new PageImpl<>(productsDTO, pageable, productsDB.getTotalElements());
    }

    /**
     * Actualiza un producto por id en base de datos
     * @param productDTO dto de producto
     * @throws IdNotFoundException mensaje de excepcion de id de produccto no encontrado
     * @throws NameExistsException mensaje de excepcion de nombre de producto ya exsiste
     */
    @Override
    public void updateProduct(ProductDTOReq productDTO) throws IdNotFoundException, NameExistsException {
        var productDB = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new IdNotFoundException("El id " + productDTO + " no existe. Ingrese un nuevo id"));
        if (!brandRepository.existsById(productDTO.getBrand().getId())){
            throw new IdNotFoundException("La marca ingresada no se encuentra registrada");
        }
        if (!categoryRepository.existsById(productDTO.getCategory().getId())){
            throw new IdNotFoundException("La categoria ingresada no se encuentra registrada");
        }
        //valida que el nombre del producto no exista y si existe que coincida con el producto encontrado
        if (!productDTO.getName().equals(productDB.getName()) && productRepository.existsByName(productDTO.getName())) {
            throw new NameExistsException("El nombre " + productDTO.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        //convierte la primer letra de cada palabra en mayúscula
        productDTO.setName(wordsConverter.capitalizeWords(productDTO.getName()));
        productRepository.save(modelMapper.map(productDTO, Product.class));
    }

    /**
     * Elimina un producto de base de datos
     * @param productID numero de id de producto
     */
    @Override
    public void deleteProduct(Long productID) {
        productRepository.deleteById(productID);
    }
}