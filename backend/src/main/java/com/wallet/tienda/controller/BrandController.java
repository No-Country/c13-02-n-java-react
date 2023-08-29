package com.wallet.tienda.controller;

import com.wallet.tienda.service.BrandService;
import com.wallet.tienda.dto.response.BrandDTORes;
import com.wallet.tienda.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping()
    public ResponseEntity<List<BrandDTORes>> listallbrands() {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.listallbrands());
    }


    @PostMapping("/brand")
    public String savebrand(@ModelAttribute("brand") Brand brand, RedirectAttributes redirect) {
        brandService.savebrand(brand);

        redirect.addFlashAttribute("msgExito", "La marca ha" +
                " sido agregada con exito");

        return "redirect:/brand";
    }

    @GetMapping("/brand/edit/{id}")
    public String showeditform(@PathVariable Long id, Model model) {
        model.addAttribute("brand", brandService.searchbrandbyid(id));
        return "edit_brand";
    }

    @PostMapping("/brand/{id}")
    public String updatebrand(@PathVariable Long id, @ModelAttribute("brand") Brand brand, Model model) {
        Brand existingbrand=brandService.searchbrandbyid(id);
        brandService.updatebrand(existingbrand);
        return "redirect:/libros";
    }

    @PostMapping("/delete/brand/{id}")
    public String deletebrand(@PathVariable Long id, RedirectAttributes redirect) {
        Brand brand = brandService.searchbrandbyid(id);
        brandService.Deletebrand(brand);

        redirect.addFlashAttribute("msgExito", "La marca ha " +
                "sido eliminada correctamente");

        return "redirect:/brand";

    }

}
