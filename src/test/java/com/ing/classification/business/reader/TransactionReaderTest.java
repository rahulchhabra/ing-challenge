package com.ing.classification.business.reader;

import com.ing.classification.SpringBootWithReactJsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringBootWithReactJsApplication.class)
public class TransactionReaderTest {

    @Autowired
    private TransactionReader transactionReader;

    @Test
    public void readTransactions() {
        assertEquals(3000, transactionReader.readTransactions().size());
    }

    @Test
    public void readTransactions_with_path() {
        assertEquals(2997, transactionReader.readTransactions("data_1.txt").size());
    }
}