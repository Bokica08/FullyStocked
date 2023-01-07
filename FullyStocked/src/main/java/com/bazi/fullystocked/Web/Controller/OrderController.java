package com.bazi.fullystocked.Web.Controller;

import com.bazi.fullystocked.Models.*;
import com.bazi.fullystocked.Models.Enumerations.OrderPriority;
import com.bazi.fullystocked.Models.Enumerations.OrderStatus;
import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.SqlViews.ArticlesAtLocationReport;
import com.bazi.fullystocked.Services.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final SuppliersService suppliersService;
    private final OrdersService ordersService;
    private final ArticlesService articlesService;
    private final LocationsService locationsService;
    private final OrderedArticlesService orderedArticlesService;

    public OrderController(SuppliersService suppliersService, OrdersService ordersService, ArticlesService articlesService, LocationsService locationsService, OrderedArticlesService orderedArticlesService) {
        this.suppliersService = suppliersService;
        this.ordersService = ordersService;
        this.articlesService = articlesService;
        this.locationsService = locationsService;
        this.orderedArticlesService = orderedArticlesService;
    }

    @GetMapping("/create")
    public String createOrder(Model model)
    {
        model.addAttribute("suppliers", suppliersService.findAllSuppliersReport());
        return "createOrder";
    }
    @PostMapping("/create")
    public String createOrder(@RequestParam OrderPriority priority,
                              @RequestParam String remark,
                              @RequestParam Integer supplierId,
                              HttpServletRequest request,
                              Model model)
    {
        Orders order = null;
        Managers manager = null;
        try {
            manager = (Managers) request.getSession().getAttribute("user");
            order=ordersService.create(priority, manager.getUserid(), supplierId).orElseThrow(InvalidArgumentsException::new);
            if(remark!=null && !remark.isEmpty())
            {
                ordersService.addManagerRemark(order.getOrderid(), remark);
            }

        }
        catch (Exception e) {
            return "redirect:/orders/createOrder?error="+e.getMessage();
        }
        model.addAttribute("orderId", order.getOrderid());
        model.addAttribute("articles", articlesService.findAllBySupplier(supplierId));
        return "selectOrderArticle";
    }

    @PostMapping("/articles/select")
    public String getDetails(@RequestParam int orderId, @RequestParam int articleId, Model model)
    {
        if(ordersService.findById(orderId).isEmpty())
        {
            return "redirect:/orders/create";
        }
        try{
            model.addAttribute("orderId", orderId);
            List<ArticlesAtLocationReport> articlesReport=articlesService.findAvailabilityAtAllLocations(articleId);
            model.addAttribute("articlesReport",articlesReport);
            model.addAttribute("locations", locationsService.findAll());
            return "addOrderArticle";
        }
        catch(Exception e)
        {
            return "redirect:/orders/backToList/"+orderId+"?"+e.getMessage();
        }
    }

    @PostMapping("/articles/add")
    public String getDetails(@RequestParam int orderId, @RequestParam int articleId, @RequestParam int quantity, @RequestParam int locationId, HttpServletRequest request, Model model)
    {
        if(ordersService.findById(orderId).isEmpty())
        {
            return "redirect:/orders/create";
        }
        try
        {
            Managers u= (Managers) request.getSession().getAttribute("user");
            Orders order=ordersService.findById(orderId).orElseThrow(InvalidArgumentsException::new);
            ordersService.addArticleToOrder(quantity, locationId, articleId, orderId);
            model.addAttribute("orderId", orderId);
            model.addAttribute("articles", articlesService.findAllBySupplier(order.getSupplier().getUserid()));
            return "selectOrderArticle";
        }
        catch(Exception e)
        {
            return "redirect:/orders/backToList/"+orderId+"?"+e.getMessage();
        }
    }

    @GetMapping("/backToList/{orderId}")
    public String backToList(@PathVariable int orderId, HttpServletRequest request, Model model, @RequestParam(required = false) String error)
    {
        Managers u=null;
        try
        {
            u=(Managers) request.getSession().getAttribute("user");
        }
        catch (Exception e)
        {
            return "redirect:/login";
        }
        if(ordersService.findById(orderId).isEmpty())
        {
            return "redirect:/orders/create";
        }
        Orders order=ordersService.findById(orderId).get();
        if(!order.getManager().getUserid().equals(u.getUserid()))
        {
            return "redirect:/login";

        }
        model.addAttribute("orderId", orderId);
        model.addAttribute("articles", articlesService.findAllBySupplier(order.getSupplier().getUserid()));
        return "selectOrderArticle";
    }

    @PostMapping("/send")
    public String sendOrder(@RequestParam Integer orderId)
    {
        if(ordersService.findById(orderId).isEmpty())
        {
            return "redirect:/orders/create";
        }
        ordersService.updateStatus(orderId, OrderStatus.SENT);
        return "redirect:/manager";
    }

    @GetMapping("/manager/list")
    public String listOrdersMan(Model model, HttpServletRequest request)
    {
        Managers m = (Managers) request.getSession().getAttribute("user");
        model.addAttribute("orders", ordersService.findAllByManagerReport(m.getUserid()));
        return "managerOrders";
    }
    @GetMapping("/supplier/list")
    public String listOrdersSup(Model model, HttpServletRequest request)
    {
        Suppliers s = (Suppliers) request.getSession().getAttribute("user");
        model.addAttribute("orders", ordersService.findAllBySupplierReport(s.getUserid()));
        return "supplierOrders";
    }
    @GetMapping("/supplier/details/{id}")
    public String orderDetailsSupplier(@PathVariable Integer id, Model model)
    {
        try
        {
            model.addAttribute("order", ordersService.findById(id).orElseThrow(InvalidArgumentsException::new));
            model.addAttribute("articles", orderedArticlesService.findAllByOrder(id));
        }
        catch (Exception e)
        {
            return "redirect:/orders/supplier/list?error"+e.getMessage();
        }
        return "suppOrderDetails";
    }
    @GetMapping("/manager/details/{id}")
    public String orderDetailsManager(@PathVariable Integer id, Model model)
    {
        try
        {
            model.addAttribute("order", ordersService.findById(id).orElseThrow(InvalidArgumentsException::new));
            model.addAttribute("articles", orderedArticlesService.findAllByOrder(id));
        }
        catch (Exception e)
        {
            return "redirect:/orders/manager/list?error"+e.getMessage();
        }
        return "managerOrderDetails";
    }

    @PostMapping("/supplier/offer")
    public String createOffer(@RequestParam Integer articleId, @RequestParam Integer orderId, HttpServletRequest request, Model model)
    {
        Suppliers u=null;
        try
        {
            u=(Suppliers) request.getSession().getAttribute("user");
        }
        catch (Exception e)
        {
            return "redirect:/login";
        }
        if(ordersService.findById(orderId).isEmpty())
        {
            return "redirect:/orders/supplier/list";
        }
        Orders order=ordersService.findById(orderId).get();
        if(!order.getSupplier().getUserid().equals(u.getUserid()))
        {
            return "redirect:/login";
        }
        model.addAttribute("orderId", orderId);
        try {
            model.addAttribute("article", orderedArticlesService.findById(articleId).orElseThrow(InvalidArgumentsException::new));
        }
        catch (Exception e)
        {
            return "redirect:/orders/supplier/list?error"+e.getMessage();
        }
        return "createOffer";
    }

    @PostMapping("/supplier/offer/save")
    public String saveOffer(@RequestParam int price, @RequestParam int quantity, @RequestParam int orderId, @RequestParam int articleId)
    {
        try {
            orderedArticlesService.update(articleId, price, quantity);
        }
        catch (Exception e)
        {
            return "redirect:/orders/supplier/details/"+orderId+"?error="+e.getMessage();
        }
        return "redirect:/orders/supplier/details/"+orderId;
    }

    @PostMapping("/supplier/approve")
    public String approveOrder(@RequestParam Integer orderId, HttpServletRequest request)
    {
        Suppliers u=null;
        try
        {
            u=(Suppliers) request.getSession().getAttribute("user");
        }
        catch (Exception e)
        {
            return "redirect:/login";
        }
        if(ordersService.findById(orderId).isEmpty())
        {
            return "redirect:/orders/supplier/list";
        }
        Orders order=ordersService.findById(orderId).get();
        if(!order.getSupplier().getUserid().equals(u.getUserid()))
        {
            return "redirect:/login";
        }
        try {
            ordersService.updateStatus(orderId, OrderStatus.APPROVED);
        }
        catch (Exception e)
        {
            return "redirect:/orders/supplier/details/"+orderId+"?error="+e.getMessage();
        }
        return "redirect:/orders/supplier/details/"+orderId;
    }
    @PostMapping("/manager/initiate")
    public String initiateOrder(@RequestParam Integer orderId, HttpServletRequest request)
    {
        Managers u=null;
        try
        {
            u=(Managers) request.getSession().getAttribute("user");
        }
        catch (Exception e)
        {
            return "redirect:/login";
        }
        if(ordersService.findById(orderId).isEmpty())
        {
            return "redirect:/orders/manager/list";
        }
        Orders order=ordersService.findById(orderId).get();
        if(!order.getManager().getUserid().equals(u.getUserid()))
        {
            return "redirect:/login";
        }
        try{
        ordersService.updateStatus(orderId, OrderStatus.IN_PROGRESS);}
        catch (Exception e)
        {
            return "redirect:/orders/manager/details/"+orderId+"?error="+e.getMessage();
        }
        return "redirect:/orders/manager/details/"+orderId;
    }
    @PostMapping("/worker/accept")
    public String acceptOrder(@RequestParam Integer orderId)
    {
        ordersService.updateStatus(orderId, OrderStatus.DELIVERED);
        return "redirect:/worker";
    }
}
