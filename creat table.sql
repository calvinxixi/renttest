# 创建房屋表
CREATE TABLE  IF NOT EXISTS house
(
hID int COMMENT '房号',
hRemark VARCHAR(255) COMMENT '备注',FczID VARCHAR(25) COMMENT '房产证',
hArea VARCHAR(255) COMMENT '房屋面积',
hHx VARCHAR(255) COMMENT '户型',
hAddress VARCHAR(255) COMMENT '房屋地址',
Active_House INT COMMENT '房屋状态',
Charge_Mode INT COMMENT '收费模式',
hDeposit FLOAT COMMENT '押金',
hRent FLOAT COMMENT '租金',
-- Charge_Date DATE COMMENT '收费日期',
-- Lease_Limit DATE COMMENT '租约期限',
PRIMARY KEY(hID)
);

#创建管理员表
CREATE TABLE  IF NOT EXISTS guanli
(
gID int COMMENT '管理员号',
gName char(50) COMMENT '管理员名',
gPassword char(50) COMMENT '密码',
gRemark char(255) COMMENT '备注',
gPhone varchar(255) COMMENT '管理员电话',
PRIMARY KEY(gID),
UNIQUE KEY(gName)
);

#创建房东信息表
CREATE TABLE  IF NOT EXISTS Own
(
oID int COMMENT '房东号',
oName char(50) COMMENT '房东名',
oPassword char(50) COMMENT '密码',
oRemark char(255) COMMENT '备注',
oPhone char(20) COMMENT '手机号',
oWechat char(50) COMMENT '微信号',
PRIMARY KEY(oID),
UNIQUE KEY(oName)
);

#创建租户信息表
CREATE TABLE  IF NOT EXISTS tenant
(
tID int COMMENT '租客号',
tName char(50) COMMENT '租客名',
tPassword char(50) COMMENT '密码',
tRemark char(255) COMMENT '备注',
tPhone char(20) COMMENT '手机号',
tWechat char(50) COMMENT '微信',
tGender CHAR(10) COMMENT '性别',
-- tLease_Limit date COMMENT '租约期限',
tActive date COMMENT '核酸日期',
PRIMARY KEY(tID),
UNIQUE KEY(tName)
);

#创建租约信息表
CREATE TABLE  IF NOT EXISTS lease
(
LID int COMMENT '租约号',
L_hID INT COMMENT '房号',
L_oID int COMMENT '房东号',
L_tID int COMMENT '租客号',
lSign_time date COMMENT '签约时间',
lLease_limit date COMMENT '租约期限',
lCash FLOAT COMMENT '押金',
lRent FLOAT COMMENT '租金',
lCHarge_date date COMMENT '收费日期',
lWater FLOAT COMMENT '水费',
lEletric FLOAT COMMENT '电费',
lRemark CHAR(255) COMMENT '备注',
PRIMARY KEY(LID),
FOREIGN KEY (L_hID) REFERENCES house(hID),
FOREIGN KEY (L_oID) REFERENCES own(oID),
FOREIGN KEY (L_tID) REFERENCES tenant(tID)
);

#创建维修信息表
CREATE TABLE  IF NOT EXISTS repairhouse
(
xID int COMMENT '维修号',
x_hID INT COMMENT '房号',
xActive int COMMENT '维修完成状态',
xContent VARCHAR(255) COMMENT '维修详情',
xType VARCHAR(255) COMMENT '维修类型',
xSponsor int COMMENT '维修发起人',
xPerson int COMMENT '维修负责人',
xTime datetime COMMENT '维修发起时间',
xCharge FLOAT COMMENT '维修金额',
xFinish datetime COMMENT '维修完成时间',
PRIMARY KEY(xID),
FOREIGN KEY (x_hID) REFERENCES house(hID),
FOREIGN KEY (xSponsor) REFERENCES tenant(tID),
FOREIGN KEY (xPerson) REFERENCES own(oID)
);

#创建收租表
CREATE TABLE  IF NOT EXISTS rent
(
rID int COMMENT '租约号',
r_LID INT COMMENT '房号',
rLastwater FLOAT COMMENT '上次水立方数',
rWater FLOAT COMMENT '本次水立方数',
rLasteletric FLOAT COMMENT '上次电表读数',
rEletric FLOAT COMMENT '本次电表读数',
rRent FLOAT COMMENT '房租',
rTime datetime COMMENT '收租时间',
rActive int COMMENT '收租状态',
rRemark VARCHAR(255) COMMENT '备注',
PRIMARY KEY(rID),
FOREIGN KEY (r_LID) REFERENCES house(hID)
);

-- 删除表
-- ALTER TABLE rent DROP FOREIGN KEY rent_ibfk_1;
-- ALTER TABLE repairhouse DROP FOREIGN KEY repairhouse_ibfk_1;
-- ALTER TABLE repairhouse DROP FOREIGN KEY repairhouse_ibfk_2;
-- ALTER TABLE repairhouse DROP FOREIGN KEY repairhouse_ibfk_3;
-- ALTER TABLE lease DROP FOREIGN KEY lease_ibfk_1;
-- ALTER TABLE lease DROP FOREIGN KEY lease_ibfk_2;
-- ALTER TABLE lease DROP FOREIGN KEY lease_ibfk_3;
-- DROP TABLE IF EXISTS rent;
-- DROP TABLE IF EXISTS guanli;
-- DROP TABLE IF EXISTS house;
-- DROP TABLE IF EXISTS lease;
-- DROP TABLE IF EXISTS own;
-- DROP TABLE IF EXISTS tenant;
-- DROP TABLE IF EXISTS repairhouse;

-- 插入管理员表
INSERT INTO guanli VALUES (001, 'admin', 'admin', 'NO','13326578856');
INSERT INTO guanli VALUES (002, 'ceshi2', '123456', 'wu','13326578856');
INSERT INTO guanli VALUES (003, 'admin3', '123456', 'NO','13326578856');

-- 插入房屋数据
INSERT INTO house VALUES (100001,'1','1002531024','40','一房一厅','广州市花都区海珠康臣街33号2楼201',1,2,500,500);
INSERT INTO house VALUES (100002,'1','1002531024','60','两房一厅','广州市花都区海珠康臣街33号2楼202',1,2,700,700);
INSERT INTO house VALUES (100003,'1','1002531024','20','单间','广州市花都区海珠康臣街33号2楼203',1,2,400,400);
INSERT INTO house VALUES (100004,'1','1002531024','40','一房一厅','广州市花都区海珠康臣街33号3楼301',1,2,500,500);
INSERT INTO house VALUES (100005,'1','1002531024','60','两房一厅','广州市花都区海珠康臣街33号3楼302',0,2,700,700);
INSERT INTO house VALUES (100006,'1','1002531024','40','一房一厅','广州市花都区海珠康臣街33号3楼303',0,2,500,500);
INSERT INTO house VALUES (100007,'1','1002531024','40','一房一厅','广州市花都区海珠康臣街33号4楼401',1,2,500,500);
INSERT INTO house VALUES (100008,'1','1002531024','40','一房一厅','广州市花都区海珠康臣街33号2楼201',1,2,500,500);
INSERT INTO house VALUES (100009,'0','1002531024','120','三房一厅','广州市花都区海珠康臣街33号5楼501',0,3,1200,2400);

-- 插入房东信息

INSERT INTO own VALUES (200001,'own1','123456','','13225784529','13225784529');
INSERT INTO own VALUES (200002,'测试房东','123456','测试文字','13225784522','13225784522');

-- 租客信息

INSERT INTO tenant VALUES(500001,'tenant1','123456','','13326548826','13326548826','男','2022-04-01');
INSERT INTO tenant VALUES(500002,'tenant2','123456','','13326542234','13326542234','男','2022-04-01');
INSERT INTO tenant VALUES(500003,'测试人员','123456','','13234348826','13234348826','女','2022-04-01');
INSERT INTO tenant VALUES(500004,'测试3','123456','','13326234236','13326234236','女','2022-04-01');
INSERT INTO tenant VALUES(500005,'宫崎新','123456','','14232338826','14232338826','男','2022-04-01');
INSERT INTO tenant VALUES(500006,'小岛秀夫','123456','','13983658854','13983658854','女','2022-04-01');
INSERT INTO tenant VALUES(500007,'莫默妮','123456','','13845092826','13845092826','女','2022-04-01');

-- 租约表
INSERT INTO lease VALUES(20222101,100001,200001,500001,'2022-04-01','2023-04-01',500,500,'2022-04-01',4.5,1.3,'');
INSERT INTO lease VALUES(20222102,100002,200001,500002,'2022-04-01','2023-04-01',700,700,'2022-04-01',4.5,1.3,'');
INSERT INTO lease VALUES(20222103,100002,200001,500003,'2022-04-01','2023-04-01',700,700,'2022-04-01',4.5,1.3,'');
INSERT INTO lease VALUES(20222104,100003,200001,500004,'2022-04-01','2023-04-01',400,400,'2022-04-01',4.5,1.3,'');
INSERT INTO lease VALUES(20222105,100004,200001,500005,'2022-04-01','2023-04-01',500,500,'2022-04-01',4.5,1.3,'');
INSERT INTO lease VALUES(20222106,100007,200001,500006,'2022-04-01','2023-04-01',500,500,'2022-04-01',4.5,1.3,'');
INSERT INTO lease VALUES(20222107,100008,200001,500007,'2022-04-01','2023-04-01',500,500,'2022-04-01',4.5,1.3,'');

-- 维修表
INSERT INTO repairhouse VALUES(8088001,100001,1,'2月31日，房屋天花板漏水，经楼上修理水管后完成。','水电维修',500001,200001,'2022-02-28 10:10:31',40,'2022-02-28 14:31:12');
INSERT INTO repairhouse VALUES(8088002,100001,1,'2月31日，空调维修，完成。','家具维修',500001,200001,'2022-02-23 10:10:31',20,'2022-02-24 14:31:12');
INSERT INTO repairhouse VALUES(8088003,100002,1,'2月31日，房屋天花板漏水，经楼上修理水管后完成。','水电维修',500003,200001,'2022-02-28 10:10:31',40,'2022-02-28 14:31:12');

-- 收租表
INSERT INTO rent VALUES(7000001,100001,7251,7259,4521,4621,500,'2022-02-28 14:31:12',1,'');
INSERT INTO rent VALUES(7000002,100002,7251,7259,4521,4621,700,'2022-02-23 14:31:12',0,'');
INSERT INTO rent VALUES(7000003,100001,7251,7259,4521,4621,500,'2022-02-27 14:31:12',1,'');
