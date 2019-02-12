package com.payroll.myapp.mypayrollapp.service;

import com.payroll.myapp.mypayrollapp.dao.PayrollDao;
import com.payroll.myapp.mypayrollapp.model.AccountSummary;
import com.payroll.myapp.mypayrollapp.model.Invoice;
import com.payroll.myapp.mypayrollapp.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nmehta on 2/9/19.
 */
@Repository
public class PayrollService {

    @Autowired
    PayrollDao repository;

    public String getInvoiceStatus(long invoiceId) {
        String status = "NOT PAID";
        if(repository.getStatusForInvoice(invoiceId)) {
            status = "PAID";
        }

        System.out.print("Status for invoiceId" +invoiceId + " is :" +status);
        return status;
    }

    @Transactional
    public void updateInvoiceDetails(Invoice i){
        repository.updateInvoiceForUser(i);
        repository.updateInvoiceStatus(i.getInvoiceId(), getInvoiceStatus(i.getInvoiceId()));
    }

    @Transactional
    public void createInvoice(Invoice i){
        i.setInvoiceId(repository.getInvoiceIdFromSeq());
        repository.createInvoice(i);
        repository.updateInvoiceStatus(i.getInvoiceId(), getInvoiceStatus(i.getInvoiceId()));
    }

    public List<Invoice> getAllInvoices(long id) {
        List<Invoice> invoices = repository.getAllInvoicesForUser(id);
        return invoices;
    }

    public List<Transactions> getAllTransactions(long id) {
        List<Transactions> transactions = repository.getAllTransactionForUser(id);
        return transactions;
    }

    public List<Invoice> getInvoicesSummary(long id) {
        List<Invoice> invoices = repository.getOneMonthInvoicesForUser(id);
        return invoices;
    }

    public List<Transactions> getTransactionsSummary(long id) {
        List<Transactions> transactions = repository.getOneMonthTransactionForUser(id);
        return transactions;
    }

    public AccountSummary getAccountSummary(long userId) {
        AccountSummary accSummary = new AccountSummary();
        double totalAmount = repository.getTotalAmountForUser(userId);
        double threshold = repository.getThresholdForUser(userId);
        String color = "";
        if(totalAmount > threshold && totalAmount > 0) {
            color = "green";
        } else if(totalAmount <= threshold && threshold > 0) {
            color = "yellow";
        } else if(threshold < threshold || threshold <= 0) {
            color = "red";
        }
        accSummary.setUserId(userId);
        accSummary.setTotalAmount(totalAmount);
        accSummary.setThreshold(threshold);
        accSummary.setDisplayColor(color);
        return accSummary;
    }
}
