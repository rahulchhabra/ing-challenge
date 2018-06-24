package com.ing.classification.business.rules;

import com.ing.classification.result.AccountResult.AccountResultBuilder;
import com.ing.classification.result.Transaction;
import org.jeasy.rules.annotation.Rule;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import static com.ing.classification.business.AppConstants.AFTERNOON_PERSON;

@Component
@Rule(name = AFTERNOON_PERSON)
public class AfternoonPersonRule extends ClassificationRule {

    @Override
    boolean isApplicable(AccountResultBuilder resultBuilder) {
        List<Transaction> transactions = resultBuilder.getTransactions();
        int afternoonCount = 0;
        LocalTime midDay = LocalTime.of(11, 0);

        double halfOfTransactions = (double) transactions.size()/2;
        for(Transaction currTransaction: transactions) {
            LocalTime transactionTime = LocalDateTime.ofInstant(currTransaction.getTransactionDate().toInstant(),
                    ZoneId.systemDefault()).toLocalTime();

            if(transactionTime.isAfter(midDay)) {
                afternoonCount++;
            }
            if(afternoonCount > halfOfTransactions) {
                return true;
            }
        }

        return false;
    }

    @Override
    String getCategory() {
        return AFTERNOON_PERSON;
    }
}
