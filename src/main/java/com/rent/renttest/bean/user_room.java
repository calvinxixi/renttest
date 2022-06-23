package com.rent.renttest.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class user_room {
    Integer id;
    Integer user_id;
    Integer room_id;
    Date inTime;
    Date outTime;
    tenant tenant;
    house house;
}