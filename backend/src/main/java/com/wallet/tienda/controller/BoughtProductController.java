package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.BoughtProductDTOReq;
import com.wallet.tienda.dto.response.BoughtProductDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.service.IBoughtProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@Tag(name = "Controlador de productos vendidos")
@RestController
@RequestMapping("api/v1/bought-product")
@RequiredArgsConstructor
public class BoughtProductController {

    private final IBoughtProductService boughtProductService;

    //@Operation(
    //summary = "Guarda un producto vendido",
    //description = "Guarda el producto y devuelve un Codigo de estado 201 creado"
    //)
    @PostMapping
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody BoughtProductDTOReq boughtProductDTO) throws IdNotFoundException {
        boughtProductService.saveBoughtProduct(boughtProductDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //@Operation(
            //summary = "Trae un producto vendido",
           // description = "Busca un producto por id y devuelve un Codigo de estado 200 y los datos del producto"
   // )
    @GetMapping("/{productId}")
    public ResponseEntity<BoughtProductDTORes> getById(@PathVariable Long productId) throws IdNotFoundException {
        return ResponseEntity.ok().body(boughtProductService.getBoughtProductById(productId));
    }

    //@Operation(
            //summary = "Traer todos los productos vendidos",
            //description = "Trae todos los productos de base de datos y devuelve un Codigo de estado 200 y el listado de productos"
    //)
    @GetMapping
    public ResponseEntity<Page<BoughtProductDTORes>> getAll(Pageable pageable){
        return ResponseEntity.ok().body(boughtProductService.getAllBoughtProducts(pageable));
    }

    //@Operation(
            //summary = "Elimina un producto vendido",
            //description = "Elimina de forma logica un producto por id, devuelve un Codigo de estado 204"
    //)
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long productId) {
        boughtProductService.deleteBoughtProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
