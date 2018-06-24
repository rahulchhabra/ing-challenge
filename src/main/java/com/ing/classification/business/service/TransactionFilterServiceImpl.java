package com.ing.classification.business.service;

import com.ing.classification.business.reader.TransactionReader;
import com.ing.classification.result.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionFilterServiceImpl implements TransactionFilterService {

    @Autowired
    TransactionReader transactionReader;

    @Override
    public List<Transaction> filterOneMonthTransactions(Long customerId, YearMonth startMonth) {
        LocalDateTime startDate = startMonth.atDay(1).atTime(LocalTime.MIN);
        LocalDateTime endDate = startMonth.atEndOfMonth().atTime(LocalTime.MAX);

        return getTransactionsBetweenDateTime(customerId, startDate, endDate);
    }

    private List<Transaction> getTransactionsBetweenDateTime(Long customerId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Transaction> completeList = transactionReader.readTransactions();
        List<Transaction> filteredTransactions = completeList.stream().filter(currTransaction -> {
            if(currTransaction.getCustomerId().equals(customerId) ) {
                LocalDateTime transactionDateTime = LocalDateTime.ofInstant(currTransaction.getTransactionDate().toInstant(),
                        ZoneId.systemDefault());

                if(startDate != null && transactionDateTime.isBefore(startDate)) {
                    return false;
                }
                if(endDate != null && transactionDateTime.isAfter(endDate)) {
                    return false;
                }
                return true;
            }
            return false;
        })
        .sorted(Comparator.comparing(Transaction::getTransactionDate))
        .collect(Collectors.toList());

        return filteredTransactions;
    }

    @Override
    public List<Transaction> filterTransactionsToDate(Long customerId, YearMonth startMonth) {
        return getTransactionsBetweenDateTime(customerId, null, LocalDateTime.now());
    }
}
