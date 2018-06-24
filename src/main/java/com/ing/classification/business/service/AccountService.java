package com.ing.classification.business.service;

import com.ing.classification.result.AccountResult;

public interface AccountService {

    public AccountResult classifyCustomer(Long customerId, String yearMonth);
}
