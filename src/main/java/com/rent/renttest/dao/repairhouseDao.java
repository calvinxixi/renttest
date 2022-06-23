package com.rent.renttest.dao;

import com.rent.renttest.bean.house;
import com.rent.renttest.bean.own;
import com.rent.renttest.bean.repairhouse;
import com.rent.renttest.bean.tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class repairhouseDao {
    @Autowired
    JdbcTemplate template;

    public int getCount() {
        int count = template.queryForObject("select count(*) from repairhouse", Integer.class);
        return count;
    }

//    public int getCount(String name) {
//        int count = template.queryForObject("select count(*) from lease where LID like '%"+name+"%' ", Integer.class);
//        return count;
//    }

    public int getCount(int xID) {
        int count = template.queryForObject("select count(*) from repairhouse where xID like '%"+xID+"%' ", Integer.class);
        return count;
    }

    public int getFreeCount() {
        int count = template.queryForObject("select count(*) from repairhouse where xActive = 0", Integer.class);
        return count;
    }

    public List<repairhouse> getAllRepairhouse(int page, int limit) {
        List<repairhouse> list = template.query("select * from repairhouse limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(repairhouse.class));
        if (list!=null){
            //分别取对应的房号，房东名，租客名，
            for (repairhouse repairhouse:list){
                List<house> houses = template.query("select * from house where hID = ?" ,new Object[]{repairhouse.getX_hID()},
                        new BeanPropertyRowMapper(house.class));
                repairhouse.setHouse(houses.get(0));
            }
            for (repairhouse repairhouse:list){
                List<own> owns = template.query("select * from own where oID = ?" ,new Object[]{repairhouse.getXPerson()},
                        new BeanPropertyRowMapper(own.class));
                repairhouse.setOwn(owns.get(0));
            }
            for (repairhouse repairhouse:list){
                List<tenant> tenants = template.query("select * from tenant where tID = ?" ,new Object[]{repairhouse.getXSponsor()},
                        new BeanPropertyRowMapper(tenant.class));
                repairhouse.setTenant(tenants.get(0));
            }
            return list;
        }else {
            return null;
        }
    }

    public int addRepairhouse(repairhouse repairhouse1) {
        return template.update("insert into repairhouse values (null,?,?,?,?,?,?,?,?,?)",
                repairhouse1.getX_hID(),repairhouse1.getXActive(),repairhouse1.getXContent(),repairhouse1.getXType(),repairhouse1.getXSponsor(),repairhouse1.getXPerson()
                ,repairhouse1.getXTime(),repairhouse1.getXCharge(),repairhouse1.getXFinish());
    }


    public int delRepairhouse(int xID) {return template.update("DELETE from repairhouse where xID = ?",xID); }

    public int updateRepairhouse(repairhouse repairhouse1){
        return template.update("update repairhouse set xActive = ? ,xContent = ?, xType = ?, xCharge = ?, xFinish = ? where xID = ?",
                repairhouse1.getXActive(),repairhouse1.getXContent(),repairhouse1.getXType(),repairhouse1.getXCharge(),repairhouse1.getXFinish(),repairhouse1.getXID());
    }
}
