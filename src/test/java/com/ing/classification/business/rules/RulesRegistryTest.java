package com.ing.classification.business.rules;

import com.ing.classification.SpringBootWithReactJsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringBootWithReactJsApplication.class)

public class RulesRegistryTest {

    @Autowired
    RulesRegistry rulesRegistry;

    @Test
    public void getRules() {
        ClassificationRule[] rules = rulesRegistry.getRules();
        assertEquals(7, rules.length);
    }
}