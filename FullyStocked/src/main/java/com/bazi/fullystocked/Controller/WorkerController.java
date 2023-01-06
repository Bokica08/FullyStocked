package com.bazi.fullystocked.Controller;

import com.bazi.fullystocked.Models.SqlViews.ArticlesReport;
import com.bazi.fullystocked.Models.StoredArticles;
import com.bazi.fullystocked.Models.User;
import com.bazi.fullystocked.Models.Workers;
import com.bazi.fullystocked.Services.StoredArticlesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value ="/worker")
public class WorkerController {
   private final StoredArticlesService storedArticlesService;

    public WorkerController(StoredArticlesService storedArticlesService) {
        this.storedArticlesService = storedArticlesService;
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
            return "detailsArticle";
        }
        return "redirect:/articles?error=ArticleNotFound";
    }
}
