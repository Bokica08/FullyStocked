package com.bazi.fullystocked.Web.Controller;

import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.Invoices;
import com.bazi.fullystocked.Models.SqlViews.ArticlesReport;
import com.bazi.fullystocked.Models.Workers;
import com.bazi.fullystocked.Services.InvoicesService;
import com.bazi.fullystocked.Services.StoredArticlesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value ="/invoices")
public class InvoiceController {
    private final InvoicesService invoicesService;
    private final StoredArticlesService storedArticlesService;

    public InvoiceController(InvoicesService invoicesService, StoredArticlesService storedArticlesService) {
        this.invoicesService = invoicesService;
        this.storedArticlesService = storedArticlesService;
    }

    @GetMapping("/create")
    public String createInvoice()
    {
        return "createInv";
    }
    @PostMapping("/create")
    public String createInvoice(@RequestParam(required = false) String cname,
                                @RequestParam(required = false) String phone,
                                @RequestParam(required = false) String city,
                                @RequestParam(required = false) String street,
                                @RequestParam(required = false) int num, HttpServletRequest request, Model model)
    {
        Invoices inv=null;
        Workers u=null;
        try
        {
            u= (Workers) request.getSession().getAttribute("user");
            if(cname==null || cname.isEmpty())
            {
                inv=invoicesService.create(u.getUserid()).orElseThrow();
            }
            else
            {
                inv = invoicesService.create(cname, phone, street, num, city, u.getUserid()).orElseThrow();
            }
        }
        catch (Exception e)
        {
            return "redirect:/invoices/create?error="+e.getMessage();
        }
        model.addAttribute("invId", inv.getInvoiceid());
        model.addAttribute("articles", storedArticlesService.findByLocation(u.getLocation().getLocationid()));
        return "selectInvArticle";
    }

    @PostMapping("/articles/select")
    public String getDetails(@RequestParam int invoiceId, @RequestParam int articleId, Model model)
    {
        if(invoicesService.findById(invoiceId).isEmpty())
        {
            return "redirect:/create";
        }
        try{
            model.addAttribute("invId", invoiceId);
            ArticlesReport articlesReport=this.storedArticlesService.findById(articleId).orElseThrow(InvalidArgumentsException::new);
            model.addAttribute("article",articlesReport);
            return "addInvoiceArticle";
        }
        catch(Exception e)
        {
            return "redirect:/invoices/backToList/"+invoiceId+"?"+e.getMessage();
        }
    }
    @PostMapping("/articles/add")
    public String getDetails(@RequestParam int invoiceId, @RequestParam int articleId, @RequestParam int quantity, @RequestParam int price, HttpServletRequest request, Model model)
    {
        if(invoicesService.findById(invoiceId).isEmpty())
        {
            return "redirect:/invoices/create";
        }
        try
        {
            Workers u= (Workers) request.getSession().getAttribute("user");
            invoicesService.addArticleToInvoice(invoiceId, articleId, price, quantity);
            model.addAttribute("invId", invoiceId);
            model.addAttribute("articles", storedArticlesService.findByLocation(u.getLocation().getLocationid()));
            return "selectInvArticle";
        }
        catch(Exception e)
        {
            return "redirect:/invoices/backToList/"+invoiceId+"?"+e.getMessage();
        }
    }

    @GetMapping("/backToList/{invoiceId}")
    public String backToList(@PathVariable int invoiceId, HttpServletRequest request, Model model, @RequestParam(required = false) String error)
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
        if(invoicesService.findById(invoiceId).isEmpty())
        {
            return "redirect:/invoices/create";
        }
        Invoices inv=invoicesService.findById(invoiceId).get();
        if(!inv.getWorker().getUserid().equals(u.getUserid()))
        {
            return "redirect:/login";

        }
        model.addAttribute("invId", invoiceId);
        model.addAttribute("articles", storedArticlesService.findByLocation(u.getLocation().getLocationid()));
        return "selectInvArticle";
    }
}
