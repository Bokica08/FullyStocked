package com.bazi.fullystocked.Web.Controller;

import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.Managers;
import com.bazi.fullystocked.Models.Questions;
import com.bazi.fullystocked.Models.Workers;
import com.bazi.fullystocked.Services.AnswerService;
import com.bazi.fullystocked.Services.ManagersService;
import com.bazi.fullystocked.Services.QuestionsService;
import com.bazi.fullystocked.Services.StoredArticlesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/questions")
public class QuestionsController {
    private final QuestionsService questionsService;
    private final StoredArticlesService storedArticlesService;
    private final ManagersService managersService;
    private final AnswerService answerService;

    public QuestionsController(QuestionsService questionsService, StoredArticlesService storedArticlesService, ManagersService managersService, AnswerService answerService) {
        this.questionsService = questionsService;
        this.storedArticlesService = storedArticlesService;
        this.managersService = managersService;
        this.answerService = answerService;
    }

    @GetMapping("/worker/create")
    public String createQuestion(Model model)
    {
        model.addAttribute("managers", managersService.findAll());
        return "askAQuestion";
    }
    @PostMapping("/worker/create")
    public String createQuestion(@RequestParam String text,
                                 @RequestParam Integer managerId,
                                 HttpServletRequest request,
                                 Model model)
    {
        try
        {
            Workers u= (Workers) request.getSession().getAttribute("user");
            Questions q = questionsService.create(text, u.getUserid(), managerId).orElseThrow();
            model.addAttribute("questionId", q.getQuestionid());
            model.addAttribute("articles", storedArticlesService.findByLocation(u.getLocation().getLocationid()));
            return "selectQuestionArticle";
        }
        catch (Exception e)
        {
            return "redirect:/questions/worker/create?error="+e.getMessage();
        }
    }
    @GetMapping("/worker/backToList/{questionId}")
    public String backToList(@PathVariable int questionId, HttpServletRequest request, Model model, @RequestParam(required = false) String error)
    {
        Workers u=null;
        try
        {
            u=(Workers) request.getSession().getAttribute("user");
        }
        catch (Exception e)
        {
            return "redirect:/login";
        }
        if(questionsService.findById(questionId).isEmpty())
        {
            return "redirect:/questions/worker/create";
        }
        Questions q = questionsService.findById(questionId).get();
        if(!q.getWorker().getUserid().equals(u.getUserid()))
        {
            return "redirect:/login";

        }
        model.addAttribute("questionId", q.getQuestionid());
        model.addAttribute("articles", storedArticlesService.findByLocation(u.getLocation().getLocationid()));
        return "selectQuestionArticle";
    }
    @PostMapping("/worker/addArticle")
    public String addArticleToQuestion(@RequestParam Integer questionId,
                                       @RequestParam Integer articleId,
                                       HttpServletRequest request,
                                       Model model)
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
            questionsService.addArticle(questionId, articleId);
            model.addAttribute("questionId", questionId);
            model.addAttribute("articles", storedArticlesService.findByLocation(u.getLocation().getLocationid()));
            return "selectQuestionArticle";
        }
        catch (Exception e)
        {
            return "redirect:/questions/worker/backToList/"+questionId+"?"+e.getMessage();
        }
    }

    @GetMapping("/manager/list")
    public String listQuestionsPerManager(HttpServletRequest request, Model model)
    {
        Managers m = (Managers) request.getSession().getAttribute("user");
        model.addAttribute("questions", questionsService.findAllByManager(m.getUserid()));
        return "managerQuestions";
    }
    @GetMapping("/manager/details/{id}")
    public String questionDetailsManager(@PathVariable Integer id, Model model, HttpServletRequest request)
    {
        try {
            Managers m = (Managers) request.getSession().getAttribute("user");
            Questions q = questionsService.findById(id).orElseThrow(InvalidArgumentsException::new);
            if(!q.getManager().getUserid().equals(m.getUserid()))
            {
                throw new IllegalAccessException();
            }
            model.addAttribute("question", q);
            model.addAttribute("answers", answerService.findAllAnswersToQuestion(id));
            return "managerQuestionDetails";
        }
        catch (Exception e)
        {
            return "redirect:/questions/manager/list?error="+e.getMessage();
        }
    }
    @GetMapping("/worker/list")
    public String listQuestionsPerWorker(HttpServletRequest request, Model model)
    {
        Workers w= (Workers) request.getSession().getAttribute("user");
        model.addAttribute("questions", questionsService.findAllByWorker(w.getUserid()));
        return "workerQuestions";
    }
    @GetMapping("/worker/details/{id}")
    public String questionDetailsWorker(@PathVariable Integer id, Model model, HttpServletRequest request)
    {
        try {
            Workers w= (Workers) request.getSession().getAttribute("user");
            Questions q = questionsService.findById(id).orElseThrow(InvalidArgumentsException::new);
            if(!q.getWorker().getUserid().equals(w.getUserid()))
            {
                throw new IllegalAccessException();
            }
            model.addAttribute("question", q);
            model.addAttribute("answers", answerService.findAllAnswersToQuestion(id));
            return "workerQuestionDetails";
        }
        catch (Exception e)
        {
            return "redirect:/questions/manager/list?error="+e.getMessage();
        }
    }
    @PostMapping("/manager/answer")
    public String answerAQuestion(@RequestParam Integer questionId, HttpServletRequest request, Model model)
    {
        try {
            Managers m = (Managers) request.getSession().getAttribute("user");
            Questions q = questionsService.findById(questionId).orElseThrow(InvalidArgumentsException::new);
            if(!q.getManager().getUserid().equals(m.getUserid()))
            {
                throw new IllegalAccessException();
            }
            model.addAttribute("questionId", questionId);
            return "createAnswer";
        }
        catch (Exception e)
        {
            return "redirect:/questions/manager/details/"+questionId+"?error="+e.getMessage();
        }
    }
    @PostMapping("manager/answer/save")
    public String saveAnswer(@RequestParam Integer questionId, @RequestParam String text)
    {
        /*try
        {
            answerService.create(questionId, text);
            return "redirect:/questions/manager/details/"+questionId;
        }
        catch (Exception e)
        {
            return "redirect:/questions/manager/details/"+questionId+"?error="+e.getMessage();
        }*/
        answerService.create(questionId, text);
        return "redirect:/questions/manager/details/"+questionId;
    }

}
