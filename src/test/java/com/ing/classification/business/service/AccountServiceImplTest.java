package com.ing.classification.business.service;

import com.ing.classification.SpringBootWithReactJsApplication;
import com.ing.classification.business.AppConstants;
import com.ing.classification.result.AccountResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringBootWithReactJsApplication.class)
public class AccountServiceImplTest {

    @Autowired
    AccountService accountService;

    @Test
    public void testClassifyCustomer() {
        AccountResult result = accountService.classifyCustomer(3L, "2016-05");
        Assert.assertEquals(2, result.getCategories().size());
        Assert.assertTrue(result.getCategories().contains(AppConstants.POTENTIAL_SAVER));
        Assert.assertTrue(result.getCategories().contains(AppConstants.AFTERNOON_PERSON));
    }

    @Test
    public void testGetBalance() {
        AccountResult result = accountService.getBalance(7632L, "2016-05");
        Assert.assertEquals(Long.valueOf(7632L), result.getCustomerId());
        Assert.assertEquals(Double.valueOf(754.5), result.getCustomerBalance());
        Assert.assertEquals("2016-05", result.getYearMonth());
    }
}