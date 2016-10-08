package com.pldp.blackjack.impl;

import com.pldp.blackjack.Rules;
import com.pldp.blackjack.api.Dealer;
import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.api.PlayerBrain;
import com.pldp.cards.Card;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PlayerImplTest {
    Rules rules;
    Hand hand;
    PlayerBrain brain;
    PlayerImpl instance;
    
    @Before
    public void setUp() {
        rules = mock(Rules.class);
        hand = mock(Hand.class);
        brain = mock(PlayerBrain.class);
        instance = new PlayerImpl(rules,hand,brain);
    }

    @Test
    public void testGetScore() {
        when(rules.score(hand)).thenReturn(7);
        assertEquals(7, instance.getScore());
        verify(rules).score(hand);
    }

    @Test
    public void testReceive() {
        Card card = new Card(1, 1);
        Boolean shown = true;
        instance.receive(card, shown);
        verify(hand).add(card, shown);
    }

    @Test
    public void testStand() {
        when(rules.score(hand)).thenReturn(7);
        when(brain.shouldPass(7)).thenReturn(false).thenReturn(true);
        assertFalse(instance.stand());
        assertTrue(instance.stand());
        verify(brain, times(2)).shouldPass(7);
    }
    
    @Test
    public void testIsBlackjack() {
        when(rules.score(hand)).thenReturn(7);
        when(rules.isBlackjack(7)).thenReturn(true, false);
        assertTrue(instance.isBlackjack());
        assertFalse(instance.isBlackjack());
        verify(rules, times(2)).isBlackjack(7);
    }
    
    @Test
    public void isBust() {
        when(rules.score(hand)).thenReturn(7);
        when(rules.isBust(7)).thenReturn(true, false);
        assertTrue(instance.isBust());
        assertFalse(instance.isBust());
        verify(rules, times(2)).isBust(7);
    }
    
}
