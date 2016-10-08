package com.pldp.blackjack.consoleui;

import com.pldp.blackjack.api.Player;
import com.pldp.cards.Card;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ObservedPlayerTest {

    ObservedPlayer instance;
    PlayerListener pl;
    Player player;

    @Before
    public void setUp() {
        pl = mock(PlayerListener.class);
        player = mock(Player.class);
        instance = new ObservedPlayer(pl, player);
    }

    Card ace = new Card(1, 1);

    @Test
    public void testGetScore() {
        when(player.getScore()).thenReturn(3);
        assertEquals(3, instance.getScore());
        verify(player).getScore();
    }

    @Test
    public void testIsBlackjack() {
        when(player.isBlackjack()).thenReturn(true, false);
        assertTrue(instance.isBlackjack());
        assertFalse(instance.isBlackjack());
        verify(player, times(2)).isBlackjack();
    }

    @Test
    public void testIsBust() {
        when(player.isBust()).thenReturn(true, false);
        assertTrue(instance.isBust());
        assertFalse(instance.isBust());
        verify(player, times(2)).isBust();
    }

    @Test
    public void testReceive() {
        Card card = ace;
        instance.receive(card, true);
        instance.receive(card, false);
        verify(player).receive(card, true);
        verify(player).receive(card, false);
        verify(pl).onReceiveCard(card, true);
        verify(pl).onReceiveCard(card, false);
    }

    @Test
    public void testStand() {
        when(player.stand()).thenReturn(true, false);
        assertTrue(instance.stand());
        assertFalse(instance.stand());
        verify(player, times(2)).stand();
        verify(pl, times(2)).onPreDecision();
        verify(pl).onPostDecision(true);
        verify(pl).onPostDecision(false);
    }

}
