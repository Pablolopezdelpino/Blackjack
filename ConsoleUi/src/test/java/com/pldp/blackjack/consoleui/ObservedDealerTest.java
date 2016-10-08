package com.pldp.blackjack.consoleui;

import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.api.Player;
import com.pldp.cards.Card;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ObservedDealerTest {
    ObservedDealer instance;
    DealerListener dl;
    Hand hand;
    Player player;
    @Before
    public void setUp() {
        dl = mock(DealerListener.class);
        hand = mock(Hand.class);
        player = mock(Player.class);
        instance = new ObservedDealer(dl, hand, player);
    }
    
    Card ace = new Card(1, 1);
    @Test
    public void testFlip() {
        when(hand.flip()).thenReturn(ace);
        instance.flip();
        verify(dl).onFlip(ace);
    }    
}
