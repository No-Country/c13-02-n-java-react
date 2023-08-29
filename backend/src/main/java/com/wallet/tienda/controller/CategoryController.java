package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.CategoryDTOReq;
import com.wallet.tienda.dto.response.CategoryDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import com.wallet.tienda.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@Tag(name = "Controlador de categorias")
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    //@Operation(
    //summary = "Guarda una categoria",
    //description = "Guarda la categoria y devuelve un Codigo de estado 201 creado"
    //)
    @PostMapping()
    public ResponseEntity<HttpStatus> saveCategory(@Valid @RequestBody CategoryDTOReq categoryDTO) throws NameExistsException {
        categoryService.saveCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //@Operation(
    //summary = "Trae una categoria",
    //description = "Busca una categoria por id y devuelve un Codigo de estado 200 y los datos de la categoria"
    //)
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTORes> getCategory(@PathVariable Long categoryId) throws IdNotFoundException {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    //@Operation(
//summary = "Traer todas las categoria",
    // description = "Trae todas las categoria de base de datos y devuelve un Codigo de estado 200 y el listado de categoria"
    //)
    @GetMapping()
    public ResponseEntity<Page<CategoryDTORes>> getAllCategories(Pageable pageable){
        return ResponseEntity.ok(categoryService.getAllCategories(pageable));
    }

    //@Operation(
    //  summary = "Actualiza una categoria",
    // description = "Busca una categoria por id y la actualiza, devuelve un Codigo de estado 204"
    //)
    @PutMapping()
    public ResponseEntity<HttpStatus> updateCategory(@Valid @RequestBody CategoryDTOReq categoryDTO) throws IdNotFoundException,
            NameExistsException {
        categoryService.updateCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@Operation(
    // summary = "Elimina una categoria",
    //  description = "Elimina de forma logica un categoria por id, devuelve un Codigo de estado 204"
    //)
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
