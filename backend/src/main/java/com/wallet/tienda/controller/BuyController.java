package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.BuyDTOReq;
import com.wallet.tienda.dto.request.ProductDTOReq;
import com.wallet.tienda.dto.response.BuyDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.service.IBuyService;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@Tag(name = "Controlador de Compra")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/buys")
public class BuyController {
    
    private final IBuyService buyService;

    //@Operation(
            //summary = "Guarda una compra",
            //description = "Guarda la compra y devuelve un Codigo de estado 201 creado"
    //)
    @PostMapping()
    public ResponseEntity<HttpStatus> saveBuy(@Valid @RequestBody BuyDTOReq buyDTOReq, ProductDTOReq productDTOReq) throws Exception {
        buyService.saveBuy(buyDTOReq, productDTOReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //@Operation(
            //summary = "Trae una compra",
            //description = "Busca una compra por id y devuelve un Codigo de estado 200 y los datos de la compra"
    //)
    @GetMapping("/{id}")
    public ResponseEntity<BuyDTORes> getBuy(@PathVariable Long id) throws IdNotFoundException {

        return ResponseEntity.ok(buyService.getBuyById(id));
    }

    //@Operation(
            //summary = "Trae todas las compras",
            //description = "Trae todas las compras de base de datos y devuelve un Codigo de estado 200 y el listado de compras"
    //)
    @GetMapping
    public ResponseEntity<Page<BuyDTORes>> getAllBuys(Pageable pageable) {
        return ResponseEntity.ok(buyService.getBuys(pageable));
    }

    //@Operation(
            //summary = "Actualiza una compra",
            //description = "Busca una compra por id y la actualiza, devuelve un Codigo de estado 204"
    //)
    @PutMapping()
    public ResponseEntity<HttpStatus> updateBuy(@Valid @RequestBody BuyDTOReq buyDTOReq, ProductDTOReq productDTOReq) throws Exception {
        buyService.updateBuy(buyDTOReq, productDTOReq);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@Operation(
            //summary = "Elimina una compra",
            //description = "Elimina de forma logica una compra por id, devuelve un Codigo de estado 204"
    //)
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBuy(@PathVariable Long id){
        buyService.deleteBuy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
