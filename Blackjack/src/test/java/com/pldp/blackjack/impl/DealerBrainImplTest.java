package com.pldp.blackjack.impl;

import com.pldp.blackjack.Rules;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DealerBrainImplTest {

    Rules rules;
    DealerBrainImpl instance;

    @Before
    public void setUp() {
        rules = mock(Rules.class);
        instance = new DealerBrainImpl(rules);
    }

    @Test
    public void testShouldPass() {
        when(rules.dealerShouldPass(1)).thenReturn(true).thenReturn(false);
        assertEquals(true, instance.shouldPass(1));
        assertEquals(false, instance.shouldPass(1));
    }
}
