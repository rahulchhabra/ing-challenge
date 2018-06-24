package com.ing.classification.business.rules;

import com.ing.classification.SpringBootWithReactJsApplication;
import com.ing.classification.business.AppConstants;
import com.ing.classification.business.reader.TransactionReader;
import com.ing.classification.result.AccountResult;
import com.ing.classification.result.Transaction;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringBootWithReactJsApplication.class)
public class PotentialLoanRuleTest {

    @Autowired
    BigSpenderRule bigSpenderRule;
    @Autowired
    FastSpenderRule fastSpenderRule;
    @Autowired
    PotentialLoanRule potentialLoanRule;

    @Autowired
    TransactionReader transactionReader;

    @Test
    public void isApplicable_fast_true_big_true_result_true() {
        final long custId = 3L;
        AccountResult.AccountResultBuilder resultBuilder = AccountResult.createBuilder(custId);

        List<Transaction> tList = transactionReader.readTransactions("test_data_potential_loan_true.txt");
        resultBuilder.addTransactions(tList);

        Facts facts = new Facts();
        facts.add("resultBuilder", resultBuilder);

        // create a rules engine and fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();

        rulesEngine.fire(new Rules(potentialLoanRule, bigSpenderRule, fastSpenderRule), facts);

        assertTrue(resultBuilder.getCategories().contains(AppConstants.POTENTIAL_LOAN));
    }

    @Test
    public void isApplicable_fast_false_big_true_result_false() {
        final long custId = 3L;
        AccountResult.AccountResultBuilder resultBuilder = AccountResult.createBuilder(custId);
        resultBuilder.addCategory(AppConstants.FAST_SPENDER);
        assertEquals(false, potentialLoanRule.isApplicable(resultBuilder));
    }

    @Test
    public void isApplicable_fast_true_big_false_result_false() {
        final long custId = 3L;
        AccountResult.AccountResultBuilder resultBuilder = AccountResult.createBuilder(custId);
        resultBuilder.addCategory(AppConstants.BIG_SPENDER);
        assertEquals(false, potentialLoanRule.isApplicable(resultBuilder));
    }

    @Test
    public void isApplicable_fast_falsebig_false_result_false() {
        final long custId = 3L;
        AccountResult.AccountResultBuilder resultBuilder = AccountResult.createBuilder(custId);
        resultBuilder.addCategory(AppConstants.POTENTIAL_SAVER);
        assertEquals(false, potentialLoanRule.isApplicable(resultBuilder));
    }

    @Test
    public void getCategory() {
        assertEquals(AppConstants.POTENTIAL_LOAN, potentialLoanRule.getCategory());
    }

    @Test
    public void getPriority() {
        assertEquals(1000, potentialLoanRule.getPriority());
    }
}