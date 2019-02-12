package com.payroll.myapp.mypayrollapp.model;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by nmehta on 2/8/19.
 */

public class Invoice implements Serializable {

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public long invoiceId;
    private long userId;
    private String invoiceClientName;
    private Timestamp invoiceCreationDate;
    private String invoiceUniqueNo;
    private double invoiceAmount;
    private String invoiceStatus;

    public Invoice() {

    }

    public Invoice(long userId, String invoiceClientName, String invoiceUniqueNo,
                   double invoiceAmount, String invoiceCreationDate) throws ParseException {
        this.userId = userId;
        this.invoiceClientName = invoiceClientName;
        this.invoiceUniqueNo = invoiceUniqueNo;
        this.invoiceAmount = invoiceAmount;
        this.invoiceCreationDate = new Timestamp(dateFormat.parse(invoiceCreationDate).getTime());
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getInvoiceCreationDate() {
        return invoiceCreationDate;
    }

    public void setInvoiceCreationDate(Timestamp invoiceCreationDate) {
        this.invoiceCreationDate = invoiceCreationDate;
    }

    public void setInvoiceCreationDate(String invoiceCreationDate) throws ParseException {
        this.invoiceCreationDate = new Timestamp(dateFormat.parse(invoiceCreationDate).getTime());
    }

    public String getInvoiceClientName() {
        return invoiceClientName;
    }

    public void setInvoiceClientName(String invoiceClientName) {
        this.invoiceClientName = invoiceClientName;
    }

    public String getInvoiceUniqueNo() {
        return invoiceUniqueNo;
    }

    public void setInvoiceUniqueNo(String invoiceUniqueNo) {
        this.invoiceUniqueNo = invoiceUniqueNo;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
}
