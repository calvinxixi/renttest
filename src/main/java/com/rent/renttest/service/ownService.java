package com.rent.renttest.service;

import com.rent.renttest.bean.own;
import com.rent.renttest.dao.ownDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ownService {
    @Autowired
    ownDao dao;

    public own findown(String oName, String oPassword){return dao.findown(oName,oPassword);}

    public List<own> findown(int page, int limit, String oName){return dao.findown(page, limit, oName);}

    public int getCount() {return dao.getCount();}

    public int getCount(int oID) {return dao.getCount(oID);}

    public int getCount(String name) {return dao.getCount(name);}

    public List<own> getAllOwns(int page, int limit) {return dao.getAllOwns(page, limit); }

    public int addOwns(own own1) {
        return dao.addOwns(own1);
    }

    public int updateOwn(own own1) {
        return dao.updateOwn(own1);
    }

    public int delOwn(int oID) {
        return dao.delOwn(oID);
    }

    public List<own> getAllOwns(){return dao.getAllOwns();}

    public int updateOwnsPass(Integer oid, String newPsw) {return dao.updateOwnsPass(oid,newPsw);}
}
