package com.bazi.fullystocked.Web.Controller;

import com.bazi.fullystocked.Services.LocationsService;
import com.bazi.fullystocked.Services.WorkersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value ="/manager")
public class ManagerController {
    private final WorkersService workersService;
    private final LocationsService locationsService;

    public ManagerController(WorkersService workersService, LocationsService locationsService) {
        this.workersService = workersService;
        this.locationsService = locationsService;
    }

    @GetMapping
    public String getManagerPage()
    {

        return "homeManager";
    }

    @GetMapping("/noLocWorkers")
    public String listWorkersWithNullLocation(Model model)
    {
        model.addAttribute("workers", workersService.findAllWithNoLocation());
        model.addAttribute("locations", locationsService.findAll());
        return "noLocWorkers";
    }
    @PostMapping("/noLocWorkers/add")
    public String addWorkerToLocation(@RequestParam Integer workerId, @RequestParam Integer locationId)
    {
        try{
            workersService.assignLocation(workerId, locationId);
            return "redirect:/manager/noLocWorkers";
        }
        catch (Exception e)
        {
            return "redirect:/manager/noLocWorkers?error="+e.getMessage();
        }
    }
}
