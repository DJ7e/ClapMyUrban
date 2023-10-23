-- create database dbms_proj;
-- use  dbms_proj;

-- DROP DATABASE dbms_proj;--


create table website_user (
 user_id bigint NOT NULL auto_increment,
 user_fname VARCHAR(255) NOT NULL,
-- user_mname VARCHAR(255) DEFAULT NULL,
 user_lname VARCHAR(255) DEFAULT NULL,
 user_phone_number VARCHAR(20) NOT NULL,
 user_email_id VARCHAR(50) NOT NULL,
 user_password VARCHAR(50) NOT NULL,
 user_address VARCHAR(255) NOT NULL,
 PRIMARY KEY (user_id)
 );



 create table website_admin (
 admin_id bigint NOT NULL auto_increment,
 admin_fname VARCHAR(255) NOT NULL,
 admin_mname VARCHAR(255) DEFAULT NULL,
 admin_lname VARCHAR(255) DEFAULT NULL,
 admin_phone_number VARCHAR(20) NOT NULL,
 admin_email_id VARCHAR(50) NOT NULL,
 admin_password VARCHAR(50) NOT NULL,
 admin_address VARCHAR(255) NOT NULL,
 PRIMARY KEY (admin_id)
 );

  create table service_provider (
 provider_id bigint NOT NULL auto_increment,
 provider_fname VARCHAR(255) NOT NULL,
 provider_mname VARCHAR(255) DEFAULT NULL,
 provider_lname VARCHAR(255) DEFAULT NULL,
 provider_phone_number VARCHAR(20) NOT NULL,
 provider_email_id VARCHAR(50) NOT NULL,
 provider_password VARCHAR(50) NOT NULL,
 provider_address VARCHAR(255) NOT NULL,
 provider_description VARCHAR(500) NOT NULL,
 PRIMARY KEY (provider_id)
 );


 create table category (
 category_id bigint NOT NULL auto_increment,
 category_name VARCHAR(255) NOT NULL,
 category_provider bigint NOT NULL,
 PRIMARY KEY (category_id),
 constraint category_ibfk_1 foreign key (category_provider) references service_provider(provider_id) ON DELETE CASCADE ON UPDATE CASCADE
 );


 create table service (
 service_id bigint NOT NULL auto_increment,
 service_name VARCHAR(255) NOT NULL,
 service_price bigint NOT NULL,
 service_description VARCHAR(500) NOT NULL,
 service_category bigint NOT NULL,
 PRIMARY KEY (service_id),
 constraint service_ibfk_1 foreign key (service_category) references category(category_id) ON DELETE CASCADE ON UPDATE CASCADE
 );



  create table slot (
 slot_id bigint NOT NULL auto_increment,
 slot_time time default null,
 slot_date date default null,
 slot_service bigint NOT NULL,
 slot_provider bigint NOT NULL,
 slot_user bigint NOT NULL,
 primary key (slot_id),
 constraint slot_ibfk_1 foreign key (slot_provider) references service_provider(provider_id) ON DELETE CASCADE ON UPDATE CASCADE,
 constraint slot_ibfk_2 foreign key (slot_service) references service(service_id) ON DELETE CASCADE ON UPDATE CASCADE,
 constraint slot_ibfk_3 foreign key (slot_user) references website_user(user_id) ON DELETE CASCADE ON UPDATE CASCADE
 );




 create table orders (
 order_id bigint NOT NULL auto_increment,
 order_booked_time time default null,
 order_booked_date date default null,
 order_time time default null,
 order_date date default null,
 order_status VARCHAR(255) NOT NULl,
 order_slot bigint NOT NULL,
 order_service bigint NOT NULL,
 order_user bigint NOT NULL,
 PRIMARY KEY (order_id),
 constraint orders_ibfk_1 foreign key (order_slot) references slot(slot_id) ON DELETE CASCADE ON UPDATE CASCADE,
 constraint orders_ibfk_2 foreign key (order_service) references service(service_id) ON DELETE CASCADE ON UPDATE CASCADE,
 constraint orders_ibfk_3 foreign key (order_user) references website_user(user_id) ON DELETE CASCADE ON UPDATE cascade
 );