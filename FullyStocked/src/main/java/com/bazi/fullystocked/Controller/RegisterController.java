package com.bazi.fullystocked.Controller;

import com.bazi.fullystocked.Models.Exceptions.UsernameAlreadyExistsException;
import com.bazi.fullystocked.Services.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;

    public RegisterController( AuthService authService) {
        this.authService = authService;
    }
    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model)
    {
        return "register";
    }
    @PostMapping
    public String register(@RequestParam String ime,
                           @RequestParam String prezime,
                           @RequestParam String username,
                           @RequestParam String email
            ,@RequestParam String password
            ,@RequestParam String role)
    {
            if(role.equals("menadzer"))
            {
                authService.registerManager(ime,prezime,username,email,password);
            }
            else if(role.equals("magacioner"))
            {
                authService.registerWorker(ime,prezime,username,email,password);
            }
            return "redirect:/login";
    }
}