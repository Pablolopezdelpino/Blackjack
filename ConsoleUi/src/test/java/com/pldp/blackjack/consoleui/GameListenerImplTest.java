package com.pldp.blackjack.consoleui;

import com.pldp.blackjack.api.Player;
import com.pldp.blackjack.Rules;
import com.pldp.cards.Card;
import com.pldp.util.console.In;
import com.pldp.util.console.Out;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameListenerImplTest {
    Out out;
    GameListenerImpl instance;
    Rules rules;
    Player player;
    @Before
    public void setUp() {
        out = mock(Out.class);
        rules = mock(Rules.class);
        player = mock(Player.class);
        instance = new GameListenerImpl(out, rules);
    }
    
    @After
    public void tearDown() {
        out = null;
        rules = null;
        instance = null;
    }

    /**
     * Test of onStartDealPhase method, of class GameListenerImpl.
     */
    @Test
    public void testOnStartDealPhase() {
        instance.onStartDealPhase(player);
        verify(out, atLeastOnce()).println(any());
    }

    /**
     * Test of onEndDealPhase method, of class GameListenerImpl.
     */
    @Test
    public void testOnEndDealPhase() {
        instance.onEndDealPhase(player);
        verify(out, atLeastOnce()).println(any());
    }

    /**
     * Test of onGiveCard method, of class GameListenerImpl.
     */
    @Test
    public void testOnGiveCard() {
        Card card = new Card(1, 1);
        instance.onGiveCard(player, card);
        verify(out, atLeastOnce()).println(any());
    }

    /**
     * Test of onGiveHiddenCard method, of class GameListenerImpl.
     */
    @Test
    public void testOnGiveHiddenCard() {
        instance.onGiveHiddenCard(player);
        verify(out, atLeastOnce()).println(any());
    }

    /**
     * Test of onShuffleCards method, of class GameListenerImpl.
     */
    @Test
    public void testOnShuffleCards() {
        instance.onShuffleCards(player);
        verify(out, atLeastOnce()).println(any());
    }

    /**
     * Test of onTurnStart method, of class GameListenerImpl.
     */
    @Test
    public void testOnTurnStart() {
        instance.onTurnStart(player);
        verify(out, atLeastOnce()).println(any());
    }

    /**
     * Test of onHit method, of class GameListenerImpl.
     */
    @Test
    public void testOnHit() {
        instance.onHit(player);
        verify(out, atLeastOnce()).println(any());
    }

    /**
     * Test of onStand method, of class GameListenerImpl.
     */
    @Test
    public void testOnStand() {
        instance.onStand(player);
        verify(out, atLeastOnce()).println(any());
    }

    /**
     * Test of onFlip method, of class GameListenerImpl.
     */
    @Test
    public void testOnFlip() {
        instance.onFlip(player, new Card(1, 1));
        verify(out, atLeastOnce()).println(any());
    }

    /**
     * Test of onTurnEnd method, of class GameListenerImpl.
     */
    @Test
    public void testOnTurnEnd() {
        instance.onTurnEnd(player);
        verify(out, never()).println(any());
    }

    /**
     * Test of onResult method, of class GameListenerImpl.
     */
    @Test
    public void testOnResult() {
        instance.onResult(player, Rules.Result.win);
        verify(out, atLeastOnce()).println(any());
    }
    
}
