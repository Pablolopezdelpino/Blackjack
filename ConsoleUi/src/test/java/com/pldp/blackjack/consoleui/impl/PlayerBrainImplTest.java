package com.pldp.blackjack.consoleui.impl;

import com.pldp.blackjack.consoleui.impl.PlayerBrainImpl;
import com.pldp.util.console.In;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PlayerBrainImplTest {
    PlayerBrainImpl instance;
    In in;
    @Before
    public void setUp() {
        in = mock(In.class);
        instance = new PlayerBrainImpl(in);
    }

    @Test
    public void testShouldPass() {
        when(in.questionYN(any())).thenReturn(true, false);
        assertTrue(instance.shouldPass(0));
        assertFalse(instance.shouldPass(0));
    }
    
}
