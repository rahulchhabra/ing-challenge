package com.ing.classification.result.handler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("DEFAULT")
public class DefaultResultHandler implements ResultHandler {
}
