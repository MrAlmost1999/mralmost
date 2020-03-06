alter table user modify column account_id varchar(36) not null COMMENT '用户唯一标识,github用户为github提供,注册用户为UUID';
alter table user modify column avatar varchar(50) not null COMMENT '用户注册码';