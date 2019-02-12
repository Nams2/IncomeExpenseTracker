insert into PAYROLL_USER(user_name, user_id, user_first_name, user_last_name, created)
values ('john_a', '1000', 'John', 'A', TO_TIMESTAMP('31-MAR-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'));
insert into PAYROLL_USER(user_name, user_id, user_first_name, user_last_name, created)
values ('rose_b', '2000', 'Rose', 'B', TO_TIMESTAMP('15-JAN-2017 00:00:00','DD-MON-YYYY HH24:MI:SS'));
insert into PAYROLL_USER(user_name, user_id, user_first_name, user_last_name, created)
values ('tim_c', '3000', 'Tim', 'C', TO_TIMESTAMP('11-JAN-2019 00:00:00','DD-MON-YYYY HH24:MI:SS'));



insert into PAYROLL_ACCOUNT(payroll_acc_id, user_id, threshold, created_time)
values (payroll_acc_id_seq.nextval, 1000, 100, TO_TIMESTAMP('31-MAR-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'));
insert into PAYROLL_ACCOUNT(payroll_acc_id, user_id, threshold, created_time)
values (payroll_acc_id_seq.nextval, 2000, 0, TO_TIMESTAMP('15-JAN-2017 00:00:00','DD-MON-YYYY HH24:MI:SS'));
insert into PAYROLL_ACCOUNT(payroll_acc_id, user_id, threshold, created_time)
values (payroll_acc_id_seq.nextval, 3000, 1000, TO_TIMESTAMP('11-JAN-2019 00:00:00','DD-MON-YYYY HH24:MI:SS'));



insert into TRANSACTIONS(transaction_id, user_id, transaction_date, transaction_desc, transaction_uniqueNo, transaction_amount)
values (transaction_id_seq.nextval, 1000, TO_TIMESTAMP('30-JUN-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'Test transaction 1', 'TESTID1', 100);
insert into TRANSACTIONS(transaction_id, user_id, transaction_date, transaction_desc, transaction_uniqueNo, transaction_amount)
values (transaction_id_seq.nextval, 1000, TO_TIMESTAMP('05-FEB-2019 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'Test transaction 2', 'TESTID2', -800);
insert into TRANSACTIONS(transaction_id, user_id, transaction_date, transaction_desc, transaction_uniqueNo, transaction_amount)
values (transaction_id_seq.nextval, 1000, TO_TIMESTAMP('20-SEP-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'Test transaction 3', 'TESTID3', -50);
insert into TRANSACTIONS(transaction_id, user_id, transaction_date, transaction_desc, transaction_uniqueNo, transaction_amount)
values (transaction_id_seq.nextval, 1000, TO_TIMESTAMP('15-DEC-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'Test transaction 4', 'TESTID4', 1000);

insert into TRANSACTIONS(transaction_id, user_id, transaction_date, transaction_desc, transaction_uniqueNo, transaction_amount)
values (transaction_id_seq.nextval, 2000, TO_TIMESTAMP('10-JUN-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'Test transaction 11', 'TESTID5', 100);
insert into TRANSACTIONS(transaction_id, user_id, transaction_date, transaction_desc, transaction_uniqueNo, transaction_amount)
values (transaction_id_seq.nextval, 2000, TO_TIMESTAMP('05-MAR-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'Test transaction 22', 'TESTID6', -1500);
insert into TRANSACTIONS(transaction_id, user_id, transaction_date, transaction_desc, transaction_uniqueNo, transaction_amount)
values (transaction_id_seq.nextval, 2000, TO_TIMESTAMP('20-JUL-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'Test transaction 33', 'TESTID7', 90);
insert into TRANSACTIONS(transaction_id, user_id, transaction_date, transaction_desc, transaction_uniqueNo, transaction_amount)
values (transaction_id_seq.nextval, 2000, TO_TIMESTAMP('15-NOV-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'Test transaction 44', 'TESTID8', 500);

insert into TRANSACTIONS(transaction_id, user_id, transaction_date, transaction_desc, transaction_uniqueNo, transaction_amount)
values (transaction_id_seq.nextval, 3000, TO_TIMESTAMP('10-JUN-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'Test transaction 111', 'TESTID9', 200);
insert into TRANSACTIONS(transaction_id, user_id, transaction_date, transaction_desc, transaction_uniqueNo, transaction_amount)
values (transaction_id_seq.nextval, 3000, TO_TIMESTAMP('05-MAR-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'Test transaction 222', 'TESTID10', -150);
insert into TRANSACTIONS(transaction_id, user_id, transaction_date, transaction_desc, transaction_uniqueNo, transaction_amount)
values (transaction_id_seq.nextval, 3000, TO_TIMESTAMP('20-JUL-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'Test transaction 333', 'TESTID11', 90);
insert into TRANSACTIONS(transaction_id, user_id, transaction_date, transaction_desc, transaction_uniqueNo, transaction_amount)
values (transaction_id_seq.nextval, 3000, TO_TIMESTAMP('15-NOV-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'Test transaction 444', 'TESTID12', 500);



insert into invoice(invoice_id, user_id, invoice_client_name, invoice_creation_date, invoice_uniqueNo, invoice_amount, invoice_status)
values (invoice_id_seq.nextval, 1000, 'CLIENT_1', TO_TIMESTAMP('14-DEC-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'TESTID4', 1000, 'PAID');
insert into invoice(invoice_id, user_id, invoice_client_name, invoice_creation_date, invoice_uniqueNo, invoice_amount, invoice_status)
values (invoice_id_seq.nextval,1000, 'CLIENT_2', TO_TIMESTAMP('15-FEB-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'TESTID99', -700, 'NOT PAID');
insert into invoice(invoice_id, user_id, invoice_client_name, invoice_creation_date, invoice_uniqueNo, invoice_amount, invoice_status)
values (invoice_id_seq.nextval,1000, 'CLIENT_3', TO_TIMESTAMP('20-SEP-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'TESTID3', -50, 'PAID');
insert into invoice(invoice_id, user_id, invoice_client_name, invoice_creation_date, invoice_uniqueNo, invoice_amount, invoice_status)
values (invoice_id_seq.nextval,1000, 'CLIENT_4', TO_TIMESTAMP('18-JAN-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'TESTID88', 100, 'NOT PAID');
insert into invoice(invoice_id, user_id, invoice_client_name, invoice_creation_date, invoice_uniqueNo, invoice_amount, invoice_status)
values (invoice_id_seq.nextval,1000, 'CLIENT_5', TO_TIMESTAMP('14-DEC-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'TESTID77', 500, 'PAID');
insert into invoice(invoice_id, user_id, invoice_client_name, invoice_creation_date, invoice_uniqueNo, invoice_amount, invoice_status)
values (invoice_id_seq.nextval,1000, 'CLIENT_6', TO_TIMESTAMP('10-FEB-2019 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'TESTID99', -10, 'NOT PAID');
insert into invoice(invoice_id, user_id, invoice_client_name, invoice_creation_date, invoice_uniqueNo, invoice_amount, invoice_status)
values (invoice_id_seq.nextval,1000, 'CLIENT_7', TO_TIMESTAMP('20-SEP-2018 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'TESTID66', 250, 'PAID');
insert into invoice(invoice_id, user_id, invoice_client_name, invoice_creation_date, invoice_uniqueNo, invoice_amount, invoice_status)
values (invoice_id_seq.nextval, 1000, 'CLIENT_8', TO_TIMESTAMP('18-JAN-2019 00:00:00','DD-MON-YYYY HH24:MI:SS'), 'TESTID88', -350, 'NOT PAID');


