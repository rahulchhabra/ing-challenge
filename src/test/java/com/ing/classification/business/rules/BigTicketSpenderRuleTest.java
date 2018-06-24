package com.ing.classification.business.rules;

import com.ing.classification.SpringBootWithReactJsApplication;
import com.ing.classification.business.AppConstants;
import com.ing.classification.business.reader.TransactionReader;
import com.ing.classification.result.AccountResult;
import com.ing.classification.result.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringBootWithReactJsApplication.class)
public class BigTicketSpenderRuleTest {

    @Autowired
    BigTicketSpenderRule bigTicketSpender;

    @Autowired
    TransactionReader transactionReader;

    @Test
    public void isApplicable_true() {
        final long custId = 3L;
        AccountResult.AccountResultBuilder resultBuilder = AccountResult.createBuilder(custId);
        List<Transaction> tList = transactionReader.readTransactions("test_data_big_ticket_true.txt");
        resultBuilder.addTransactions(tList);
        assertEquals(true, bigTicketSpender.isApplicable(resultBuilder));
    }

    @Test
    public void isApplicable_false() {
        final long custId = 3L;
        AccountResult.AccountResultBuilder resultBuilder = AccountResult.createBuilder(custId);
        List<Transaction> tList = transactionReader.readTransactions("test_data_big_ticket_false.txt");
        resultBuilder.addTransactions(tList);
        assertEquals(false, bigTicketSpender.isApplicable(resultBuilder));
    }

    @Test
    public void getCategory() {
        assertEquals(AppConstants.BIG_TICKET_SPENDER, bigTicketSpender.getCategory());
    }
}