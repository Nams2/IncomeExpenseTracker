package com.payroll.myapp.mypayrollapp;

import com.payroll.myapp.mypayrollapp.controller.PayrollController;
import com.payroll.myapp.mypayrollapp.dao.PayrollDao;
import com.payroll.myapp.mypayrollapp.model.Invoice;
import com.payroll.myapp.mypayrollapp.service.PayrollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class MypayrollappApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(MypayrollappApplication.class);
	//@Autowired
	//PayrollService service;

	public static void main(String[] args) {
		SpringApplication.run(MypayrollappApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		//logger.info("Invoice for User id 1000 -> {}", repository.getAllInvoicesForUser(1000L));

		//logger.info("Transaction for User id 1000 -> {}", repository.getAllTransactionForUser(1000L));

		//Invoice i=new Invoice(1000, "FromApp", "Test001001", 50, "01/01/2019");
		//i.setInvoiceCreationDate(new Timestamp(dateFormat.parse("01/01/2019").getTime()));
		//logger.info("Create in voice for User id 1000 -> {}", repository.createInvoice(i));
/*
		i.setInvoiceId(1000);
		i.setUserId(1000);
		i.setInvoiceClientName("FromApp Update");
		i.setInvoiceAmount(50);
		i.setInvoiceUniqueNo("Test001001");
		i.setInvoiceStatus("NOT PAID");
		i.setInvoiceCreationDate(new Timestamp(dateFormat.parse("02/01/2019").getTime()));
		//logger.info("updateInvoiceForUser User id 1000 -> {}", repository.updateInvoiceForUser(i));

		//logger.info("getStatusForInvoice User id 1000 -> {}", repository.getStatusForInvoice(1000L));

		//logger.info("getOneMonthInvoicesForUser for User id 1000 -> {}", repository.getOneMonthInvoicesForUser(1000L));

		//logger.info("getOneMonthTransactionForUser for User id 1000 -> {}", repository.getOneMonthTransactionForUser(1000L));

*/
	}
}

