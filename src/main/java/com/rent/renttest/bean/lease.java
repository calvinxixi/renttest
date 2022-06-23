package com.rent.renttest.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class lease {
    Integer LID;
    Integer L_hID;
    Integer L_oID;
    Integer L_tID;
    Date lSign_time;
    Date lLease_limit;
    Float lCash;
    Float lRent;
    Date lCharge_date;
    Float lWater;
    Float lEletric;
    String lRemark;
    house house;
    own own;
    tenant tenant;
}
