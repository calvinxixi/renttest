package com.rent.renttest.dao;


import com.rent.renttest.bean.lease;
import com.rent.renttest.bean.rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class rentDao {
    @Autowired
    JdbcTemplate template;

    public List<rent> findRent(int page, int limit, int r_LID){
        List<rent> list = template.query("select * from rent where r_LID = '"+r_LID+"' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(rent.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }


    public int getCount() {
        int count = template.queryForObject("select count(*) from rent", Integer.class);
        return count;
    }

    public int getFreeCount() {
        int count = template.queryForObject("select count(*) from rent where rActive = 0", Integer.class);
        return count;
    }


    public int getCount(int r_LID) {
        int count = template.queryForObject("select count(*) from rent where r_LID = "+r_LID+" ", Integer.class);
//        System.out.println("返回数据2=="+count);
        return count;
    }

    public List<rent> getAllRent(int page, int limit) {
        List<rent> list = template.query("select * from rent limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(rent.class));
        if (list!=null){
            //分别取对应的房号，房东名，租客名，
            for (rent rent:list){
                List<lease> leases = template.query("select * from lease where lID = ?" ,new Object[]{rent.getR_LID()},
                        new BeanPropertyRowMapper(lease.class));
                rent.setLease(leases.get(0));
            }
            return list;
        }else {
            return null;
        }
    }

    public int addRent(rent rent1) {
        return template.update("insert into rent values (null,?,?,?,?,?,?,?,?,?)",
                rent1.getR_LID(),rent1.getRLastwater(),rent1.getRWater(),rent1.getRLasteletric(),rent1.getREletric(),rent1.getRRent()
                ,rent1.getRTime(),rent1.getRActive(),rent1.getRRemark());
    }


    public int delRent(int rID) {return template.update("DELETE from rent where rID = ?",rID); }

    public int updateRent(rent rent1) {
        System.out.println("success");
        Integer ee = template.update("update rent set rActive = ?, rRemark = ? where rID = ?",
                rent1.getRActive(),rent1.getRRemark(),rent1.getRID());
        System.out.println(ee);
        Integer sisi = rent1.getRID();
        System.out.println("sisi"+ sisi);
        return ee;
    }
}
