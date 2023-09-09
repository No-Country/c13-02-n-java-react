package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.BrandDTOReq;
import com.wallet.tienda.dto.response.BrandDTORes;
import com.wallet.tienda.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @GetMapping()
    public ResponseEntity<Page<BrandDTORes>> listAllBrands(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.listAllBrands(pageable));
    }
    @PostMapping()
    public ResponseEntity<HttpStatus> save(@RequestBody BrandDTOReq brandDTOReq) {
        brandService.save(brandDTOReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BrandDTORes> getById(@PathVariable Long id) {
        return new ResponseEntity<>(brandService.searchById(id), HttpStatus.OK);
    }
    @PatchMapping()
    public ResponseEntity<HttpStatus> update(@RequestBody BrandDTOReq brandDTOReq) {
        brandService.update(brandDTOReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        brandService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
