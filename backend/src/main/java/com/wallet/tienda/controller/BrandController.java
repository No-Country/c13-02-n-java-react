package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.BrandDTOReq;
import com.wallet.tienda.dto.response.BrandDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.service.IBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase controladora de marcas
 * @Autor Maria Paez, David Ramon Thomen
 */
@Tag(name = "Controlador de marca")
@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

    @Autowired
    private IBrandService brandService;


    /**
     * Metodo para traer de base de datos todos las marcas paginadas
     * @param pageable configuracion de paginado
     * @return respuesta http con las marcas paginados
     */
    @Operation(
            summary = "Traer todas los marcas",
            description = "Trae todos los marcas de base de datos y devuelve un Codigo de estado 200 y el listado de marcas con paginacion"
    )
    @GetMapping()
    public ResponseEntity<Page<BrandDTORes>> listAllBrands(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.listAllBrands(pageable));
    }

    /**
     * Metodo para guardar una marca, que devuelve un codigo de estado 200 ok
     * @param brandDTOReq dto de marca
     * @return Respuesta de estado http creado
     */
    @Operation(
            summary = "Guarda una marca",
            description = "Guarda la marca y devuelve un Codigo de estado 201 creado"
    )
    @PostMapping()
    public ResponseEntity<HttpStatus> save(@RequestBody BrandDTOReq brandDTOReq) {
        brandService.save(brandDTOReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Metodo que busca por id y responde con un dto de marca, puede arrojar una excepcion de id no encontrado
     * @param id numero de id de la marca
     * @return respuesta http con el dto
     * @throws IdNotFoundException mensaje de excepcion de id no encontrado
     */
    @Operation(
            summary = "Trae una marca",
            description = "Busca una marca por id y devuelve un Codigo de estado 200 y los datos de la marca"
    )
    @GetMapping("/{id}")
    public ResponseEntity<BrandDTORes> getById(@PathVariable Long id) {
        return new ResponseEntity<>(brandService.searchById(id), HttpStatus.OK);
    }

    /**
     * Metodo que actualiza una marca y devuelve un codigo de estado 200
     * @param brandDTOReq dto de marca
     * @return respuesta http ok
     */
    @Operation(
            summary = "Actualiza una marca",
            description = "Actualiza una marca y devuelve un Codigo de estado 200"
    )
    @PatchMapping()
    public ResponseEntity<HttpStatus> update(@RequestBody BrandDTOReq brandDTOReq) {
        brandService.update(brandDTOReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /**
     * Metodo que elimina una marca por id, devuelve un Codigo de estado 204
     * @param id numero de id de marca
     * @return respuesta http 204 sin contenido
     */
    @Operation(
            summary = "Elimina una marca",
            description = "Elimina una marca por id, devuelve un Codigo de estado 204"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        brandService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
