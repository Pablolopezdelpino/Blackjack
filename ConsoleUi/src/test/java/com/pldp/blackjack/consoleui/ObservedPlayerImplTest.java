package com.pldp.blackjack.consoleui;

import com.pldp.blackjack.api.Dealer;
import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.api.Player;
import com.pldp.cards.Card;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ObservedPlayerImplTest {
    ObservedPlayerImpl instance;
    GameListener gl;
    Player player;
    @Before
    public void setUp() {
        gl = mock(GameListener.class);
        player = mock(Player.class);
        instance = new ObservedPlayerImpl(gl, player);
    }
    
    @Test
    public void testGetHand() {
        Hand hand = mock(Hand.class);
        when(player.getHand()).thenReturn(hand);
        Hand result = instance.getHand();
        assertEquals(hand, result);
        verify(player).getHand();
    }

    @Test
    public void testGetName() {
        String name = "Test!";
        when(player.getName()).thenReturn(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    Card ace = new Card(1, 1);
    
    @Test
    public void testReceive() {
        instance.receive(ace, true);
        verify(player).receive(ace, true);
    }

    @Test
    public void testOnTurn() {
        Dealer dealer = mock(Dealer.class);
        instance.onTurn(dealer);
        verify(player).onTurn(dealer);
        verify(gl).onTurnStart(any());
        verify(gl).onTurnEnd(any());
    }
    
}
