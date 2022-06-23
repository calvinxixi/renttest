package com.rent.renttest.dao;

import com.rent.renttest.bean.house;
import com.rent.renttest.bean.lease;
import com.rent.renttest.bean.own;
import com.rent.renttest.bean.tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class leaseDao {
    @Autowired
    JdbcTemplate template;

    public List<lease> findLease(int page, int limit, int LID){
        List<lease> list = template.query("select * from lease where lID = '"+LID+"' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(lease.class));
        if (list!=null){
                return list;
        }else{
            return null;
        }
    }


    public int getCount() {
        int count = template.queryForObject("select count(*) from lease", Integer.class);
        return count;
    }

//    public int getCount(String name) {
//        int count = template.queryForObject("select count(*) from lease where LID like '%"+name+"%' ", Integer.class);
//        return count;
//    }

    public int getCount(int lID) {
        int count = template.queryForObject("select count(*) from lease where LID like '%"+lID+"%' ", Integer.class);
        return count;
    }

    public List<lease> getAllLease(int page, int limit) {
        List<lease> list = template.query("select * from lease limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(lease.class));
        if (list!=null){
            //分别取对应的房号，房东名，租客名，
            for (lease lease:list){
                List<house> houses = template.query("select * from house where hID = ?" ,new Object[]{lease.getL_hID()},
                        new BeanPropertyRowMapper(house.class));
                lease.setHouse(houses.get(0));
            }
            for (lease lease:list){
                List<own> owns = template.query("select * from own where oID = ?" ,new Object[]{lease.getL_oID()},
                        new BeanPropertyRowMapper(own.class));
                lease.setOwn(owns.get(0));
            }
            for (lease lease:list){
                List<tenant> tenants = template.query("select * from tenant where tID = ?" ,new Object[]{lease.getL_tID()},
                        new BeanPropertyRowMapper(tenant.class));
                lease.setTenant(tenants.get(0));
            }
            return list;
        }else {
            return null;
        }
    }

    public int addLease(lease lease1) {
        Integer i =  changeactive(lease1, 1);
        return template.update("insert into lease values (null,?,?,?,?,?,?,?,?,?,?,?)",
                lease1.getL_hID(),lease1.getL_oID(),lease1.getL_tID(),lease1.getLSign_time(),lease1.getLLease_limit(),lease1.getLCash()
                ,lease1.getLRent(),lease1.getLCharge_date(),lease1.getLWater(),lease1.getLEletric(),lease1.getLRemark());
    }

    int changeactive(lease lease1, int active){
        return template.update("update house set Active_House = ? where hID = ?",
                active, lease1.getL_hID());
    }


    public int delLease(int lID, int L_hid) {Integer i =  change(L_hid); return template.update("DELETE from lease where LID = ?",lID); }

    int change( int L_hid){
        Integer active = 0;
        return template.update("update house set Active_House = ? where hID = ?",
         active,  L_hid);
    }

    public List<lease> getAllLease() {
        List<lease> list = template.query("select * from lease" ,
                new BeanPropertyRowMapper(lease.class));
        if (list!=null){
            return list;
        }else {
            return null;
        }
    }
}
