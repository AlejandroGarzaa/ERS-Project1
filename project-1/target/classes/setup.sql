
----------------------------------------
DROP TABLE IF EXISTS ers_reimbursements_status;
CREATE TABLE ers_reimbursements_status(
	status_id int PRIMARY KEY,
	status_name varchar(10)
);

insert into ers_reimbursements_status (status_id, status_name) values (1, 'approved');
insert into ers_reimbursements_status (status_id, status_name) values (2, 'denied');
insert into ers_reimbursements_status (status_id, status_name) values (3, 'pending');
------------------------------------------
DROP TABLE IF EXISTS ers_reimbursements_types;
CREATE TABLE ers_reimbursements_types(
	type_id int PRIMARY KEY,
	type_name varchar(10)
);

insert into ers_reimbursements_types (type_id, type_name) values (1, 'lodging');
insert into ers_reimbursements_types (type_id, type_name) values (2, 'travel');
insert into ers_reimbursements_types (type_id, type_name) values (3, 'food');
insert into ers_reimbursements_types (type_id, type_name) values (4, 'other');
---------------------------------
DROP TABLE IF EXISTS ers_users;
CREATE TABLE ers_users(
	user_id serial PRIMARY KEY,
	username varchar(50) UNIQUE NOT NULL,
	user_password varchar(50) NOT NULL,
	user_first_name varchar(50) NOT NULL,
	user_last_name varchar(50) NOT NULL,
	user_email varchar(100) UNIQUE NOT null,
	user_role_id int NOT NULL,
	foreign key (user_role_id) references ers_user_roles(role_id)
);

insert into ers_users (username, user_password,user_first_name,user_last_name,user_email,user_role_id ) values ('alej','alejpassword','alejandro','garza','alej@gmail.com',1);
insert into ers_users (username, user_password,user_first_name,user_last_name,user_email,user_role_id ) values ('man1','password','manager','scott','scott@gmail.com',2);
----------------------------------------

DROP TABLE IF EXISTS ers_user_roles;
CREATE TABLE ers_user_roles(
	role_id int PRIMARY KEY,
	role_name varchar(50) NOT NULL 
);

insert into ers_user_roles (role_id, role_name) values (1, 'employee');
insert into ers_user_roles (role_id, role_name) values (2, 'manager');
------------------------------------------
DROP TABLE IF EXISTS ers_reimbursements;
CREATE TABLE ers_reimbursements(
	reimb_id serial PRIMARY KEY,
	reimb_amount decimal(10, 2),
	reimb_submitted timestamp,
	reimb_resolved timestamp,
	reimb_description varchar(250),
	reimb_author_id int,
	reimb_resolver_id int,
	reimb_status_id int,
	reimb_type_id int,
	foreign key (reimb_author_id) references ers_users(user_id),
	foreign key (reimb_resolver_id) references ers_users(user_id),
	foreign key (reimb_status_id) references ers_reimbursements_status(status_id),
	foreign key (reimb_type_id) references ers_reimbursements_types(type_id)
);

INSERT INTO ers_reimbursements(reimb_amount, reimb_description, reimb_author_id, reimb_resolver_id, reimb_status_id, reimb_type_id) 
VALUES (100.50,'for company trip', 1, 2, 3, 2);

------------------------------------------
CREATE OR REPLACE FUNCTION set_current_time_f()
RETURNS TRIGGER AS $$
BEGIN
   NEW.reimb_submitted = now();
   RETURN NEW;
END;
$$ language plpgsql;

CREATE TRIGGER set_current_time
BEFORE INSERT
ON ers_reimbursements
FOR EACH ROW
	EXECUTE PROCEDURE set_current_time_f();

