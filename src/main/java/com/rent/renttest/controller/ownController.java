package com.rent.renttest.controller;

import com.rent.renttest.bean.ResBody;
import com.rent.renttest.bean.own;
import com.rent.renttest.bean.tenant;
import com.rent.renttest.service.ownService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ownController {
    @Autowired
    ownService service;
    private static final Logger LOG = LoggerFactory.getLogger(ownController.class);

    @RequestMapping("/own/loginByPassword")
    public ResBody loginByPassword(@RequestBody Map<String, Object> params, HttpSession session){
        ResBody resBody = new ResBody();
        String Name = params.get("username").toString();
        String Password = params.get("password").toString();
        own owns = service.findown(Name,Password);
        if (owns == null){
            resBody.setCode(500);
            resBody.setMsg("登录失败，请重新登录");
        }else {
            session.setAttribute("own", owns);
            LOG.info(owns.toString());
            resBody.setCode(200);
            resBody.setMsg("登陆成功");
        }

        return resBody;
    }

    @GetMapping("api/getOwns")
    public ResBody getOwns(@RequestParam int page, @RequestParam int limit){
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<own> list= service.getAllOwns(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("api/addOwn")
    public ResBody addOwn(@RequestBody own own1) {
        ResBody resBody = new ResBody();
        int i = service.addOwns(own1);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("api/updateOwn")
    public ResBody updateOwn(@RequestBody own own1) {
        ResBody resBody = new ResBody();
        int i = service.updateOwn(own1);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("api/delOwn")
    public ResBody delOwn(@RequestParam int oID) {
        ResBody resBody = new ResBody();
        int i = service.delOwn(oID);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("api/findOwn")
    public ResBody findOwn(@RequestParam int page,
                              @RequestParam int limit,
                              @RequestParam String name) {
        int count = 0;
        List<own> list= new ArrayList<>();
        ResBody resBody = new ResBody();
        if (name.isEmpty()){
            count = service.getCount();
            list= service.getAllOwns(page, limit);
        } else {
            count = service.getCount(name);
            list= service.findown(page, limit, name);
        }
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/ajax/getAllOwns")
    public ResBody getAllOwns() {
        ResBody resBody = new ResBody();
        List<own> list= service.getAllOwns();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/updateOwnsPass")
    public ResBody updateOwnsPass(@RequestBody Map<String, Object> params,
                                     HttpSession session) {
        ResBody resBody = new ResBody();
        String newPsw = params.get("newPsw").toString();
        own own1 = (own) session.getAttribute("own");
        own1.setOPassword(newPsw);
        int i = service.updateOwnsPass(own1.getOID(),newPsw);
        if (i != 1){
            resBody.setCode(500);
            resBody.setMsg("修改失败，后台出错");
        }else {
            session.setAttribute("own",own1);
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }
        return resBody;
    }
}
