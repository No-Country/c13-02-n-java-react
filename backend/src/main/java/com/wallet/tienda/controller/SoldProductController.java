package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.SoldProductDTOReq;
import com.wallet.tienda.dto.response.SoldProductDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.service.ISoldProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Controlador de productos vendidos")
@RestController
@RequestMapping("/api/v1/sold-products")
public class SoldProductController {

    @Autowired
    private ISoldProductService soldProductService;

    @Operation(
            summary = "Guardar un producto vendido",
            description = "Guarda en base de datos un producto vendido, devuelve una respuesta http con estado 201 creado"
    )
    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SoldProductDTOReq soldProductDTOReq) throws Exception {
        soldProductService.save(soldProductDTOReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Traer todos los productos vendidos",
            description = "Trae de base de datos todos los productos vendidos, devuelve una respuesta http con los productos vendidos paginados"
    )
    @GetMapping()
    public ResponseEntity<Page<SoldProductDTORes>> getAll(Pageable pageable) {
        return ResponseEntity.ok(soldProductService.getAll(pageable));
    }

    @Operation(
            summary = "Traer un producto vendido por id",
            description = "Trae de base de datos un producto vendido, devuelve una respuesta http con el producto"
    )
    @GetMapping("/{id}")
    public ResponseEntity<SoldProductDTORes> getById(@PathVariable Long id) throws IdNotFoundException {
        return ResponseEntity.ok(soldProductService.getById(id));
    }

    @Operation(
            summary = "Eliminar un producto vendido",
            description = "Elimina de base de datos un producto vendido, devuelve una respuesta http con estado 200 ok"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws IdNotFoundException {
        soldProductService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
