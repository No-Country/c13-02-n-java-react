package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.BuyDTOReq;
import com.wallet.tienda.dto.request.ProductDTOReq;
import com.wallet.tienda.dto.response.BuyDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.service.IBuyService;

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
 * Clase controladora de compras
 * @Autor DAmian Della Corte
 */
@Tag(name = "Controlador de compras")
@RestController
@RequestMapping("/api/v1/buys")
public class BuyController {

    @Autowired
    private IBuyService buyService;

    /**
     * Metodo para guardar una compra en base de datos
     * @param buyDTOReq dto de compra
     * @return respuesta http con codigo de estado 201 creado
     * @throws Exception mensaje de excepcion
     */
    @Operation(
            summary = "Guarda una compra",
            description = "Guarda la compra y devuelve un Codigo de estado 201 creado"
    )
    @PostMapping()
    public ResponseEntity<HttpStatus> saveBuy(@Valid @RequestBody BuyDTOReq buyDTOReq) throws Exception {
        buyService.saveBuy(buyDTOReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Busca una compra por id
     * @param id numero de id
     * @return respuesta http con dto de compra
     * @throws IdNotFoundException mensaje de excepcion de id no encontrado
     */
    @Operation(
            summary = "Trae una compra",
            description = "Busca una compra por id y devuelve un Codigo de estado 200 y los datos de la compra"
    )
    @GetMapping("/{id}")
    public ResponseEntity<BuyDTORes> getBuy(@PathVariable Long id) throws IdNotFoundException {

        return ResponseEntity.ok(buyService.getBuyById(id));
    }

    /**
     * Trae todas las compras de base de datos
     * @param pageable configuracion de paginacion
     * @return respuesta http con lista de compras paginadas
     */
    @Operation(
            summary = "Trae todas las compras",
            description = "Trae todas las compras de base de datos y devuelve un Codigo de estado 200 y el listado de compras"
    )
    @GetMapping
    public ResponseEntity<Page<BuyDTORes>> getAllBuys(Pageable pageable) {
        return ResponseEntity.ok(buyService.getBuys(pageable));
    }

    /**
     * Busca una compra por id y la actualiza
     * @param buyDTOReq dto de compra
     * @param productDTOReq dto de producto
     * @return resouesta http con codigo de estado sin contenido
     * @throws Exception mensaje de excepcion
     */
    @Operation(
            summary = "Actualiza una compra",
            description = "Busca una compra por id y la actualiza, devuelve un Codigo de estado 204"
    )
    @PutMapping()
    public ResponseEntity<HttpStatus> updateBuy(@Valid @RequestBody BuyDTOReq buyDTOReq, ProductDTOReq productDTOReq) throws Exception {
        buyService.updateBuy(buyDTOReq);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Elimina una compra por id
     * @param id numero de id
     * @return respuesta http con codigo de estado sin contenido
     */
    @Operation(
            summary = "Elimina una compra",
            description = "Elimina de forma logica una compra por id, devuelve un Codigo de estado 204"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBuy(@PathVariable Long id){
        buyService.deleteBuy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
