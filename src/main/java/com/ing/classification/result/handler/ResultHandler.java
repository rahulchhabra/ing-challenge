package com.ing.classification.result.handler;

import com.ing.classification.result.AccountResult;

public interface ResultHandler {

    public default void handle(AccountResult.AccountResultBuilder resultBuilder, String category) {
        resultBuilder.addCategory(category);
    };
}
