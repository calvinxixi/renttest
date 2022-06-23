package com.rent.renttest.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class rent {
    Integer rID;
    String r_LID;
    Float rLastwater;
    Float rWater;
    Float rLasteletric;
    Float rEletric;
    Float rRent;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    Date rTime;
    Integer rActive;
    String rRemark;
    lease lease;
}
