package com.rent.renttest.dao;

import com.rent.renttest.bean.own;
import com.rent.renttest.bean.tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class tenantDao {
    @Autowired
    JdbcTemplate template;

    public tenant findtenant(String tName, String tPassword){
        List<tenant> list = template.query("select * from tenant where tName = ? && tPassword = ?",
                new Object[]{tName,tPassword}, new BeanPropertyRowMapper(tenant.class));
        if (list!=null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    public List<tenant> findtenant(int page, int limit, String tName){
        List<tenant> list = template.query("select * from tenant where tName = '"+tName+"' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(tenant.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }


    public int getCount() {
        int count = template.queryForObject("select count(*) from tenant", Integer.class);
        return count;
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from tenant where tName like '%"+name+"%' ", Integer.class);
        return count;
    }

//    public int getCount(int oID) {
//        int count = template.queryForObject("select count(*) from own where oID like '%"+oID+"%' ", Integer.class);
//        return count;
//    }

    public List<tenant> getAllTenants(int page, int limit) {
        List<tenant> list = template.query("select * from tenant limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(tenant.class));
        if (list!=null){
            return list;
        }else {
            return null;
        }
    }

    public int addTenant(tenant tenant1) {
        return template.update("insert into tenant values (null,?,null,?,?,?,?,?,?)",
                tenant1.getTName(),tenant1.getTRemark(),tenant1.getTPhone(),tenant1.getTWechat(),
                tenant1.getTGender(),tenant1.getTActive(),tenant1.getTIDCard());
    }

    public int updateTenant(tenant tenant1) {
        return template.update("update tenant set tName = ?, tRemark = ?, tPhone = ?, tWechat = ?, tGender = ?, tActive = ?,tIDCard = ? where tID = ?",
                tenant1.getTName(),tenant1.getTRemark(),tenant1.getTPhone(),tenant1.getTWechat(),tenant1.getTGender(),tenant1.getTActive(),tenant1.getTIDCard(),tenant1.getTID());
    }

    public int delTenant(int tID) {return template.update("DELETE from tenant where tID = ?",tID); }

    public List<tenant> getAllTenants() {
        List<tenant> list = template.query("select * from tenant" ,
                new BeanPropertyRowMapper(tenant.class));
        if (list!=null){
            return list;
        }else {
            return null;
        }
    }

    public int updateTenantsPass(Integer tid, String newPsw) {
            return template.update("update `tenant` set `tPassword` = ? where tID = ?",
                    newPsw,tid);
    }
}
