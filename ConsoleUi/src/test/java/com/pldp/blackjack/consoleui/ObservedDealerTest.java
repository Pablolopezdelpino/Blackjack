package com.pldp.blackjack.consoleui;

import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.api.Player;
import com.pldp.cards.Card;
import com.pldp.cards.Deck;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ObservedDealerTest {
    ObservedDealer instance;
    GameListener gl;
    Deck deck;
    Player player;
    @Before
    public void setUp() {
        gl = mock(GameListener.class);
        deck = mock(Deck.class);
        player = mock(Player.class);
        instance = new ObservedDealer(gl, deck, player);
    }
    
    Card ace = new Card(1, 1);
    @Test
    public void testFlip() {
        Hand hand = mock(Hand.class);
        when(player.getHand()).thenReturn(hand);
        instance.flip();
        verify(gl).onFlip(any(), any());
    }

    @Test
    public void testStart() {
        List<Player> players = new ArrayList<Player>();
        instance.start(players);
        verify(gl).onStartDealPhase(any());
        verify(gl).onEndDealPhase(any());
    }

    @Test
    public void testGiveCard() {
        instance.giveCard(player, ace, true);
        verify(gl).onGiveCard(player, ace);
    }
    
    @Test
    public void testGiveHiddenCard() {
        instance.giveCard(player, ace, false);
        verify(gl).onGiveHiddenCard(player);
    }

    @Test
    public void testShuffleDeck() {
        instance.shuffleDeck();
        verify(gl).onShuffleCards(any());
    }
    
}
