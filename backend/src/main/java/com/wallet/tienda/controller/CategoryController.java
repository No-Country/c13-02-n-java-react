package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.CategoryDTOReq;
import com.wallet.tienda.dto.response.CategoryDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import com.wallet.tienda.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase controladora de categorias
 * @Autor Damian Della Corte
 */
@Tag(name = "Controlador de categorias")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    /**
     * Guarda una categoria en base de datos
     * @param categoryDTO dto de categoria
     * @return respuesta http cpn codigo de estado creado
     * @throws NameExistsException mensaje de excepcion de nombre de categoria ya existe
     */
    @Operation(
    summary = "Guarda una categoria",
    description = "Guarda la categoria y devuelve un Codigo de estado 201 creado"
    )
    @PostMapping()
    public ResponseEntity<HttpStatus> saveCategory(@Valid @RequestBody CategoryDTOReq categoryDTO) throws NameExistsException {
        categoryService.saveCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Busca una categoria por id
     * @param categoryId numero de id de categoria
     * @return respuesta http con dto de categoria
     * @throws IdNotFoundException mensaje de excepcion de id de categoria no encontrado
     */
    @Operation(
    summary = "Trae una categoria",
    description = "Busca una categoria por id y devuelve un Codigo de estado 200 y los datos de la categoria"
    )
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTORes> getCategory(@PathVariable Long categoryId) throws IdNotFoundException {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    /**
     * Trae todas las categorias de base de datos
     * @param pageable configuracion de paginacion
     * @return respuesta http con lista de dto de categorias paginado
     */
    @Operation(
summary = "Traer todas las categoria",
     description = "Trae todas las categorias de base de datos y devuelve un Codigo de estado 200 y el listado de categoria"
    )
    @GetMapping()
    public ResponseEntity<Page<CategoryDTORes>> getAllCategories(Pageable pageable){
        return ResponseEntity.ok(categoryService.getAllCategories(pageable));
    }

    /**
     * Actualiza una categoria en base de datos
     * @param categoryDTO dto de categoria
     * @return respuesta http con cpdigo de estado sin contenido
     * @throws IdNotFoundException mensaje de excepcion de id no encontrado
     * @throws NameExistsException mensaje de excepcion de nombre ya existe en base de datos
     */
    @Operation(
      summary = "Actualiza una categoria",
     description = "Actualiza una categoria y la actualiza, devuelve un Codigo de estado 204"
    )
    @PutMapping()
    public ResponseEntity<HttpStatus> updateCategory(@Valid @RequestBody CategoryDTOReq categoryDTO) throws IdNotFoundException,
            NameExistsException {
        categoryService.updateCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Elimina una categoria por id
     * @param categoryId numero de id de categoria
     * @return respuesta http con codigo de estado sin contenido
     */
    @Operation(
     summary = "Elimina una categoria",
      description = "Elimina una categoria por id, devuelve un Codigo de estado 204"
    )
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
