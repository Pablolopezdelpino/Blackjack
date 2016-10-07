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
    String name;
    Hand hand;
    PlayerBrain brain;
    PlayerImpl instance;
    
    @Before
    public void setUp() {
        rules = mock(Rules.class);
        name = "Bob";
        hand = mock(Hand.class);
        brain = mock(PlayerBrain.class);
        instance = new PlayerImpl(rules,name,hand,brain);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetName() {
        assertEquals(name, instance.getName());
    }

    @Test
    public void testGetHand() {
        assertEquals(hand, instance.getHand());
    }

    @Test
    public void testReceive() {
        Card card = new Card(1, 1);
        Boolean shown = true;
        instance.receive(card, shown);
        verify(hand).add(card, shown);
    }

    @Test
    public void testOnTurn() {
        when(brain.shouldPass(hand)).thenReturn(false).thenReturn(true);
        Dealer dealer = mock(Dealer.class);
        instance.onTurn(dealer);
        verify(brain, times(2)).shouldPass(hand);
        verify(dealer).requestCard(instance);
    }
    
}
