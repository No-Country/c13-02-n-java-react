package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.CategoryDTOReq;
import com.wallet.tienda.dto.response.CategoryDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import com.wallet.tienda.model.Category;
import com.wallet.tienda.repository.ICategoryRepository;
import com.wallet.tienda.util.IWordsConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private  ICategoryRepository categoryRepository;
    private  IWordsConverter wordsConverter;
    private  ModelMapper modelMapper;


    //CREA UNA CATEGORIA
    @Override
    public void saveCategory(CategoryDTOReq categoryDTO) throws NameExistsException {
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            throw new NameExistsException("El nombre " + categoryDTO.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        //convierte la primer letra de cada palabra en mayúscula
        categoryDTO.setName(wordsConverter.capitalizeWords(categoryDTO.getName()));

        categoryRepository.save(modelMapper.map(categoryDTO, Category.class));
    }

    //MUESTRA UNA CATEGORIA POR ID
    @Override
    public CategoryDTORes getCategoryById(Long categoryId) throws IdNotFoundException {
        return modelMapper.map(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IdNotFoundException("El id " + categoryId + " no existe. Ingrese un nuevo id")), CategoryDTORes.class);
    }

    //LISTA CATEGORIAS PAGINADAS
    @Override
    public Page<CategoryDTORes> getAllCategories(Pageable pageable) {
        var categoriesDB = categoryRepository.findAll(pageable);
        var categoriesDTO = new ArrayList<CategoryDTORes>();
        //recorre la lista de categorias de la DB, los convierte a DTO y los guarda en una listaDTO
        for (Category category : categoriesDB) {
            categoriesDTO.add(modelMapper.map(category, CategoryDTORes.class));
        }
        return new PageImpl<>(categoriesDTO, pageable, categoriesDTO.size());
    }

    //ACTUALIZA UNA CATEGORIA
    @Override
    public void updateCategory(CategoryDTOReq categoryDTO) throws IdNotFoundException, NameExistsException {
        var categoryDB = categoryRepository.findById(categoryDTO.getId())
                .orElseThrow(() -> new IdNotFoundException("El id " + categoryDTO + " no existe. Ingrese un nuevo id"));
        //valida que el nombre de la categoria no exista y si existe que coincida con la categoria encontrada
        if (!categoryDTO.getName().equals(categoryDB.getName()) && categoryRepository.existsByName(categoryDTO.getName())) {
            throw new NameExistsException("El nombre " + categoryDTO.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        //convierte la primer letra de cada palabra en mayúscula
        categoryDTO.setName(wordsConverter.capitalizeWords(categoryDTO.getName()));
        categoryRepository.save(modelMapper.map(categoryDTO, Category.class));
    }

    //ELIMINA UNA CATEGORIA
    @Override
    public void deleteCategory(Long categoryID) {
        categoryRepository.deleteById(categoryID);
    }
}
