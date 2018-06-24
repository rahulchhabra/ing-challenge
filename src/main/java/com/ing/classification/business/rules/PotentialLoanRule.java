package com.ing.classification.business.rules;

import com.ing.classification.business.AppConstants;
import com.ing.classification.result.AccountResult.AccountResultBuilder;
import com.ing.classification.result.handler.ResultHandler;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Rule(name = AppConstants.POTENTIAL_LOAN)
public class PotentialLoanRule extends ClassificationRule {

    @Override
    boolean isApplicable(AccountResultBuilder resultBuilder) {
        return resultBuilder.getCategories().contains(AppConstants.BIG_SPENDER) &&
                resultBuilder.getCategories().contains(AppConstants.FAST_SPENDER);
    }

    @Override
    String getCategory() {
        return AppConstants.POTENTIAL_LOAN;
    }

    @Override
    @Priority
    public int getPriority() {
        return 1000;
    }

    @Override
    @Autowired
    @Qualifier(AppConstants.POTENTIAL_LOAN)
    public void setResultHandler(ResultHandler resultHandler) {
        super.setResultHandler(resultHandler);
    }
}
