package com.rent.renttest.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tenant {
    Integer tID;
    String tName;
    String tPassword;
    String tRemark;
    String tPhone;
    String tWechat;
    String tGender;
    Date tActive;
    String tIDCard;

}
