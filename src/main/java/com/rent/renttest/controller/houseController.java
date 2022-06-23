package com.rent.renttest.controller;

import com.rent.renttest.bean.ResBody;
import com.rent.renttest.bean.house;
import com.rent.renttest.service.houseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class houseController {
    @Autowired
    houseService service;

    @GetMapping("api/getAllhosues")
    public ResBody getAllhouses(@RequestParam int page, @RequestParam int limit){
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<house> list= service.getAllhouses(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("api/addHouse")
    public ResBody addHouse(@RequestBody house house1) {
        ResBody resBody = new ResBody();
        int i = service.addHouse(house1);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("api/updateHouse")
    public ResBody updateHouse(@RequestBody house house1) {
        ResBody resBody = new ResBody();
        int i = service.updateHouse(house1);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("api/delHouse")
    public ResBody delHouse(@RequestParam int hID) {
        ResBody resBody = new ResBody();
        int i = service.delHouse(hID);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("api/findHouse")
    public ResBody findHouses(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        int count = 0;
        List<house> list= new ArrayList<>();
        ResBody resBody = new ResBody();
        if (name.isEmpty()){
            count = service.getCount();
            list= service.getAllhouses(page, limit);
        }else {
            count = service.getCount(name);
            list= service.findHouse(page, limit,name);
        }
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("ajax/getAllFreehouses")
    public ResBody getAllFreehouses() {
        ResBody resBody = new ResBody();
        List<house> list = service.getAllFreeHouses();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("ajax/getAllhouses")
    public ResBody getAllhouses() {
        ResBody resBody = new ResBody();
        List<house> list = service.getAllhouses();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

}
