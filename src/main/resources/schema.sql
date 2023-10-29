--create database final;
--use final;
create table website_user (
 user_id bigint NOT NULL auto_increment,
 user_fname VARCHAR(255) NOT NULL,
 user_mname VARCHAR(255) DEFAULT NULL,
 user_lname VARCHAR(255) DEFAULT NULL,
 user_phone_number VARCHAR(20) NOT NULL,
 user_email_id VARCHAR(50) NOT NULL,
 user_password VARCHAR(50) NOT NULL,
 user_address VARCHAR(255) NOT NULL,
 user_photo varchar(255) NOT NULL,
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
 admin_photo varchar(255) NOT NULL,
 PRIMARY KEY (admin_id)
 );




 create table manager (
 manager_id bigint NOT NULL auto_increment,
 manager_fname VARCHAR(255) NOT NULL,
 manager_mname VARCHAR(255) DEFAULT NULL,
 manager_lname VARCHAR(255) DEFAULT NULL,
 manager_phone_number VARCHAR(20) NOT NULL,
 manager_email_id VARCHAR(50) NOT NULL,
 manager_password VARCHAR(50) NOT NULL,
 manager_address VARCHAR(255) NOT NULL,
 manager_description VARCHAR(500) NOT NULL,
 manager_photo varchar(255) NOT NULL,
 PRIMARY KEY (manager_id)
 );

  create table category (
 category_id bigint NOT NULL auto_increment,
 category_name VARCHAR(255) NOT NULL,
 category_manager bigint NOT NULL,
 PRIMARY KEY (category_id),
 constraint category_ibfk_1 foreign key (category_manager) references manager(manager_id) ON DELETE CASCADE ON UPDATE CASCADE
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
 provider_photo varchar(255) NOT NULL,
 provider_aadhar_number varchar(20) NOT NULL,
 provider_aadhar_verification varchar(10) NOT NULL,
 provider_account_number varchar(20) NOT NULL,
 provider_IFSC varchar(20) NOT NULL,
 provider_manager bigint NOT NULL,
 provider_category bigint NOT NULL,
 PRIMARY KEY (provider_id),
 constraint service_provider_ibfk_1 foreign key (provider_manager) references manager(manager_id) ON DELETE CASCADE ON UPDATE CASCADE,
 constraint service_provider_ibfk_2 foreign key (provider_category) references category(category_id) ON DELETE CASCADE ON UPDATE CASCADE
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
 order_status VARCHAR(255) NOT NULl,
 order_slot bigint NOT NULL,
 PRIMARY KEY (order_id),
 constraint orders_ibfk_1 foreign key (order_slot) references slot(slot_id) ON DELETE CASCADE ON UPDATE CASCADE
 );

create table offer (
offer_id bigint NOT NULL auto_increment,
discount bigint,
offer_service bigint NOT NULL,
primary key (offer_id),
constraint offer_ibfk_3 foreign key (offer_service) references service(service_id) ON DELETE CASCADE ON UPDATE CASCADE
 );

 create table cart (
 cart_id bigint NOT NULL auto_increment,
 cart_order bigint NOT NULL,
 primary key (cart_id),
 constraint cart_ibfk_1 foreign key (cart_order) references orders(order_id) ON DELETE CASCADE ON UPDATE CASCADE
 );

 create table payment (
 payment_id bigint NOT NULL auto_increment,
 payment_date date NOT NULL,
 payment_time time NOT NULL,
 payment_amount bigint,
 payment_method VARCHAR(255),
 payment_order bigint not null,
 primary key (payment_id),
 constraint payment_ibfk_1 foreign key (payment_order) references orders(order_id) ON DELETE CASCADE ON UPDATE CASCADE
 );

 -- Inserting entries in website_user table
INSERT INTO website_user (user_fname, user_mname, user_lname, user_phone_number, user_email_id, user_password, user_address, user_photo)
VALUES ('John', 'Kumar', 'Doe', '+91-9876543210', 'john.doe@email.com', 'password1', '123 Main Street, Mumbai', 'john_doe.jpg');

INSERT INTO website_user (user_fname, user_mname, user_lname, user_phone_number, user_email_id, user_password, user_address, user_photo)
VALUES ('Priya', 'Singh', 'Patel', '+91-8765432109', 'priya@email.com', 'password2', '456 Park Avenue, New Delhi', 'priya_patel.jpg');

INSERT INTO website_user (user_fname, user_mname, user_lname, user_phone_number, user_email_id, user_password, user_address, user_photo)
VALUES ('Amit', 'Kumar', 'Sharma', '+91-7654321098', 'amit.sharma@email.com', 'password3', '789 Oak Street, Bangalore', 'amit_sharma.jpg');

INSERT INTO website_user (user_fname, user_mname, user_lname, user_phone_number, user_email_id, user_password, user_address, user_photo)
VALUES ('Sneha', 'Rani', 'Verma', '+91-6543210987', 'sneha.verma@email.com', 'password4', '567 Pine Road, Chennai', 'sneha_verma.jpg');

INSERT INTO website_user (user_fname, user_mname, user_lname, user_phone_number, user_email_id, user_password, user_address, user_photo)
VALUES ('Rajesh', 'Singh', 'Mehta', '+91-5432109876', 'rajesh.mehta@email.com', 'password5', '101 Cedar Lane, Kolkata', 'rajesh_mehta.jpg');

-- select * from website_user;

-- Inserting admin entry
INSERT INTO website_admin (admin_fname, admin_mname, admin_lname, admin_phone_number, admin_email_id, admin_password, admin_address, admin_photo)
VALUES ('Anita', 'Kumari', 'Sharma', '+91-9876543210', 'anita.sharma@admin.com', 'adminPassword1', '789 Park Street, New Delhi', 'anita_sharma.jpg');

-- select * from website_admin;

-- Inserting into manager table
INSERT INTO manager (manager_fname, manager_mname, manager_lname, manager_phone_number, manager_email_id, manager_password, manager_address, manager_description, manager_photo)
VALUES ('Vikram', 'Raj', 'Gupta', '+91-9876543211',
 'vikram.gupta@email.com', 'managerPassword101', '123 Green Avenue, Mumbai', 'Experienced manager with a strong background in finance.', 'vikram_gupta.jpg');

INSERT INTO manager (manager_fname, manager_mname, manager_lname, manager_phone_number, manager_email_id, manager_password, manager_address, manager_description, manager_photo)
VALUES ('Preeti', 'Kumari', 'Mehra', '+91-8765432101', 'preeti.mehra@email.com', 'managerPassword102', '789 Skyline Road, New Delhi', 'Experienced manager with a focus on marketing and branding.', 'preeti_mehra.jpg');

INSERT INTO manager (manager_fname, manager_mname, manager_lname, manager_phone_number, manager_email_id, manager_password, manager_address, manager_description, manager_photo)
VALUES ('Rahul', 'Kumar', 'Yadav', '+91-7654321099', 'rahul.yadav@email.com', 'managerPassword103', '101 Hillside Lane, Bangalore', 'Experienced manager with a focus on operations and logistics.', 'rahul_yadav.jpg');

INSERT INTO manager (manager_fname, manager_mname, manager_lname, manager_phone_number, manager_email_id, manager_password, manager_address, manager_description, manager_photo)
VALUES ('Meenakshi', 'Rani', 'Jain', '+91-6543210988', 'meenakshi.jain@email.com', 'managerPassword104', '567 Sunshine Road, Chennai', 'Experienced manager with a focus on human resources and personnel management.', 'meenakshi_jain.jpg');

INSERT INTO manager (manager_fname, manager_mname, manager_lname, manager_phone_number, manager_email_id, manager_password, manager_address, manager_description, manager_photo)
VALUES ('Sanjay', 'Kumar', 'Gandhi', '+91-5432109877', 'sanjay.gandhi@email.com', 'managerPassword105', '234 Riverside Lane, Kolkata', 'Experienced manager with a focus on sales and business development.', 'sanjay_gandhi.jpg');

-- select * from manager;

SELECT * FROM category;

-- Insert sample categories related to managers (1 to 5)
-- Categories for Manager 1
INSERT INTO category (category_name, category_manager) VALUES ('provider_aadhar_verificationHome Services', 1);
INSERT INTO category (category_name, category_manager) VALUES ('Beauty and Personal Care', 1);

-- Categories for Manager 2
INSERT INTO category (category_name, category_manager) VALUES ('Beauty and Personal Care', 2);
INSERT INTO category (category_name, category_manager) VALUES ('Health and Wellness', 2);

-- Categories for Manager 3
INSERT INTO category (category_name, category_manager) VALUES ('Health and Wellness', 3);
INSERT INTO category (category_name, category_manager) VALUES ('Event Planning and Photography', 3);

-- Categories for Manager 4
INSERT INTO category (category_name, category_manager) VALUES ('Event Planning and Photography', 4);
INSERT INTO category (category_name, category_manager) VALUES ('Academic and Tutoring', 4);

-- Categories for Manager 5
INSERT INTO category (category_name, category_manager) VALUES ('Academic and Tutoring', 5);
INSERT INTO category (category_name, category_manager) VALUES ('Home Services', 5);
 -- select * from category;

 -- Inserting into services
 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Plumbing', 150, 'Description of Service 1 ', 1);
 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Carpentry', 340, 'Description of Service 2 ', 1);

 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Salon Services', 170, 'Description of Service 3 ', 2);
 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Spa and Massage', 140, 'Description of Service 4 ', 2);

 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Salon Services', 250, 'Description of Service 5 ', 3);
 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Spa and Massage', 240, 'Description of Service 6 ', 3);

 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Physiotherapy', 1500, 'Description of Service 7 ', 4);
 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Diet and Nutrition ', 3540, 'Description of Service 8 ', 4);

 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Physiotherapy', 1560, 'Description of Service 9 ', 5);
 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Diet and Nutrition ', 2540, 'Description of Service 10 ', 5);

 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('DJ Services', 13000, 'Description of Service 11 ', 6);
 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Videography', 30000, 'Description of Service 12 ', 6);

 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('DJ Services', 16000, 'Description of Service 13 ', 7);
 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Videography', 36000, 'Description of Service 14 ', 7);

 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Tutoring', 500, 'Description of Service 15 ', 8);
 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Test Prep', 940, 'Description of Service 16 ', 8);

 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Tutoring', 600, 'Description of Service 17 ', 9);
 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Test Prep', 640, 'Description of Service 18 ', 9);

 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Plumbing', 550, 'Description of Service 19 ', 10);
 INSERT INTO service (service_name, service_price, service_description, service_category) VALUES ('Carpentry', 740, 'Description of Service 20 ', 10);


 -- select * from service;



 -- Insert sample service providers related to managers and categories
INSERT INTO service_provider (provider_fname, provider_lname, provider_phone_number, provider_email_id, provider_password, provider_address, provider_description, provider_photo, provider_aadhar_number, provider_aadhar_verification, provider_account_number, provider_IFSC, provider_manager, provider_category)
VALUES ('Rajesh', 'Sharma', '+91 9876543210', 'rajesh@gmail.com', 'password123', '123, ABC Street, Delhi', 'Experienced plumber', 'rajesh.jpg', '1234567890', 'Verified', '0123456789', 'IFSC1234', 1, 1);

INSERT INTO service_provider (provider_fname, provider_lname, provider_phone_number, provider_email_id, provider_password, provider_address, provider_description, provider_photo, provider_aadhar_number, provider_aadhar_verification, provider_account_number, provider_IFSC, provider_manager, provider_category)
VALUES ('Preeti', 'Singh', '+91 9876543211', 'preeti@gmail.com', 'password456', '456, XYZ Street, Mumbai', 'Skilled carpenter', 'preeti.jpg', '2345678901', 'Verified', '1234567890', 'IFSC5678', 1, 1);

-- Service Providers for Manager 2, Category 2
INSERT INTO service_provider (provider_fname, provider_lname, provider_phone_number, provider_email_id, provider_password, provider_address, provider_description, provider_photo, provider_aadhar_number, provider_aadhar_verification, provider_account_number, provider_IFSC, provider_manager, provider_category)
VALUES ('Amit', 'Kumar', '+91 9876543212', 'amit@gmail.com', 'password789', '789, LMN Street, Bangalore', 'Professional beautician', 'amit.jpg', '3456789012', 'Verified', '2345678901', 'IFSC1234', 2, 3);

-- Service Providers for Manager 3, Category 3
INSERT INTO service_provider (provider_fname, provider_lname, provider_phone_number, provider_email_id, provider_password, provider_address, provider_description, provider_photo, provider_aadhar_number, provider_aadhar_verification, provider_account_number, provider_IFSC, provider_manager, provider_category)
VALUES ('Sneha', 'Verma', '+91 9876543213', 'sneha@gmail.com', 'passwordabc', '101, PQR Street, Chennai', 'Experienced fitness trainer', 'sneha.jpg', '4567890123', 'Verified', '3456789012', 'IFSC5678', 3, 5);

-- Service Providers for Manager 4, Category 4
INSERT INTO service_provider (provider_fname, provider_lname, provider_phone_number, provider_email_id, provider_password, provider_address, provider_description, provider_photo, provider_aadhar_number, provider_aadhar_verification, provider_account_number, provider_IFSC, provider_manager, provider_category)
VALUES ('Rahul', 'Gupta', '+91 9876543214', 'rahul@gmail.com', 'passwordxyz', '202, STU Street, Hyderabad', 'Professional event planner', 'rahul.jpg', '5678901234', 'Verified', '4567890123', 'IFSC1234', 4, 7);

-- Service Providers for Manager 5, Category 5
INSERT INTO service_provider (provider_fname, provider_lname, provider_phone_number, provider_email_id, provider_password, provider_address, provider_description, provider_photo, provider_aadhar_number, provider_aadhar_verification, provider_account_number, provider_IFSC, provider_manager, provider_category)
VALUES ('Neha', 'Yadav', '+91 9876543215', 'neha@gmail.com', 'passwordijk', '303, VWX Street, Kolkata', 'Skilled academic tutor', 'neha.jpg', '6789012345', 'Verified', '5678901234', 'IFSC5678', 5, 9);

INSERT INTO service_provider (provider_fname, provider_lname, provider_phone_number, provider_email_id, provider_password, provider_address, provider_description, provider_photo, provider_aadhar_number, provider_aadhar_verification, provider_account_number, provider_IFSC, provider_manager, provider_category)
VALUES ('Kunal', 'Mishra', '+91 9876543216', 'kunal@gmail.com', 'passwordlmn', '404, WXY Street, Pune', 'Experienced home improvement specialist', 'kunal.jpg', '7890123456', 'Verified', '6789012345', 'IFSC1234', 5, 10);

-- select * from service_provider;

-- Insert 5 to 6 slot entries
INSERT INTO slot (slot_time, slot_date, slot_service, slot_provider, slot_user)
VALUES
    ('10:00:00', '2023-10-15', 1, 1, 1),
    ('14:30:00', '2023-10-15', 1, 2, 2),
    ('16:00:00', '2023-10-15', 5, 3, 3),
    ('09:30:00', '2023-10-16', 9, 4, 4),
    ('12:45:00', '2023-10-16', 13, 5, 5);

-- select * from slot;
-- SELECT last_insert_id(slot_id) from slot;
-- use final;

-- Insert 5 order entries;
INSERT INTO orders (order_booked_time, order_booked_date,  order_status, order_slot)
VALUES
    ('10:30:00', '2023-10-10',  'Pending', 1),
    ('15:45:00', '2023-10-10',  'Confirmed', 2),
    ('09:00:00', '2023-10-10',  'Completed', 3),
    ('13:15:00', '2023-10-10',  'Pending', 4),
    ('11:45:00', '2023-10-10',  'Confirmed', 5);
-- select * from orders;



-- Service 1
INSERT INTO offer (discount, offer_service)
VALUES (10, 1);

-- Service 2
INSERT INTO offer (discount, offer_service)
VALUES (20, 2);

-- Service 3
INSERT INTO offer (discount, offer_service)
VALUES (15, 3);

-- Service 4
INSERT INTO offer (discount, offer_service)
VALUES (30, 4);

-- Service 5
INSERT INTO offer (discount, offer_service)
VALUES (25, 5);

-- Service 6
INSERT INTO offer (discount, offer_service)
VALUES (12, 6);

-- Service 7
INSERT INTO offer (discount, offer_service)
VALUES (18, 7);

-- Service 8
INSERT INTO offer (discount, offer_service)
VALUES (22, 8);

-- Service 9
INSERT INTO offer (discount, offer_service)
VALUES (14, 9);

-- Service 10
INSERT INTO offer (discount, offer_service)
VALUES (28, 10);

-- Service 11
INSERT INTO offer (discount, offer_service)
VALUES (17, 11);

-- Service 12
INSERT INTO offer (discount, offer_service)
VALUES (19, 12);

-- Service 13
INSERT INTO offer (discount, offer_service)
VALUES (13, 13);

-- Service 14
INSERT INTO offer (discount, offer_service)
VALUES (21, 14);

-- Service 15
INSERT INTO offer (discount, offer_service)
VALUES (16, 15);

-- Service 16
INSERT INTO offer (discount, offer_service)
VALUES (27, 16);

-- Service 17
INSERT INTO offer (discount, offer_service)
VALUES (23, 17);

-- Service 18
INSERT INTO offer (discount, offer_service)
VALUES (24, 18);

-- Service 19
INSERT INTO offer (discount, offer_service)
VALUES (29, 19);

-- Service 20
INSERT INTO offer (discount, offer_service)
VALUES (26, 20);


-- Cart for Order 1
INSERT INTO cart (cart_order)
VALUES (1);

-- Cart for Order 2
INSERT INTO cart (cart_order)
VALUES (2);

-- Cart for Order 3
INSERT INTO cart (cart_order)
VALUES (3);

-- Cart for Order 4
INSERT INTO cart (cart_order)
VALUES (4);

-- Cart for Order 5
INSERT INTO cart (cart_order)
VALUES (5);

-- Payment for Order 1
INSERT INTO payment (payment_date, payment_time, payment_amount, payment_method, payment_order)
VALUES ('2023-10-17', '11:00:00', 140, 'Credit Card', 1);

-- Payment for Order 2
INSERT INTO payment (payment_date, payment_time, payment_amount, payment_method, payment_order)
VALUES ('2023-10-18', '16:30:00', 320, 'PayPal', 2);

-- Payment for Order 3
INSERT INTO payment (payment_date, payment_time, payment_amount, payment_method, payment_order)
VALUES ('2023-10-19', '09:30:00', 510, 'Debit Card', 3);

-- Payment for Order 4
INSERT INTO payment (payment_date, payment_time, payment_amount, payment_method, payment_order)
VALUES ('2023-10-20', '13:45:00', 1220, 'Cash', 4);

-- Payment for Order 5
INSERT INTO payment (payment_date, payment_time, payment_amount, payment_method, payment_order)
VALUES ('2023-10-21', '12:30:00', 1180, 'Cash', 5);


-- select * from payment;