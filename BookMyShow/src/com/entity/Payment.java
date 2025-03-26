package com.entity;

import java.util.Date;

import com.util.PaymentStatus;

public class Payment {
    private String id;
    private double amount;
    private PaymentStatus status;
    private Date timestamp;
    private String transactionId;

    public Payment(String id, double amount) {
        this.id = id;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
        this.timestamp = new Date();
    }

    public String getId() { return id; }
    public double getAmount() { return amount; }
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
}