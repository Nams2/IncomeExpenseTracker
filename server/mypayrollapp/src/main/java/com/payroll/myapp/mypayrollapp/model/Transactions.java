package com.payroll.myapp.mypayrollapp.model;


import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by nmehta on 2/8/19.
 */

public class Transactions implements Serializable {

    private long transactionId;
    private Timestamp transactionDate;
    private String transactionDesc;
    private String transactionUniqueNo;
    private double transactionAmount;
    private long userId;


    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public String getTransactionUniqueNo() {
        return transactionUniqueNo;
    }

    public void setTransactionUniqueNo(String transactionUniqueNo) {
        this.transactionUniqueNo = transactionUniqueNo;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
