package com.pldp.blackjack;

import com.pldp.blackjack.api.Dealer;
import com.pldp.blackjack.api.Player;
import com.pldp.cards.Deck;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class GameTest {
        Game game;
        Rules rules;
        Deck deck;
        Dealer dealer;
        Player player;
    
    @Before
    public void setUp() {
        game = new Game();
        deck = mock(Deck.class);
        rules = mock(Rules.class);
        dealer = mock(Dealer.class);
        player = mock(Player.class);
    }

    @Test
    public void testRun() {
        when(player.isBlackjack()).thenReturn(true);
        when(dealer.isBlackjack()).thenReturn(true);
        when(player.getScore()).thenReturn(21);
        when(dealer.getScore()).thenReturn(21);
        when(rules.evaluate(21, 21)).thenReturn(Rules.Result.push);
        assertEquals(Rules.Result.push, game.run(rules, deck, dealer, player));
    }
    
    @Test
    public void testPlayerBust() {
        when(player.isBust()).thenReturn(true);
        assertEquals(Rules.Result.lose, game.run(rules, deck, dealer, player));
    }
    
    @Test
    public void testDealerBust() {
        when(dealer.isBust()).thenReturn(true);
        when(player.stand()).thenReturn(true);
        when(player.isBlackjack()).thenReturn(false);
        assertEquals(Rules.Result.win, game.run(rules, deck, dealer, player));
    }
    
}
