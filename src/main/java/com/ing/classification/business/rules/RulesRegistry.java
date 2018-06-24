package com.ing.classification.business.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RulesRegistry {

    @Autowired
    AfternoonPersonRule afternoonPersonRule;
    @Autowired
    MorningPersonRule morningPersonRule;
    @Autowired
    BigSpenderRule bigSpenderRule;
    @Autowired
    BigTicketSpenderRule bigTicketSpenderRule;
    @Autowired
    FastSpenderRule fastSpenderRule;
    @Autowired
    PotentialSaverRule potentialSaverRule;
    @Autowired
    PotentialLoanRule potentialLoanRule;

    public ClassificationRule[] getRules() {
        return new ClassificationRule[]{afternoonPersonRule, bigSpenderRule,
                bigTicketSpenderRule, fastSpenderRule, morningPersonRule,
                potentialSaverRule, potentialLoanRule};
    }
}
