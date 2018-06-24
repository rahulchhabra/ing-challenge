package com.ing.classification.service;

import com.ing.classification.SpringBootWithReactJsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.YearMonth;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringBootWithReactJsApplication.class)
public class TransactionFilterServiceImplTest {

    @Autowired
    TransactionFilterService transactionFilterService;

    @Test
    public void filterOneMonthTransactions() {
        assertEquals(29, transactionFilterService
                .filterOneMonthTransactions(3L, YearMonth.parse("2016-05") ).size());
    }
}