package com.ing.classification.business.rules;

import com.ing.classification.result.AccountResult.AccountResultBuilder;
import org.jeasy.rules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.ing.classification.business.AppConstants.MORNING_PERSON;

@Component
@Rule(name = MORNING_PERSON)
public class MorningPersonRule extends ClassificationRule {

    @Autowired
    AfternoonPersonRule afternoonPersonRule;

    @Override
    boolean isApplicable(AccountResultBuilder resultBuilder) {
        return !afternoonPersonRule.isApplicable(resultBuilder);
    }

    @Override
    String getCategory() {
        return MORNING_PERSON;
    }
}
