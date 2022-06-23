package com.rent.renttest.controller;

import com.rent.renttest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {
    @Autowired
    tenantService tenantService;
    @Autowired
    repairhouseService repairhouseService;
    @Autowired
    houseService houseService;
    @Autowired
    leaseService leaseService;
    @Autowired
    rentService rentService;
    @Autowired
    ownService ownService;
    @Autowired
    guanliService guanliService;

    @GetMapping("/login")
    public String login(){ return "page/template/login";}

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/console")
    public String console(Model model) {
        int tenantCount = tenantService.getCount();
        int repairhouseCount = repairhouseService.getCount();
        int repairhouseFreeCount = repairhouseService.getFreeCount();
        int houseCount = houseService.getCount();
        int houseFreeCount = houseService.getFreeCount();
        int leaseCount = leaseService.getCount();
        int rentCount = rentService.getCount();
        int rentFreeCount = rentService.getFreeCount();
        model.addAttribute("leaseCount",leaseCount);
        model.addAttribute("repairhouseCount",repairhouseCount);
        model.addAttribute("repairhouseFreeCount",repairhouseFreeCount);
        model.addAttribute("houseCount",houseCount);
        model.addAttribute("houseFreeCount",houseFreeCount);

        model.addAttribute("rentCount",rentCount);
        model.addAttribute("rentFreeCount",rentFreeCount);
        model.addAttribute("tenantCount",tenantCount);
        return "page/console/console";
    }

    @GetMapping("/house")
    public String house(){
        return "page/template/house";
    }

    @GetMapping("/own")
    public String own(){
        return "page/template/own";
    }

    @GetMapping("/tenant")
    public String tenant(){
        return "page/template/tenant";
    }

    @GetMapping("/guanli")
    public String guanli(){
        return "page/template/guanli";
    }

    @GetMapping("/lease")
    public String lease(){
        return "page/template/lease";
    }

    @GetMapping("/repairhouseAdd")
    public String repairhouseAdd(){
        return "page/template/repairhouseAdd";
    }

    @GetMapping("/repairhouse")
    public String repairhouse(){
        return "page/template/repairhouse";
    }

    @GetMapping("/rentAdd")
    public String rentAdd(){
        return "page/template/rentAdd";
    }

    @GetMapping("/rent")
    public String rent(){
        return "page/template/rent";
    }

    @GetMapping("/guanli-info")
    public String guanliinfo(){
        return "page/template/guanli-info";
    }

    @GetMapping("/own-info")
    public String owninfo(){
        return "page/template/own-info";
    }

    @GetMapping("/tenant-info")
    public String tenantinfo(){
        return "page/template/tenant-info";
    }

    @GetMapping("/rili")
    public String rili(){
        return "page/template/rili";
    }

    @GetMapping("/tpl-user-password")
    public String reader_password(){
        return "page/tpl/tpl-user-password";
    }
}
