package com.ing.classification.business.rules;

import com.ing.classification.business.AppConstants;
import com.ing.classification.result.AccountResult;
import com.ing.classification.result.AccountResult.AccountResultBuilder;
import com.ing.classification.result.Transaction;
import org.jeasy.rules.annotation.Rule;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;

@Component
@Rule(name = AppConstants.FAST_SPENDER)
public class FastSpenderRule extends ClassificationRule {

    @Override
    boolean isApplicable(AccountResultBuilder resultBuilder) {
        List<Transaction> transactions = resultBuilder.getTransactions();
        Comparator<Transaction> transactionDateComparator = new Comparator<Transaction>() {

            @Override
            public int compare(Transaction o1, Transaction o2) {
                return o1.getTransactionDate().compareTo(o2.getTransactionDate());
            }
        };
        boolean isFastSpender = transactions.stream()
            .filter(transaction -> {
                return transaction.getAmount() > 0;
            }).allMatch(currDeposit -> {
                LocalDateTime depositTime = LocalDateTime.ofInstant(currDeposit.getTransactionDate().toInstant(), ZoneId.systemDefault());
                LocalDateTime endTime = depositTime.plusDays(7);
                Double withdrawlAmountInOneWeek = transactions.stream()
                    .filter(ot -> {
                        LocalDateTime otTransactionDate = LocalDateTime.ofInstant(
                                ot.getTransactionDate().toInstant(), ZoneId.systemDefault());
                        return otTransactionDate.isAfter(depositTime) && otTransactionDate.isBefore(endTime);
                    })
                    .map(Transaction::getAmount)
                    .reduce(0D, (a, b) -> a + b);
                if(-withdrawlAmountInOneWeek > .75*currDeposit.getAmount() ) {
                    return true;
                }
                return false;
            });

        return isFastSpender;
    }

    @Override
    String getCategory() {
        return AppConstants.FAST_SPENDER;
    }
}
