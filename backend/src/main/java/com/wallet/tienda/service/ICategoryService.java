package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.CategoryDTOReq;
import com.wallet.tienda.dto.response.CategoryDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {

    void saveCategory(CategoryDTOReq categoryDTO) throws NameExistsException;
    CategoryDTORes getCategoryById(Long categoryId) throws IdNotFoundException;

    Page<CategoryDTORes> getAllCategories(Pageable pageable);

}
