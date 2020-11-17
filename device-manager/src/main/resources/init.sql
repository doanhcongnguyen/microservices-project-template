create table device (
    serial varchar(255) not null,
     created_by varchar(255),
     created_date datetime,
     last_modified_by varchar(255),
     last_modified_date datetime,
     model_name varchar(255),
     software_version varchar(255),
     status varchar(255),
     primary key (serial)
 )