package com.rent.renttest.controller;

import com.rent.renttest.bean.ResBody;
import com.rent.renttest.bean.house;
import com.rent.renttest.bean.lease;
import com.rent.renttest.service.leaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class leaseController {
    @Autowired
    leaseService service;

    @GetMapping("api/getLease")
    public ResBody getLease(@RequestParam int page, @RequestParam int limit){
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<lease> list= service.getAllLease(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("api/addLease")
    public ResBody addLease(@RequestBody lease lease1) {
        ResBody resBody = new ResBody();
        int i = service.addLease(lease1);
        if (i == 1){ 
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @GetMapping("api/delLease")
    public ResBody delLease(@RequestParam int LID,@RequestParam int L_hid) {
        ResBody resBody = new ResBody();
        int i = service.delLease(LID,L_hid);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("api/findLease")
    public ResBody findLease(@RequestParam int page,
                           @RequestParam int limit,
                           @RequestParam int name) {
        int count = 0;
        List<lease> list= new ArrayList<>();
        ResBody resBody = new ResBody();
        count = service.getCount(name);
        if (count == 0){
            //count = service.getCount();
            list= service.getAllLease(page, limit);
        } else {
            //count = service.getCount(name);
            list= service.findLease(page, limit, name);
        }
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("ajax/getAllLeases")
    public ResBody getAllLeases() {
        ResBody resBody = new ResBody();
        List<lease> list = service.getAllLeases();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
}
