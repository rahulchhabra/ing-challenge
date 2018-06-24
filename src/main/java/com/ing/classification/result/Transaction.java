package com.ing.classification.result;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;

public class Transaction {

    @CsvBindByName(column = "CustomerId")
    private Long customerId;

    @CsvBindByName(column = "Date")
    @CsvDate("dd/MM/yyyy hh:mm:ss a")
    private Date transactionDate;

    @CsvBindByName(column = "Amount")
    private Double amount;

    @CsvBindByName(column = "Description")
    private String description;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "customerId='" + customerId + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", amount='" + amount + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
