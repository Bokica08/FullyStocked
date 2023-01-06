package com.bazi.fullystocked.Web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/logout")
public class LogOutController {
    @GetMapping
    public String loguot(HttpServletRequest request)
    {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
