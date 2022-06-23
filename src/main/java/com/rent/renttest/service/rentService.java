package com.rent.renttest.service;

import com.rent.renttest.bean.rent;
import com.rent.renttest.dao.rentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class rentService {
    @Autowired
    rentDao dao;

    public List<rent> findRent(int page, int limit, int RID){return dao.findRent(page, limit, RID);}

    public int getCount(){return dao.getCount();}

    public int getFreeCount(){return dao.getFreeCount();}

    public int getCount(int rID){return dao.getCount(rID);}

    public List<rent> getAllRent(int page, int limit){return dao.getAllRent(page, limit);}

    public int addRent(rent rent1){return dao.addRent(rent1);}

    public int delRent(int rID){return dao.delRent(rID);}

    public int updateRent(rent rent1) {return dao.updateRent(rent1); }
}
