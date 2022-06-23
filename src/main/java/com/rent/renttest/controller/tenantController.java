package com.rent.renttest.controller;

import com.rent.renttest.bean.*;
import com.rent.renttest.service.tenantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class tenantController {
    @Autowired
    tenantService service;

    private static final Logger LOG = LoggerFactory.getLogger(tenantController.class);

    @RequestMapping("/tenant/loginByPassword")
    public ResBody loginByPassword(@RequestBody Map<String, Object> params, HttpSession session){
        ResBody resBody = new ResBody();
        String Name = params.get("username").toString();
        String Password = params.get("password").toString();

        tenant tenants = service.findtenant(Name,Password);
        if (tenants == null){
            resBody.setCode(500);
            resBody.setMsg("登录失败，请重新登录");
        }else {
            session.setAttribute("tenant", tenants);
            LOG.info(tenants.toString());
            resBody.setCode(200);
            resBody.setMsg("登陆成功");
        }

        return resBody;
    }

    @GetMapping("api/getTenants")
    public ResBody getOwns(@RequestParam int page, @RequestParam int limit){
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<tenant> list= service.getAllTenants(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("api/addTenant")
    public ResBody addOwn(@RequestBody tenant tenant1) {
        ResBody resBody = new ResBody();
        int i = service.addTenant(tenant1);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("api/updateTenant")
    public ResBody updateOwn(@RequestBody tenant tenant1) {
        ResBody resBody = new ResBody();
        int i = service.updateTenant(tenant1);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("api/delTenant")
    public ResBody delOwn(@RequestParam int tID) {
        ResBody resBody = new ResBody();
        int i = service.delTenant(tID);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("api/findTenant")
    public ResBody findOwn(@RequestParam int page,
                           @RequestParam int limit,
                           @RequestParam String name) {
        int count = 0;
        List<tenant> list= new ArrayList<>();
        ResBody resBody = new ResBody();
        if (name.isEmpty()){
            count = service.getCount();
            list= service.getAllTenants(page, limit);
        } else {
            count = service.getCount(name);
            list= service.findtenant(page, limit, name);
        }
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/ajax/getAllTenants")
    public ResBody getAllTenants() {
        ResBody resBody = new ResBody();
        List<tenant> list= service.getAllTenants();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/updateTenantsPass")
    public ResBody updateTenantsPass(@RequestBody Map<String, Object> params,
                              HttpSession session) {
        ResBody resBody = new ResBody();
        String newPsw = params.get("newPsw").toString();
        tenant tenant1 = (tenant) session.getAttribute("tenant");
        tenant1.setTPassword(newPsw);
        int i = service.updateTenantsPass(tenant1.getTID(),newPsw);
        if (i != 1){
            resBody.setCode(500);
            resBody.setMsg("修改失败，后台出错");
        }else {
            session.setAttribute("tenant",tenant1);
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }
        return resBody;
    }
}
