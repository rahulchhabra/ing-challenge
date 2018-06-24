package com.ing.classification.result;

import java.util.ArrayList;
import java.util.List;

public class AccountResult {

    private Long customerId;
    private String yearMonth;
    private List<String> categories;
    private List<Transaction> processedTransactions;
    private Double customerBalance;

    private AccountResult(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> clientCategories) {
        this.categories = clientCategories;
    }

    public List<Transaction> getProcessedTransactions() {
        return processedTransactions;
    }

    public void setProcessedTransactions(List<Transaction> processedTransactions) {
        this.processedTransactions = processedTransactions;
    }

    public Double getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(Double customerBalance) {
        this.customerBalance = customerBalance;
    }

    public static class AccountResultBuilder {
        private AccountResult result;

        AccountResultBuilder(AccountResult result) {
            this.result = result;
        }

        public AccountResultBuilder addCategory(String category) {
            result.categories.add(category);
            return this;
        }

        public List<String> getCategories() {
            return result.getCategories();
        }

        public AccountResultBuilder removeCategory(String category) {
            result.categories.remove(category);
            return this;
        }

        public AccountResultBuilder addTransaction(Transaction transaction) {
            result.processedTransactions.add(transaction);
            return this;
        }

        public AccountResultBuilder addTransactions(List<Transaction> transactions) {
            result.processedTransactions.addAll(transactions);
            return this;
        }

        public AccountResultBuilder setBalance(Double balance) {
            result.customerBalance = balance;
            return this;
        }

        public AccountResultBuilder setYearMonth(String yearMonth) {
            result.yearMonth = yearMonth;
            return this;
        }

        public List<Transaction> getTransactions() {
            return result.processedTransactions;
        }

        public AccountResult build() {
            AccountResult copyOfResult = result;
            //destroy reference to current result, so that builder reference can't be reused;
            result = null;
            return copyOfResult;
        }
    }

    public static AccountResultBuilder createBuilder(Long customerId) {
        AccountResult result = new AccountResult(customerId);
        result.categories = new ArrayList<String>();
        result.processedTransactions = new ArrayList<Transaction>();

        return new AccountResultBuilder(result);
    }
}
