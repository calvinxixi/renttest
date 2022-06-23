package com.rent.renttest.service;

import com.rent.renttest.bean.guanli;
import com.rent.renttest.dao.guanliDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class guanliService {
    @Autowired
    guanliDao dao;

    public guanli findGuanli(String gName, String gPassword) {return dao.findGuanli(gName,gPassword);}

    public int updategPass(int gName, String newPsw){return dao.updategPass(gName, newPsw);}

    public List<guanli> findGuanli(int page, int limit, String gName){return dao.findGuanli(page, limit, gName);}

    public int getCount(){return dao.getCount();}

    public int getCount(String name){return dao.getCount(name);}

    public int getCount(int gID){return dao.getCount(gID);}

    public List<guanli> getAllGuanli(int page, int limit){return dao.getAllGuanli(page, limit);}

    public int addGuanli(guanli guanli){return dao.addGuanli(guanli);}

    public int updateGuanli(guanli guanli1){return dao.updateGuanli(guanli1);}

    public int delGuanli(int gID){return dao.delGuanli(gID);}

    public int updateGuanlisPass(Integer gid, String newPsw) {return dao.updateGuanlisPass(gid,newPsw); }
}
