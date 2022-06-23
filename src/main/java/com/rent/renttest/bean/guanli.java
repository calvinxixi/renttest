package com.rent.renttest.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //@Data 生成getter,setter ,toString等函数
@AllArgsConstructor        //@NoArgsConstructor 生成无参构造函数
@NoArgsConstructor        //@AllArgsConstructor //生成全参数构造函数
public class guanli {
    Integer gID;
    String gName;
    String gPassword;
    String gRemark;
    String gPhone;
}
