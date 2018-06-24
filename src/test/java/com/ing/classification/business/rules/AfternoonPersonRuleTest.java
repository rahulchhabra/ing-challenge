package com.ing.classification.business.rules;

import com.ing.classification.SpringBootWithReactJsApplication;
import com.ing.classification.business.reader.TransactionReader;
import com.ing.classification.result.AccountResult;
import com.ing.classification.result.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringBootWithReactJsApplication.class)
public class AfternoonPersonRuleTest {

    @Autowired
    AfternoonPersonRule afternoonPersonRule;

    @Autowired
    TransactionReader transactionReader;

    @Test
    public void isApplicable_true() {
        final long custId = 3L;
        AccountResult.AccountResultBuilder resultBuilder = AccountResult.createBuilder(custId);
        List<Transaction> tList = transactionReader.readTransactions("data_afternoon_person_true.txt");
        resultBuilder.addTransactions(tList);
        assertEquals(true, afternoonPersonRule.isApplicable(resultBuilder));
    }

    @Test
    public void isApplicable_false() {
        final long custId = 3L;
        AccountResult.AccountResultBuilder resultBuilder = AccountResult.createBuilder(custId);
        List<Transaction> tList = transactionReader.readTransactions("data_afternoon_person_false.txt");
        resultBuilder.addTransactions(tList);
        assertEquals(false, afternoonPersonRule.isApplicable(resultBuilder));
    }

    public static Transaction createTransaction(Long custId, Date tDate, double amount, String description) {
        Transaction trans = new Transaction();
        trans.setCustomerId(custId);
        trans.setTransactionDate(tDate);
        trans.setAmount(amount);
        trans.setDescription(description);
        return trans;
    }
}