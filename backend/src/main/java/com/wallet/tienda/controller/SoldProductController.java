package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.SoldProductDTOReq;
import com.wallet.tienda.dto.response.SoldProductDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.service.ISoldProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sold-products")
@RequiredArgsConstructor
public class SoldProductController {
    private final ISoldProductService soldProductService;

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SoldProductDTOReq soldProductDTOReq) throws Exception {
        soldProductService.save(soldProductDTOReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<Page<SoldProductDTORes>> getAll(Pageable pageable) {
        return ResponseEntity.ok(soldProductService.getAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<SoldProductDTORes> getById(@PathVariable Long id) throws IdNotFoundException {
        return ResponseEntity.ok(soldProductService.getById(id));
    }

    @PatchMapping()
    public ResponseEntity<HttpStatus> update(@Valid @RequestBody SoldProductDTOReq soldProductDTOReq) throws IdNotFoundException {
        soldProductService.update(soldProductDTOReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws IdNotFoundException {
        soldProductService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}