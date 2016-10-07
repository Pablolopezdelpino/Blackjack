package com.pldp.blackjack;

import com.pldp.blackjack.api.Dealer;
import com.pldp.blackjack.api.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class GameTest {
        Game game;
        Rules rules;
        Dealer dealer;
        Player player;
    
    @Before
    public void setUp() {
        game = new Game();
        rules = mock(Rules.class);
        dealer = mock(Dealer.class);
        player = mock(Player.class);
    }
    
    @After
    public void tearDown() {
        rules = null;
        dealer = null;
        player = null;
    }

    @Test
    public void testRun() {
        when(rules.evaluate(any(), any())).thenReturn(Rules.Result.push);
        Rules.Result expResult = game.run(rules, dealer, player);
        assertEquals(Rules.Result.push, expResult);
    }
    
    @Test
    public void testPlayerBust() {
        when(rules.isBust(any())).thenReturn(true);
        Rules.Result expResult = game.run(rules, dealer, player);
        assertEquals(Rules.Result.lose, expResult);
    }
    
    @Test
    public void testDealerBust() {
        when(rules.isBust(any())).thenReturn(false).thenReturn(true);
        Rules.Result expResult = game.run(rules, dealer, player);
        assertEquals(Rules.Result.win, expResult);
    }
    
}
