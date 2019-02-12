--drop table PAYROLL_USER;
--drop table PAYROLL_ACCOUNT;
--drop table TRANSACTIONS;
--drop table INVOICE;


create table PAYROLL_USER (
  user_name VARCHAR2(100) not null,
  user_id int not null primary key,
  user_first_name VARCHAR2(100),
  user_last_name VARCHAR2(100),
  created timestamp
);

create sequence user_id_seq start with 1000;

create table PAYROLL_ACCOUNT (
  payroll_acc_id int not null primary key,
  user_id int not null,
  threshold NUMBER,
  created_time timestamp
);

create sequence payroll_acc_id_seq start with 1000;

create table TRANSACTIONS (
  transaction_id int not null primary key,
  user_id int not null,
  transaction_date timestamp,
  transaction_desc VARCHAR2(500),
  transaction_uniqueNo VARCHAR2(100) not null,
  transaction_amount NUMBER not null
);

create sequence transaction_id_seq start with 1000;

create table INVOICE (
  invoice_id int not null primary key,
  user_id int not null,
  invoice_client_name VARCHAR2(100),
  invoice_creation_date timestamp,
  invoice_uniqueNo VARCHAR2(100) not null,
  invoice_amount NUMBER,
  invoice_status VARCHAR2(100)
);

create sequence invoice_id_seq start with 1000;