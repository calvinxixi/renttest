package com.rent.renttest.service;

import com.rent.renttest.bean.house;
import com.rent.renttest.dao.houseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class houseService {
    @Autowired
    houseDao dao;

    public int getCount() { return dao.getCount(); }

    public List<house> getAllhouses(int page, int limit){return dao.getAllhouses(page, limit);}

    public int addHouse(house house1){return dao.addHouse(house1);}

    public int updateHouse(house house1){return dao.updateHouse(house1);}

    public int delHouse(int hID){return dao.delHouse(hID);}

    public int getCount(String FczID){return dao.getCount(FczID);}

    public int getFreeCount(){return dao.getFreeCount();}

    public List<house> getAllFreeHouses(){return dao.getAllFreeHouses();}

    public List<house> findHouse(int page, int limit, String hID){return dao.findHouse(page, limit, hID);}

    public List<house> getAllhouses() { return dao.getAllhouses();}
}
