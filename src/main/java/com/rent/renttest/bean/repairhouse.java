package com.rent.renttest.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class repairhouse {
    Integer xID;
    Integer x_hID;
    Integer xActive;
    String xContent;
    String xType;
    Integer xSponsor;
    Integer xPerson;

    //注解@JsonFormat主要是后台到前台的时间格式的转换
    //注解@DataFormat主要是前后到后台的时间格式的转换
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    Date xTime;

    Float xCharge;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    Date xFinish;

    house house;
    own own;
    tenant tenant;
}
