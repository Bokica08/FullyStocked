package com.bazi.fullystocked.Web.Controller;

import com.bazi.fullystocked.Models.SqlViews.ArticlesReport;
import com.bazi.fullystocked.Models.Workers;
import com.bazi.fullystocked.Services.ArticlesService;
import com.bazi.fullystocked.Services.StoredArticlesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value ="/worker")
public class WorkerController {
   private final StoredArticlesService storedArticlesService;
   private final ArticlesService articlesService;

    public WorkerController(StoredArticlesService storedArticlesService, ArticlesService articlesService) {
        this.storedArticlesService = storedArticlesService;
        this.articlesService = articlesService;
    }

    @GetMapping
    public String getWorkerPage()
    {

        return "homeWorker";
    }
    @GetMapping("/articles")
    public String getArticles(HttpServletRequest request, Model model)
    {
        Workers u= (Workers) request.getSession().getAttribute("user");
        List<ArticlesReport> articlesReport=storedArticlesService.findByLocation(u.getLocation().getLocationid());
        model.addAttribute("articles",articlesReport);
        return "articles";
    }
    @GetMapping("/articles/details/{id}")
    public String getDetails(@PathVariable Integer id,Model model)
    {
        if(this.storedArticlesService.findById(id).isPresent())
        {
            ArticlesReport articlesReport=this.storedArticlesService.findById(id).get();
            model.addAttribute("article",articlesReport);
            model.addAttribute("categories", articlesService.findAllCategoriesByArticle(articlesReport.getArticleid()));
            return "detailsArticle";
        }
        return "redirect:/worker/articles?error=ArticleNotFound";
    }
}
