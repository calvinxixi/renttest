package com.rent.renttest.controller;

import com.rent.renttest.bean.ResBody;
import com.rent.renttest.bean.rent;
import com.rent.renttest.bean.tenant;
import com.rent.renttest.service.rentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class rentController {
    @Autowired
    rentService service;

    @GetMapping("api/getRent")
    public ResBody getRent(@RequestParam int page, @RequestParam int limit){
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<rent> list= service.getAllRent(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("api/addRent")
    public ResBody addRent(@RequestBody rent rent1) {
        ResBody resBody = new ResBody();
        int i = service.addRent(rent1);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @GetMapping("api/delRent")
    public ResBody delRent(@RequestParam int rID) {
        ResBody resBody = new ResBody();
        int i = service.delRent(rID);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("api/findRent")
    public ResBody findRent(@RequestParam int page,
                             @RequestParam int limit,
                             @RequestParam int name) {
        int count = 0;
        List<rent> list= new ArrayList<>();
        ResBody resBody = new ResBody();
        count = service.getCount(name);
//        System.out.println("返回数据=="+count);
        if (count == 0){
            //count = service.getCount();
            list= service.getAllRent(page, limit);
        } else {
            //count = service.getCount(name);
            list= service.findRent(page, limit, name);
        }
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("api/updateRent")
    public ResBody updateRent(@RequestBody rent rent) {
        ResBody resBody = new ResBody();
        int i = service.updateRent(rent);
        System.out.println("i = " + i);
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
