package com.bazi.fullystocked.Web.Controller;

import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.Exceptions.UsernameAlreadyExistsException;
import com.bazi.fullystocked.Models.User;
import com.bazi.fullystocked.Services.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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
            , @RequestParam String password
            , @RequestParam String role, HttpServletRequest request) {
        try {
            if (role.equals("menadzer")) {
                authService.registerManager(ime, prezime, username, email, password);
            } else if (role.equals("magacioner")) {
                authService.registerWorker(ime, prezime, username, email, password);
            } else if (role.equals("dobavuvac")) {
                User u = new User(ime, prezime, username, email, password);
                request.getSession().setAttribute("user", u);
                return "redirect:/register/registerSupplier";
            }
            return "redirect:/login";
        }
        catch (UsernameAlreadyExistsException | InvalidArgumentsException exception)
        {
            return "redirect:/register?error="+exception.getMessage();
        }
    }
    @GetMapping("/registerSupplier")
    public String getRegisterSupplier(@RequestParam(required = false) String error, Model model)
    {
        return "registerSupplier";
    }
    @PostMapping("/registerSupplier")
    public String registerSupplier(@RequestParam String sinfo,
                                   @RequestParam String phone,
                                   @RequestParam String street,
                                   @RequestParam String broj,
                                   @RequestParam String grad,HttpServletRequest request)
    {
        try {
            User u = (User) request.getSession().getAttribute("user");
            authService.registerSupplier(u.getFirstname(), u.getLastname(), u.getUsername(), u.getEmail(), u.getUserpassword(), sinfo, phone, street, Integer.parseInt(broj), grad);
            request.getSession().invalidate();
            return "redirect:/login";
        }
        catch (UsernameAlreadyExistsException | InvalidArgumentsException exception)
        {
            return "redirect:/register?error="+exception.getMessage();
        }
    }
}
