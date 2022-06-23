package com.rent.renttest.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class house {
    Integer hID;
    String hRemark;
    String FczID;
    String hArea;
    String hHx;
    String hAddress;
    Integer Active_House;
    Integer Charge_Mode;
    float hDeposit;
    float hRent;
}
