package com.bazi.fullystocked.Web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value ="/manager")
public class ManagerController {
    @GetMapping
    public String getManagerPage()
    {

        return "homeManager";
    }
}