package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.ProductDTOReq;
import com.wallet.tienda.dto.request.SaleDTOReq;
import com.wallet.tienda.dto.response.SaleDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.service.ISaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Controlador de ventas")
@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @Operation(
            summary = "Crear venta en base de datos",
            description = "Crea una venta en base de datos y devuelve una respuesta http 201 creado"
    )
    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SaleDTOReq saleDTOReq, ProductDTOReq productDTOReq) throws Exception {
        saleService.save(saleDTOReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Traer ventas",
            description = "Trae las ventas de base de datos y devuelve una respuesta http con las ventas paginadas"
    )
    @GetMapping()
    public ResponseEntity<Page<SaleDTORes>> getAll(Pageable pageable) {
        return ResponseEntity.ok(saleService.getAll(pageable));
    }

    @Operation(
            summary = "Traer una venta",
            description = "Trae una venta por id de base de datos y devuelve una respuesta http con la venta"
    )
    @GetMapping("/{id}")
    public ResponseEntity<SaleDTORes> getById(@PathVariable Long id) throws IdNotFoundException {
        return ResponseEntity.ok(saleService.getById(id));
    }

    @Operation(
            summary = "Actualizar una venta",
            description = "Actualiza una venta de base de datos y devuelve una respuesta http 200 ok si la actualiza"
    )
    @PatchMapping()
    public ResponseEntity<HttpStatus> update(@Valid @RequestBody SaleDTOReq saleDTOReq) throws IdNotFoundException {
        saleService.update(saleDTOReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Eliminar una venta",
            description = "Elimina una venta por id de base de datos y devuelve una respuesta http 200 ok"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        saleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
