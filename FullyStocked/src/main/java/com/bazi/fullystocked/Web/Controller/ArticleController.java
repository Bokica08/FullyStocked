package com.bazi.fullystocked.Web.Controller;

import com.bazi.fullystocked.Models.Articles;
import com.bazi.fullystocked.Models.Categories;
import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Services.ArticlesService;
import com.bazi.fullystocked.Services.CategoriesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value ="/articles")
public class ArticleController {
    private final ArticlesService articlesService;
    private final CategoriesService categoriesService;

    public ArticleController(ArticlesService articlesService, CategoriesService categoriesService) {
        this.articlesService = articlesService;
        this.categoriesService = categoriesService;
    }

    @GetMapping("/create")
    private String createArticle()
    {
        return "createArticle";
    }
    @PostMapping("/create")
    public String createArticle(@RequestParam String aname,
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
        return "redirect:/manager";
    }
    @GetMapping("/category/add")
    private String categoryArticle(Model m)
    {
        List<Articles> articlesList=articlesService.findAll();
        List<Categories> categories=categoriesService.findAll();
        m.addAttribute("articles",articlesList);
        m.addAttribute("categories",categories);


        return "addArticleCategory";
    }
    @PostMapping("/category/add")
    private String categoryArticle(@RequestParam Integer article,
                                   @RequestParam Integer category)
    {
    try{
        articlesService.addToCategory(article,category);
    }
    catch (InvalidArgumentsException e)
    {
        return "redirect:/articles/category/add?error="+e.getMessage();
    }


        return "redirect:/manager";
    }
}
