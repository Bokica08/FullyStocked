package com.bazi.fullystocked.Web.Controller;

import com.bazi.fullystocked.Models.Enumerations.ArticleStatus;
import com.bazi.fullystocked.Models.Questions;
import com.bazi.fullystocked.Models.SqlViews.ArticlesReport;
import com.bazi.fullystocked.Models.Workers;
import com.bazi.fullystocked.Services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value ="/worker")
public class WorkerController {
   private final StoredArticlesService storedArticlesService;
   private final ArticlesService articlesService;
   private final OrderedArticlesService orderedArticlesService;
   private final QuestionsService questionsService;
   private final ManagersService managersService;

    public WorkerController(StoredArticlesService storedArticlesService, ArticlesService articlesService, OrderedArticlesService orderedArticlesService, QuestionsService questionsService, ManagersService managersService) {
        this.storedArticlesService = storedArticlesService;
        this.articlesService = articlesService;
        this.orderedArticlesService = orderedArticlesService;
        this.questionsService = questionsService;
        this.managersService = managersService;
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
    @GetMapping("/deliveredArticles")
    public String listDeliveredArticles(Model model, HttpServletRequest request)
    {
        Workers u= (Workers) request.getSession().getAttribute("user");
        model.addAttribute("articles", orderedArticlesService.findByStatusAtLocation(ArticleStatus.DELIVERED, u.getLocation().getLocationid()));
        return "deliveredArticles";
    }

    @PostMapping("/deliveredArticles/process")
    public String processOrderedArticle(@RequestParam Integer articleId, HttpServletRequest request)
    {
        try
        {
            Workers u= (Workers) request.getSession().getAttribute("user");
            if(orderedArticlesService.findById(articleId).isEmpty())
            {
                return "redirect:/worker/deliveredArticles";
            }
            if(!orderedArticlesService.findById(articleId).get().getLocationid().equals(u.getLocation().getLocationid()))
            {
                return "redirect:/login";
            }
            storedArticlesService.updateFromOrder(articleId);
            return "redirect:/worker/deliveredArticles";
        }
        catch (Exception e)
        {
            return "redirect:/worker/deliveredArticles?error="+e.getMessage();
        }
    }

    @PostMapping("/articles/askAQuestion")
    public String askAQuestionForASingleArticle(@RequestParam Integer articleId, Model model)
    {
        model.addAttribute("articleId", articleId);
        model.addAttribute("managers", managersService.findAll());
        return "AskAQuestionSingle";
    }
    @PostMapping("/articles/askAQuestion/send")
    public String sendQuestionSingle(@RequestParam Integer articleId, @RequestParam Integer managerId, @RequestParam String text, HttpServletRequest request)
    {
        try
        {
            Workers u= (Workers) request.getSession().getAttribute("user");
            if(storedArticlesService.findById(articleId).isEmpty())
            {
                return "redirect:/worker/articles";
            }
            if(!storedArticlesService.findById(articleId).get().getLocationid().equals(u.getLocation().getLocationid()))
            {
                return "redirect:/login";
            }
            Questions q=questionsService.create(text, u.getUserid(), managerId).orElseThrow();
            questionsService.addArticle(q.getQuestionid(), articleId);
            return "redirect:/worker";
        }
        catch (Exception e)
        {
            return "redirect:/worker?error="+e.getMessage();
        }
    }

}
