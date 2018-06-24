package com.ing.classification.controller;

import com.ing.classification.business.service.AccountService;
import com.ing.classification.result.AccountResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    AccountService acService;

    @RequestMapping(value = "/customerDetail/{customerId}/month/{yearMonth}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<AccountResult> customerDetail(@PathVariable("customerId") Long customerId,
                                                        @PathVariable(value = "yearMonth") String yearMonth
    ) {
        System.out.println(String.format("Account id received %s, date %s", customerId, yearMonth));

        AccountResult result = acService.classifyCustomer(customerId, yearMonth);

        return ResponseEntity.ok(result);
    }
}
