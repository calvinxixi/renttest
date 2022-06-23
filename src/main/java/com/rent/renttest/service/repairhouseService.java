package com.rent.renttest.service;

import com.rent.renttest.bean.repairhouse;
import com.rent.renttest.dao.repairhouseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class repairhouseService {
    @Autowired
    repairhouseDao dao;

    public int getCount(){return dao.getCount();}

    public int getCount(int xID){return dao.getCount(xID);}

    public int getFreeCount(){return dao.getFreeCount();}

    public List<repairhouse> getAllRepairhouse(int page, int limit){return dao.getAllRepairhouse(page, limit);}

    public int addRepairhouse(repairhouse repairhouse1){return dao.addRepairhouse(repairhouse1);}

    public int delRepairhouse(int xID){return dao.delRepairhouse(xID);}

    public int updateRepairhouse(repairhouse repairhouse1){return dao.updateRepairhouse(repairhouse1);}
}
