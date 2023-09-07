package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.SaleDTOReq;
import com.wallet.tienda.dto.response.SaleDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.service.ISaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SaleController {

    private final ISaleService saleService;

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SaleDTOReq saleDTOReq) throws Exception {
        saleService.save(saleDTOReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<Page<SaleDTORes>> getAll(Pageable pageable) {
        return ResponseEntity.ok(saleService.getAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<SaleDTORes> getById(@PathVariable Long id) throws IdNotFoundException {
        return ResponseEntity.ok(saleService.getById(id));
    }

    @PatchMapping()
    public ResponseEntity<HttpStatus> update(@Valid @RequestBody SaleDTOReq saleDTOReq) throws IdNotFoundException {
        saleService.update(saleDTOReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        saleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
