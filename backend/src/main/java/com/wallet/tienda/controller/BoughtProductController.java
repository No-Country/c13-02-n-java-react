package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.BoughtProductDTOReq;
import com.wallet.tienda.dto.response.BoughtProductDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.service.IBoughtProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase controladora de productos comprados
 * @Autor Damian Della Corte
 */
@Tag(name = "Controlador de productos comprados")
@RestController
@RequestMapping("api/v1/bought-products")
@RequiredArgsConstructor
public class BoughtProductController {

    private final IBoughtProductService boughtProductService;

    /**
     * Metodo para guardar un producto comprado y sus detalles, que devuelve un codigo de estado 200 ok
     * @param boughtProductDTO dto de producto comprado
     * @return Respuesta de estado http
     * @throws IdNotFoundException mensaje de excepcion de id no encontrado
     */
    @Operation(
    summary = "Guarda un producto vendido",
    description = "Guarda el producto y devuelve un Codigo de estado 201 creado"
    )
    @PostMapping
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody BoughtProductDTOReq boughtProductDTO) throws IdNotFoundException {
        boughtProductService.saveBoughtProduct(boughtProductDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Metodo que busca por id y responde con un dto de producto comprado, puede arrojar una excepcion de id no encontrado
     * @param productId numero de id del producto comprado
     * @return respuesta http con el dto
     * @throws IdNotFoundException mensaje de excepcion de id no encontrado
     */
    @Operation(
            summary = "Trae un producto vendido",
            description = "Busca un producto por id y devuelve un Codigo de estado 200 y los datos del producto"
    )
    @GetMapping("/{productId}")
    public ResponseEntity<BoughtProductDTORes> getById(@PathVariable Long productId) throws IdNotFoundException {
        return ResponseEntity.ok().body(boughtProductService.getBoughtProductById(productId));
    }

    /**
     * Metodo para traer de base de datos todos los productos vendidos paginados
     * @param pageable configuracion de paginado
     * @return respuesta http con los productos comprados paginados
     */
    @Operation(
            summary = "Traer todos los productos vendidos",
            description = "Trae todos los productos de base de datos y devuelve un Codigo de estado 200 y el listado de productos con paginacion"
    )
    @GetMapping
    public ResponseEntity<Page<BoughtProductDTORes>> getAll(Pageable pageable){
        return ResponseEntity.ok().body(boughtProductService.getAllBoughtProducts(pageable));
    }

    /**
     * Metodo que elimina de forma logica un producto comprado por id, devuelve un Codigo de estado 204
     * @param productId numero de id de producto comprado
     * @return respuesta http 204 sin contenido
     */
    @Operation(
            summary = "Elimina un producto vendido",
            description = "Elimina de forma logica un producto por id, devuelve un Codigo de estado 204"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long productId) {
        boughtProductService.deleteBoughtProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
