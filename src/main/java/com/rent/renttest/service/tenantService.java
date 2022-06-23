package com.rent.renttest.service;

import com.rent.renttest.bean.tenant;
import com.rent.renttest.dao.tenantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tenantService {
    @Autowired
    tenantDao dao;

    public tenant findtenant(String name, String password) {return dao.findtenant(name, password);}

    public List<tenant> findtenant(int page, int limit, String tName){return dao.findtenant(page, limit, tName);}

    public int getCount(){return dao.getCount();}

    public int getCount(String name){return dao.getCount(name);}

    public List<tenant> getAllTenants(int page, int limit){return dao.getAllTenants(page, limit);}

    public int addTenant(tenant tenant1){return dao.addTenant(tenant1);}

    public int updateTenant(tenant tenant1){return dao.updateTenant(tenant1);}

    public int delTenant(int tID){return dao.delTenant(tID);}

    public List<tenant> getAllTenants(){return dao.getAllTenants();}

    public int updateTenantsPass(Integer tid, String newPsw) {return dao.updateTenantsPass(tid, newPsw); }
}
