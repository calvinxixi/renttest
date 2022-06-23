package com.rent.renttest.dao;

import com.rent.renttest.bean.house;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class houseDao {
    @Autowired
    JdbcTemplate template;
    public int getCount(){
        //获得信息条数
        int count = template.queryForObject("select count(*) from house", Integer.class);
        return count;
    }

    public List<house> getAllhouses(int page, int limit) {
        List<house> list = template.query("select * from house limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(house.class));
        if (list!=null){
            return list;
        }else {
            return null;
        }
    }

    public int addHouse(house house1){
        return template.update("insert into house values (null,?,?,?,?,?,0,?,?,?)",
                house1.getHRemark(),house1.getFczID(),house1.getHArea(),house1.getHHx(),house1.getHAddress(),
                house1.getCharge_Mode(),house1.getHDeposit(),house1.getHRent());
    }

    public int updateHouse(house house1){
        return template.update("update house set hRemark = ? ,FczID = ?,hArea = ?, hHx = ?,hAddress = ?," +
                        "Charge_Mode = ?,hDeposit = ?,hRent = ? where hID = ?",
                house1.getHRemark(),house1.getFczID(),house1.getHArea(),house1.getHHx(),house1.getHAddress(),
                house1.getCharge_Mode(),house1.getHDeposit(),house1.getHRent(),house1.getHID());
    }

    public int delHouse(int hID){return template.update("DELETE from house where hID = ?",hID);}

    //找hid
    public int getCount(String Active_House) {
        int count = template.queryForObject("select count(*) from house where Active_House like '%"+Active_House+"%' ", Integer.class);
        return count;
    }

    public int getFreeCount() {
        int count = template.queryForObject("select count(*) from house where Active_House = 0", Integer.class);
        return count;
    }

    public List<house> getAllFreeHouses() {
        List<house> list = template.query("select * from house where Active_House = 0 ",
                new BeanPropertyRowMapper(house.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public List<house> findHouse(int page, int limit, String Active_House) {
        List<house> list = template.query("select * from house where Active_House = "+Integer.parseInt(Active_House)+" limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(house.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public List<house> getAllhouses() {
        List<house> list = template.query("select * from house" ,
                new BeanPropertyRowMapper(house.class));
        if (list!=null){
            return list;
        }else {
            return null;
        }
    }
}
