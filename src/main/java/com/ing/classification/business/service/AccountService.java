package com.ing.classification.business.service;

import com.ing.classification.result.AccountResult;

public interface AccountService {

    AccountResult classifyCustomer(Long customerId, String yearMonth);

    AccountResult getBalance(Long customerId, String yearMonth);
}
