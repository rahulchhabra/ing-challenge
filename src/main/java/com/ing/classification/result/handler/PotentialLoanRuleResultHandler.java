package com.ing.classification.result.handler;

import com.ing.classification.business.AppConstants;
import com.ing.classification.result.AccountResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.ing.classification.business.AppConstants.POTENTIAL_LOAN;


@Component
@Qualifier(POTENTIAL_LOAN)
public class PotentialLoanRuleResultHandler implements ResultHandler {

    @Override
    public void handle(AccountResult.AccountResultBuilder resultBuilder, String category) {
        resultBuilder.removeCategory(AppConstants.BIG_SPENDER);
        resultBuilder.removeCategory(AppConstants.FAST_SPENDER);
        resultBuilder.addCategory(category);
    }
}
