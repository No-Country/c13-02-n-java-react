package com.wallet.tienda.controllerBrand;

import com.wallet.tienda.ServicieBrand.BrandService;
import com.wallet.tienda.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brand")
    public String listallbrands(Model model) {
        model.addAttribute("brands", brandService.listallbrands());
        return "brand";
    }

    @GetMapping("/new/brand")
    public String createbrandform(Model model) {
        Brand brand = new Brand();
        model.addAttribute("brand", brand);
        return "new_brand";
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
