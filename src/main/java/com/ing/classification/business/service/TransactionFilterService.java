package com.ing.classification.business.service;

import com.ing.classification.result.Transaction;

import java.time.YearMonth;
import java.util.List;

public interface TransactionFilterService {

    public List<Transaction> filterOneMonthTransactions(Long customerId, YearMonth startMonth);

    public List<Transaction> filterTransactionsToDate(Long customerId, YearMonth startMonth);
}
