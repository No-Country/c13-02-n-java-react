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

@Tag(name = "Controlador de provedores")
@RestController
@RequestMapping("/api/v1/providers")
public class ProviderController {

    @Autowired
    private IProviderService providerService;

    @Operation(
    summary = "Guarda un proovedor",
    description = "Guarda un proovedor y devuelve un Codigo de estado 201 creado"
    )
    @PostMapping()
    public ResponseEntity<HttpStatus> saveProvider(@Valid @RequestBody ProviderDTOReq providerDTO) throws NameExistsException {
        providerService.saveProvider(providerDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
    summary = "Trae un proovedor",
    description = "Busca un proovedor por id y devuelve un Codigo de estado 200 y los datos del proovedor"
    )
    @GetMapping("/{providerId}")
    public ResponseEntity<ProviderDTORes> getProvider(@PathVariable Long providerId) throws IdNotFoundException {
        return ResponseEntity.ok(providerService.getProviderById(providerId));
    }

    @Operation(
summary = "Traer todos los proovedores",
     description = "Trae todos los proovedores de base de datos y devuelve un Codigo de estado 200 y el listado de proovedor"
    )
    @GetMapping()
    public ResponseEntity<Page<ProviderDTORes>> getAllProviders(Pageable pageable){
        return ResponseEntity.ok(providerService.getAllProviders(pageable));
    }

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

    @Operation(
     summary = "Elimina un proovedor",
      description = "Elimina de forma logica un proovedor por id, devuelve un Codigo de estado 204"
    )
    @DeleteMapping("/{providerId}")
    public ResponseEntity<HttpStatus> deleteProvider(@PathVariable Long providerId) {
        providerService.deleteProvider(providerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
