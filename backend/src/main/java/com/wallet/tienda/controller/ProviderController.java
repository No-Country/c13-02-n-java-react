package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.ProviderDTOReq;
import com.wallet.tienda.dto.response.ProviderDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import com.wallet.tienda.service.IProviderService;
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
 * Clase controladora de proveedores
 * @Autor Damian Della corte
 */
@Tag(name = "Controlador de proveedores")
@RestController
@RequestMapping("/api/v1/providers")
public class ProviderController {

    @Autowired
    private IProviderService providerService;

    /**
     * Guarda un proovedor
     * @param providerDTO datos de proveedor
     * @return respuesta http con estado creado
     * @throws NameExistsException mensaje de excepcion de nombre de proveedor ya existe
     */
    @Operation(
    summary = "Guarda un proovedor",
    description = "Guarda un proovedor y devuelve un Codigo de estado 201 creado"
    )
    @PostMapping()
    public ResponseEntity<HttpStatus> saveProvider(@Valid @RequestBody ProviderDTOReq providerDTO) throws NameExistsException {
        providerService.saveProvider(providerDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Busca un proovedor por id
     * @param providerId numero de id de proveedor
     * @return respuesta http con datos de proveedor
     * @throws IdNotFoundException mensaje de excepcion de id de proveedor no encontrado
     */
    @Operation(
    summary = "Trae un proovedor",
    description = "Busca un proovedor por id y devuelve un Codigo de estado 200 y los datos del proovedor"
    )
    @GetMapping("/{providerId}")
    public ResponseEntity<ProviderDTORes> getProvider(@PathVariable Long providerId) throws IdNotFoundException {
        return ResponseEntity.ok(providerService.getProviderById(providerId));
    }

    /**
     * Trae todos los proovedores de base de datos
     * @param pageable configuracion de paginado
     * @return respuesta http con lista de proveedores paginados
     */
    @Operation(
summary = "Traer todos los proovedores",
     description = "Trae todos los proovedores de base de datos y devuelve un Codigo de estado 200 y el listado de proovedor"
    )
    @GetMapping()
    public ResponseEntity<Page<ProviderDTORes>> getAllProviders(Pageable pageable){
        return ResponseEntity.ok(providerService.getAllProviders(pageable));
    }

    /**
     * Busca un proovedor por id y lo actualiza
     * @param providerDTO datos de proveedor
     * @return respuesta http con estado sin contenido
     * @throws IdNotFoundException mensaje de excepcion de id de proveedor no encontrado
     * @throws NameExistsException mensaje de excepcion de nombre de proveedor ya existe
     */
    @Operation(
      summary = "Actualiza un proovedor",
     description = "Busca un proovedor por id y lo actualiza, devuelve un Codigo de estado 204"
    )
    @PutMapping()
    public ResponseEntity<HttpStatus> updateProvider(@Valid @RequestBody ProviderDTOReq providerDTO) throws IdNotFoundException,
            NameExistsException {
        providerService.updateProvider(providerDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Elimina un proovedor por id
     * @param providerId numero de id de proceedor
     * @return respuesta http con estdo sin contenido
     */
    @Operation(
     summary = "Elimina un proovedor",
      description = "Elimina un proovedor por id, devuelve un Codigo de estado 204"
    )
    @DeleteMapping("/{providerId}")
    public ResponseEntity<HttpStatus> deleteProvider(@PathVariable Long providerId) {
        providerService.deleteProvider(providerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
