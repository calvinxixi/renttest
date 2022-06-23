package com.rent.renttest.controller;

import com.rent.renttest.bean.ResBody;
import com.rent.renttest.bean.house;
import com.rent.renttest.bean.repairhouse;
import com.rent.renttest.service.repairhouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class repairhouseController {
    @Autowired
    repairhouseService service;

    @GetMapping("api/getRepairhouse")
    public ResBody getRepairhouse(@RequestParam int page, @RequestParam int limit){
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<repairhouse> list= service.getAllRepairhouse(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("api/addRepairhouse")
    public ResBody addRepairhouse(@RequestBody repairhouse repairhouse1) {
        ResBody resBody = new ResBody();
        int i = service.addRepairhouse(repairhouse1);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @GetMapping("api/delRepairhouse")
    public ResBody delRepairhouse(@RequestParam int xID) {
        ResBody resBody = new ResBody();
        int i = service.delRepairhouse(xID);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("api/findRepairhouse")
    public ResBody findRepairhouse(@RequestParam int page,
                             @RequestParam int limit,
                             @RequestParam int name) {
        int count = 0;
        List<repairhouse> list= new ArrayList<>();
        ResBody resBody = new ResBody();
        count = service.getCount(name);
        if (count == 0){
            //count = service.getCount();
            list= service.getAllRepairhouse(page, limit);
        } else {
            //count = service.getCount(name);
            //list= service.find(page, limit, name);
        }
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("api/updateRepairhouse")
    public ResBody updateRepairhouse(@RequestBody repairhouse repairhouse1) {
        ResBody resBody = new ResBody();
        int i = service.updateRepairhouse(repairhouse1);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }
}
