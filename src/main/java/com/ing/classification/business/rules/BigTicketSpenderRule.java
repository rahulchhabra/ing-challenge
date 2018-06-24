package com.ing.classification.business.rules;

import com.ing.classification.business.AppConstants;
import com.ing.classification.result.AccountResult;
import com.ing.classification.result.Transaction;
import org.jeasy.rules.annotation.Rule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Rule(name = AppConstants.BIG_TICKET_SPENDER)
public class BigTicketSpenderRule extends ClassificationRule {

    @Override
    boolean isApplicable(AccountResult.AccountResultBuilder resultBuilder) {
        List<Transaction> transactions = resultBuilder.getTransactions();
        for(Transaction currTransaction: transactions) {
            if(currTransaction.getAmount() < -1000) {
                return true;
            }
        }
        return false;
    }

    @Override
    String getCategory() {
        return AppConstants.BIG_TICKET_SPENDER;
    }
}
