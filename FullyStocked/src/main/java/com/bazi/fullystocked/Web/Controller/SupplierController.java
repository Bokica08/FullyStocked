package com.bazi.fullystocked.Web.Controller;

import com.bazi.fullystocked.Models.Suppliers;
import com.bazi.fullystocked.Services.SuppliersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value ="/supplier")
public class SupplierController {
    private final SuppliersService suppliersService;

    public SupplierController(SuppliersService suppliersService) {
        this.suppliersService = suppliersService;
    }

    @GetMapping
    public String getSupplierPage()
    {

        return "homeSupplier";
    }
    @GetMapping("/categories")
    public String getSupplierCategories(HttpServletRequest request, Model m)
    {
        Suppliers s= (Suppliers) request.getSession().getAttribute("user");
        m.addAttribute("categoires",suppliersService.findCategoriesBySupplier(s.getUserid()));
        return "supplierCategories";
    }
}