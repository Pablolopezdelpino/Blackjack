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
    Deck deck;
    Player dealerPlayer;

    @Before
    public void beforeTest() {
        deck = mock(Deck.class);
        dealerPlayer = mock(Player.class);
        instance = new DealerImpl(deck, dealerPlayer);
    }

    /**
     * Test of getHand method, of class DealerImpl.
     */
    @Test
    public void testGetHand() {
        Hand result = new HandImpl();
        when(dealerPlayer.getHand()).thenReturn(result);
        assertEquals(instance.getHand(), result); // dealer must return the assigned hand
    }

    /**
     * Test of getName method, of class DealerImpl.
     */
    @Test
    public void testGetName() {
        String name = "Test";
        when(dealerPlayer.getName()).thenReturn(name);
        assertEquals(instance.getName(), name); // dealer must return the assigned name
    }

    Card ace = new Card(StandardDeck.Suit.Clubs.ordinal(), 1);

    @Test
    public void testFlip() {
        Hand hand = mock(Hand.class);
        when(hand.flip()).thenReturn(ace);
        when(dealerPlayer.getHand()).thenReturn(hand);
        assertEquals(ace, instance.flip()); // dealer must return flipped card
        verify(hand).flip(); // dealer must flip the hand
    }

    @Test
    public void testShuffleDeck() {
        instance.shuffleDeck();
        verify(deck).shuffle(); // dealer must shuffle the deck
    }

    @Test
    public void testGiveCard() {
        Player player = mock(Player.class);
        instance.giveCard(player, ace, true);
        verify(player).receive(ace, true); // dealer must give the given card
    }

    @Test
    public void testRequestCard() {
        Player player = mock(Player.class);
        when(deck.retrieve()).thenReturn(ace);
        instance.requestCard(player);

        verify(player).receive(ace, true); // dealer must give the 
        verify(deck).retrieve();
    }

    @Test
    public void testStart() {
        Player player = mock(Player.class);
        //Hand hand = mock(Hand.class);
        //when(player.getHand()).thenReturn(hand);
        when(deck.retrieve()).thenReturn(ace);

        //Hand dealerHand = mock(Hand.class);
        //when(dealerPlayer.getHand()).thenReturn(dealerHand);
        List<Player> players = Arrays.asList(player);

        instance.start(players);
        verify(deck, times(4)).retrieve();
        verify(deck).shuffle(); // dealer must shuffle
        verify(dealerPlayer).receive(ace, true); // dealer must receive a visible card
        verify(dealerPlayer).receive(ace, false); // dealer must receive a hidden card
        verify(dealerPlayer, times(2)).receive(any(), any()); // dealer must receive two cards
        verify(player, times(2)).receive(ace, true); // other players must receive two cards
        verify(player, times(2)).receive(any(), any());
    }
    
    
    @Test
    public void testOnTurn() {
        instance.onTurn(instance);
        verify(dealerPlayer).onTurn(instance);
    }
    
    @Test
    public void testReceive() {
        instance.receive(ace, true);
        verify(dealerPlayer).receive(ace, true);
    }
}
