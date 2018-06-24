package com.ing.classification.business.service;

import com.ing.classification.business.rules.RulesRegistry;
import com.ing.classification.result.AccountResult;
import com.ing.classification.result.Transaction;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.YearMonth;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    RulesRegistry rulesRegistry;

    @Autowired
    TransactionFilterService transactionFilterService;

    private DecimalFormat df = new DecimalFormat("#.##");

    @Override
    public AccountResult classifyCustomer(Long customerId, String yearMonthStr) {
        AccountResult.AccountResultBuilder resultBuilder = AccountResult.createBuilder(customerId);
        YearMonth yeamMonth = YearMonth.parse(yearMonthStr);
        resultBuilder.setYearMonth(yearMonthStr);

        List<Transaction> filteredTransactions = transactionFilterService.filterOneMonthTransactions(customerId, yeamMonth);
        resultBuilder.addTransactions(filteredTransactions);
        fireRules(resultBuilder);
        return resultBuilder.build();
    }

    @Override
    public AccountResult getBalance(Long customerId, String yearMonthStr) {
        AccountResult.AccountResultBuilder resultBuilder = AccountResult.createBuilder(customerId);
        resultBuilder.setYearMonth(yearMonthStr);
        YearMonth yearMonth = YearMonth.parse(yearMonthStr);

        List<Transaction> filteredTransactions = transactionFilterService.filterTransactionsToDate(customerId, yearMonth);
        Double balance = filteredTransactions.stream().map(Transaction::getAmount).reduce(0D, (a, b) -> a + b);

        return resultBuilder.setBalance(Double.valueOf(df.format(balance))).build();
    }

    private void fireRules(AccountResult.AccountResultBuilder resultBuilder) {
        Facts facts = new Facts();
        facts.add("resultBuilder", resultBuilder);

        // create a rules engine and fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(new Rules(rulesRegistry.getRules()), facts);
    }

}
