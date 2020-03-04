alter table register_user add COLUMN state int COMMENT '激活状态 0:未激活,1:已激活';
alter table register_user add COLUMN code varchar(36) COMMENT '激活码';