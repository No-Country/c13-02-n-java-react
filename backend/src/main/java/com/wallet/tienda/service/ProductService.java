package com.wallet.tienda.service;

import com.wallet.tienda.model.Product;

public class ProductService {
    @Autowired
    private ModelMapper modelMapper;

    //    CREA UN PRODUCTO
    @Override
    public void createProduct(ProductDTOReq productDTO) throws NameExistsException {
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
    public Page<ProductDTORes> getAllProductS(Pageable pageable) {
        var productsBD = productRepository.findAll(pageable);
        var productsDTO = new ArrayList<ProductDTORes>();
        //recorre la lista de productos de la BD, los convierte a DTO y los guarda en una listaDTO
        for (Product product : productsBD) {
            productsDTO.add(modelMapper.map(product, ProductDTORes.class));
        }
        return new PageImpl<>(productsDTO, pageable, productsDTO.size());
    }

    //ACTUALIZA UN PRODUCTO
    @Override
    public void updateProduct(ProductDTOReq productDTO) throws IdNotFoundException, NameExistsException {
        var productBD = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new IdNotFoundException("El id " + productDTO + " no existe. Ingrese un nuevo id"));
        //valida que el nombre del juego no exista y si existe que coincida con el juego encontrado
        if (!productDTO.getName().equals(productBD.getName()) && productRepository.existsByName(productDTO.getName())) {
            throw new NameExistsException("El nombre " + productDTO.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        //convierte la primer letra de cada palabra en mayúscula
        productDTO.setName(wordsConverter.capitalizeWords(productDTO.getName()));
        productRepository.save(modelMapper.map(productDTO, Product.class));
    }

    //ELIMINA UN PRODUCTO
    @Override
    public void deleteproduct(Long productID) {
        productRepository.deleteById(productID);
    }
}