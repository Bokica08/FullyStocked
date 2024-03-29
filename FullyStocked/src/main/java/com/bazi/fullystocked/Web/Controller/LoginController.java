package com.bazi.fullystocked.Web.Controller;

import com.bazi.fullystocked.Models.Exceptions.InvalidUserCredentialsException;
import com.bazi.fullystocked.Models.Exceptions.UserNotFoundException;
import com.bazi.fullystocked.Models.Managers;
import com.bazi.fullystocked.Models.Suppliers;
import com.bazi.fullystocked.Models.User;
import com.bazi.fullystocked.Models.Workers;
import com.bazi.fullystocked.Services.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {


    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(Model m, @RequestParam(required = false) String error) {
        m.addAttribute("bodycontent","login");
        m.addAttribute("error", error);
        return "/login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try {
            user = this.authService.login(request.getParameter("username"),
                    request.getParameter("password"));
            if (user instanceof Workers) {
                Workers w= (Workers) user;
                request.getSession().setAttribute("user", w);
                request.getSession().setAttribute("location",w.getLocation().getLocationname()+" "+w.getLocation().getCity());
                return "redirect:/worker";
            } else if (user instanceof Managers) {
                Managers m= (Managers) user;
                request.getSession().setAttribute("user", m);
                return "redirect:/manager";
            }
            else if(user instanceof Suppliers)
            {
                Suppliers s= (Suppliers) user;
                request.getSession().setAttribute("user", s);
                request.getSession().setAttribute("info",s.getSupplierinfo());
                request.getSession().setAttribute("number",s.getPhone());
                request.getSession().setAttribute("location",s.getStreet()+" бр."+ s.getStreetnumber()+", "+s.getCity());
                return "redirect:/supplier";
            }
            return "redirect:/home";
        }
        catch (Exception exception) {

            return "redirect:/login?error="+exception.getMessage();
        }
    }
}