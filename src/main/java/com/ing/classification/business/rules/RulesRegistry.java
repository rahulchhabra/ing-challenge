package com.ing.classification.business.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RulesRegistry {

    @Autowired
    AfternoonPersonRule afternoonPersonRule;

    public ClassificationRule[] getRules() {
        return new ClassificationRule[]{afternoonPersonRule};
    }
}
