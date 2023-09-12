package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.ProductDTOReq;
import com.wallet.tienda.dto.response.ProductDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import com.wallet.tienda.service.IProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Controlador de productos")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Operation(
            summary = "Guarda un producto",
            description = "Guarda el producto y devuelve un Codigo de estado 201 creado"
    )
    @PostMapping()
    public ResponseEntity<HttpStatus> saveProduct(@Valid @RequestBody ProductDTOReq productDTO) throws NameExistsException {
        productService.saveProduct(productDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Trae un producto",
            description = "Busca un producto por id y devuelve un Codigo de estado 200 y los datos del producto"
    )
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTORes> getProduct(@PathVariable Long productId) throws IdNotFoundException {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @Operation(
            summary = "Traer todos los productos",
            description = "Trae todos los productos de base de datos y devuelve un Codigo de estado 200 y el listado de productos"
    )
    @GetMapping()
    public ResponseEntity<Page<ProductDTORes>> getAllProducts(Pageable pageable){
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @Operation(
            summary = "Actualiza un producto",
            description = "Busca un producto por id y lo actualiza, devuelve un Codigo de estado 204"
    )
    @PutMapping()
    public ResponseEntity<HttpStatus> updateProduct(@Valid @RequestBody ProductDTOReq productDTO) throws IdNotFoundException,
            NameExistsException {
        productService.updateProduct(productDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Elimina un producto",
            description = "Elimina de forma logica un producto por id, devuelve un Codigo de estado 204"
    )
    @DeleteMapping("/{productId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}