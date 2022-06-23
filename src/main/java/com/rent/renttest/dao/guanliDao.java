package com.rent.renttest.dao;

import com.rent.renttest.bean.guanli;
import com.rent.renttest.bean.own;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class guanliDao {
    @Autowired
    JdbcTemplate template;

    public guanli findGuanli(String gName, String gPassword){
        List<guanli> list = template.query("select * from guanli where gName = ? && gPassword = ?",
                new Object[]{gName,gPassword}, new BeanPropertyRowMapper(guanli.class));
        if (list!=null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    public int updategPass(int gName, String newPsw){
        return template.update("update guanli set gPassword = ? where gName = ?",
                newPsw, gName);
    }

    public List<guanli> findGuanli(int page, int limit, String gName){
        List<guanli> list = template.query("select * from guanli where gName = '"+gName+"' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(guanli.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }


    public int getCount() {
        int count = template.queryForObject("select count(*) from guanli", Integer.class);
        return count;
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from guanli where gName like '%"+name+"%' ", Integer.class);
        return count;
    }

    public int getCount(int gID) {
        int count = template.queryForObject("select count(*) from guanli where gID like '%"+gID+"%' ", Integer.class);
        return count;
    }

    public List<guanli> getAllGuanli(int page, int limit) {
        List<guanli> list = template.query("select * from guanli limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(guanli.class));
        if (list!=null){
            return list;
        }else {
            return null;
        }
    }

    public int addGuanli(guanli guanli) {
        return template.update("insert into guanli values (null,?,null,?,?)",
                guanli.getGName(),guanli.getGRemark(),guanli.getGPhone());
    }

    public int updateGuanli(guanli guanli1) {
        return template.update("update guanli set gName = ?, gRemark = ?, gPhone = ? where gID = ?",
                guanli1.getGName(),guanli1.getGRemark(),guanli1.getGPhone(),guanli1.getGID());
    }

    public int delGuanli(int gID) {return template.update("DELETE from guanli where gID = ?",gID); }

    public int updateGuanlisPass(Integer gid, String newPsw) {
        return template.update("update `guanli` set `gPassword` = ? where gID = ?",
                newPsw,gid);
    }
}
