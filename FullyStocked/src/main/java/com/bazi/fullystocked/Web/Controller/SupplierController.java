package com.bazi.fullystocked.Web.Controller;

import com.bazi.fullystocked.Models.Categories;
import com.bazi.fullystocked.Models.Suppliers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value ="/supplier")
public class SupplierController {
    @GetMapping
    public String getSupplierPage()
    {

        return "homeSupplier";
    }
    @GetMapping("/categories")
    public String getSupplierCategories(HttpServletRequest request, Model m)
    {
        Suppliers s= (Suppliers) request.getSession().getAttribute("user");
        List<Categories> categoriesList=s.getCategoryList();
        m.addAttribute("categoires",categoriesList);
        return "supplierCategories";
    }
}