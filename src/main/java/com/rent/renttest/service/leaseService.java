package com.rent.renttest.service;

import com.rent.renttest.bean.lease;
import com.rent.renttest.dao.leaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class leaseService {
    @Autowired
    leaseDao dao;

    public List<lease> findLease(int page, int limit, int LID){return dao.findLease(page, limit, LID);}

    public int getCount(){return dao.getCount();}

    public int getCount(int lID){return dao.getCount(lID);}

    public List<lease> getAllLease(int page, int limit){return dao.getAllLease(page, limit);}

    public int addLease(lease lease1){return dao.addLease(lease1);}

    public int delLease(int lID,int L_hid){return dao.delLease(lID,L_hid);}

    public List<lease> getAllLeases() {return dao.getAllLease();}
}
