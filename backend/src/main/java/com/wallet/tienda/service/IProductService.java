package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.ProductDTOReq;
import com.wallet.tienda.dto.response.ProductDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    void saveProduct(ProductDTOReq productDTO) throws NameExistsException, IdNotFoundException;
    ProductDTORes getProductById(Long productId) throws IdNotFoundException;

    Page<ProductDTORes> getAllProducts(Pageable pageable);
    void updateProduct(ProductDTOReq productDTO) throws IdNotFoundException, NameExistsException;
    void deleteProduct(Long productID);
}
