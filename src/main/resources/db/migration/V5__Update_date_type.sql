alter table comment modify column gmt_create varchar(50);
alter table comment modify column gmt_modified varchar(50);

alter table question modify column gmt_create varchar(50);
alter table question modify column gmt_modified varchar(50);

alter table user modify column gmt_create varchar(50);
alter table user modify column gmt_modified varchar(50);

alter table record modify column record_date varchar(50);