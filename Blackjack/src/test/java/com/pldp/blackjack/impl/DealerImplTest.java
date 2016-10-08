package com.pldp.blackjack.impl;

import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.api.Player;
import com.pldp.blackjack.Rules;
import com.pldp.cards.Card;
import com.pldp.cards.Deck;
import com.pldp.cards.StandardDeck;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.Mockito.*;

public class DealerImplTest {

    Rules rules = new Rules();
    DealerImpl instance;
    Hand hand;
    Player dealerPlayer;

    @Before
    public void beforeTest() {
        hand = mock(Hand.class);
        dealerPlayer = mock(Player.class);
        instance = new DealerImpl(hand, dealerPlayer);
    }

    @Test
    public void testGetScore() {
        when(dealerPlayer.getScore()).thenReturn(7);
        assertEquals(7, instance.getScore());
        verify(dealerPlayer).getScore();
    }

    Card ace = new Card(StandardDeck.Suit.Clubs.ordinal(), 1);

    @Test
    public void testFlip() {
        when(hand.flip()).thenReturn(ace);
        assertEquals(ace, instance.flip()); // dealer must return flipped card
        verify(hand).flip(); // dealer must flip the hand
    }    
    
    @Test
    public void testIsBlackjack() {
        when(dealerPlayer.isBlackjack()).thenReturn(true).thenReturn(false);
        assertEquals(true, instance.isBlackjack());
        assertEquals(false, instance.isBlackjack());
        verify(dealerPlayer, times(2)).isBlackjack();
    }
    
    @Test
    public void testIsBust() {
        when(dealerPlayer.isBust()).thenReturn(true).thenReturn(false);
        assertEquals(true, instance.isBust());
        assertEquals(false, instance.isBust());
        verify(dealerPlayer, times(2)).isBust();
    }
    
    @Test
    public void testReceive() {
        instance.receive(ace, true);
        verify(dealerPlayer).receive(ace, true);
    }
    
    @Test
    public void testThink() {
        when(dealerPlayer.stand()).thenReturn(true).thenReturn(false);
        assertEquals(true, instance.stand());
        assertEquals(false, instance.stand());
        verify(dealerPlayer, times(2)).stand();
    }
}
