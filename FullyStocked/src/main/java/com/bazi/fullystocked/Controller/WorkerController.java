package com.bazi.fullystocked.Controller;

import com.bazi.fullystocked.Models.Workers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value ="/homeWorker")
public class WorkerController {
    @GetMapping
    public String getWorkerPage()
    {

        return "homeWorker";
    }
}
