drop table T_ACCOUNT if exists;

create table T_ACCOUNT (ID bigint identity primary key, NUMBER varchar(9),
                        NAME varchar(50) not null, BALANCE decimal(8,2), PRODUCT varchar(50), unique(NUMBER));
                        
ALTER TABLE T_ACCOUNT ALTER COLUMN BALANCE SET DEFAULT 0.0;
ALTER TABLE T_ACCOUNT ALTER COLUMN PRODUCT SET DEFAULT 'Beer';

drop table T_PRODUCT if exists;

create table T_PRODUCT (ID bigint identity primary key, NUMBER varchar(9),
                        NAME varchar(50) not null, PRICE decimal(6,2), unique(NUMBER));

ALTER TABLE T_PRODUCT ALTER COLUMN PRICE SET DEFAULT 0.0;