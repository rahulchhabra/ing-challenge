package com.ing.classification.business.rules;

import com.ing.classification.result.AccountResult;
import com.ing.classification.result.AccountResult.AccountResultBuilder;
import com.ing.classification.result.handler.ResultHandler;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

public abstract class ClassificationRule {

    private ResultHandler resultHandler;

    @Autowired
    @Required
    @Qualifier("DEFAULT")
    public void setResultHandler(ResultHandler resultHandler) {
        this.resultHandler = resultHandler;
    }

    @Condition
    public boolean when(@Fact("resultBuilder") AccountResultBuilder resultBuilder) {
        if(resultBuilder.getTransactions().size() == 0) {
            return false;
        }
        return isApplicable(resultBuilder);
    }

    abstract boolean isApplicable(AccountResultBuilder resultBuilder);

    abstract String getCategory();

    @Action
    public void then(@Fact("resultBuilder") AccountResultBuilder resultBuilder) {
        resultHandler.handle(resultBuilder, getCategory());
    }

    @Priority
    public int getPriority() {
        return 100;
    }
}
