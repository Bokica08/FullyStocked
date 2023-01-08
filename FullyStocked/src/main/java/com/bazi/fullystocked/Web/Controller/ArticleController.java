package com.bazi.fullystocked.Web.Controller;

import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Services.ArticlesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value ="/articles")
public class ArticleController {
    private final ArticlesService articlesService;

    public ArticleController(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }

    @GetMapping("/create")
    private String createArticle()
    {
        return "createArticle";
    }
    @PostMapping("/create")
    public String createInvoice(@RequestParam String aname,
                                @RequestParam String opis,
                                @RequestParam(required = false) String slika,
                                @RequestParam int num, HttpServletRequest request, Model model) {
        try {
            if (slika != null || !slika.isEmpty()) {
                articlesService.create(opis, aname, slika, num);
            } else {
                articlesService.create(opis, aname, num);
            }

        }
        catch (InvalidArgumentsException e)
        {
            return "redirect:/articles/create?error="+e.getMessage();

        }
        return "redirect:/homeManager";
    }
}
