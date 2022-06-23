package com.rent.renttest.controller;

import com.rent.renttest.bean.ResBody;
import com.rent.renttest.bean.guanli;
import com.rent.renttest.bean.tenant;
import com.rent.renttest.service.guanliService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class gaunliController {
    @Autowired
    guanliService service;

    private static final Logger LOG = LoggerFactory.getLogger(gaunliController.class);

    @RequestMapping("/guanli/loginByPassword")
    public ResBody loginByPassword(@RequestBody Map<String, Object> params, HttpSession session){
        ResBody resBody = new ResBody();
        String Name = params.get("username").toString();
        String Password = params.get("password").toString();
//        //选择登录角色
//        0.房东账号
//        1.租客
//        2.管理员

            guanli guanlis = service.findGuanli(Name,Password);
            if (guanlis == null){
                resBody.setCode(500);
                resBody.setMsg("登录失败，请重新登录");
            }else {
                session.setAttribute("guanli", guanlis);
                LOG.info(guanlis.toString());
                resBody.setCode(200);
                resBody.setMsg("登陆成功");
            }

        return resBody;
    }

    @GetMapping("api/getGuanlis")
    public ResBody getGuanlis(@RequestParam int page, @RequestParam int limit){
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<guanli> list= service.getAllGuanli(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("api/addGuanli")
    public ResBody addGuanli(@RequestBody guanli guanli1) {
        ResBody resBody = new ResBody();
        int i = service.addGuanli(guanli1);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("api/updateGuanli")
    public ResBody updateGuanli(@RequestBody guanli guanli1) {
        ResBody resBody = new ResBody();
        int i = service.updateGuanli(guanli1);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("api/delGuanli")
    public ResBody delGuanli(@RequestParam int gID) {
        ResBody resBody = new ResBody();
        int i = service.delGuanli(gID);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("api/findGuanli")
    public ResBody findGuanli(@RequestParam int page,
                           @RequestParam int limit,
                           @RequestParam String name) {
        int count = 0;
        List<guanli> list= new ArrayList<>();
        ResBody resBody = new ResBody();
        if (name.isEmpty()){
            count = service.getCount();
            list= service.getAllGuanli(page, limit);
        } else {
            count = service.getCount(name);
            list= service.findGuanli(page, limit, name);
        }
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/updateGuanlisPass")
    public ResBody updateGuanlisPass(@RequestBody Map<String, Object> params,
                                     HttpSession session) {
        ResBody resBody = new ResBody();
        String newPsw = params.get("newPsw").toString();
        guanli guanli1 = (guanli) session.getAttribute("guanli");
        guanli1.setGPassword(newPsw);
        int i = service.updateGuanlisPass(guanli1.getGID(),newPsw);
        if (i != 1){
            resBody.setCode(500);
            resBody.setMsg("修改失败，后台出错");
        }else {
            session.setAttribute("guanli",guanli1);
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }
        return resBody;
    }
}
