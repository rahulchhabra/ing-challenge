package com.ing.classification.business.rules;

import com.ing.classification.business.AppConstants;
import com.ing.classification.result.AccountResult;
import com.ing.classification.result.Transaction;
import org.jeasy.rules.annotation.Rule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Rule(name = AppConstants.POTENTIAL_SAVER)
public class PotentialSaverRule extends ClassificationRule {

    @Override
    boolean isApplicable(AccountResult.AccountResultBuilder resultBuilder) {
        List<Transaction> transactions = resultBuilder.getTransactions();
        Double depositAmount = 0D;
        Double withdrawAmount = 0D;
        for(Transaction currTransaction: transactions) {
            if(currTransaction.getAmount() > 0) {
                depositAmount += currTransaction.getAmount();
            } else {
                withdrawAmount += (-currTransaction.getAmount());
            }
        }
        if(withdrawAmount < 0.25 * depositAmount) {
            return true;
        }
        return false;
    }

    @Override
    String getCategory() {
        return AppConstants.POTENTIAL_SAVER;
    }
}
