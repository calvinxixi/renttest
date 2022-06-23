package com.rent.renttest.dao;

import com.rent.renttest.bean.house;
import com.rent.renttest.bean.own;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ownDao {
    @Autowired
    JdbcTemplate template;

    public own findown(String oName, String oPassword){
        List<own> list = template.query("select * from own where oName = ? && oPassword = ?",
                new Object[]{oName,oPassword}, new BeanPropertyRowMapper(own.class));
        if (list!=null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    public List<own> findown(int page, int limit, String oName){
        List<own> list = template.query("select * from own where oName = '"+oName+"' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(own.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }


    public int getCount() {
        int count = template.queryForObject("select count(*) from own", Integer.class);
        return count;
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from own where oName like '%"+name+"%' ", Integer.class);
        return count;
    }

    public int getCount(int oID) {
        int count = template.queryForObject("select count(*) from own where oID like '%"+oID+"%' ", Integer.class);
        return count;
    }

    public List<own> getAllOwns(int page, int limit) {
        List<own> list = template.query("select * from own limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(own.class));
        if (list!=null){
            return list;
        }else {
            return null;
        }
    }

    public int addOwns(own own1) {
        return template.update("insert into own values (null,?,?,?,?,?)",
                own1.getOName(),own1.getOPassword(),own1.getORemark(),own1.getOPhone(),own1.getOWechat());
    }

    public int updateOwn(own own1) {
        return template.update("update own set oName = ?, oPassword = ?, oRemark = ?, oPhone = ?, oWechat = ? where oID = ?",
                own1.getOName(),own1.getOPassword(),own1.getORemark(),own1.getOPhone(),own1.getOWechat(),own1.getOID());
    }

    public int delOwn(int oID) {return template.update("DELETE from own where oID = ?",oID); }

    public List<own> getAllOwns() {
        List<own> list = template.query("select * from own" ,
                new BeanPropertyRowMapper(own.class));
        if (list!=null){
            return list;
        }else {
            return null;
        }
    }

    public int updateOwnsPass(Integer oid, String newPsw) {
        return template.update("update `own` set `oPassword` = ? where oID = ?",
                newPsw,oid);
    }
}
