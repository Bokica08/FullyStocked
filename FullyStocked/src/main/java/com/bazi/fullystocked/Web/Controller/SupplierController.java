package com.bazi.fullystocked.Web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value ="/supplier")
public class SupplierController {
    @GetMapping
    public String getSupplierPage()
    {

        return "homeSupplier";
    }
}