package com.pldp.blackjack.impl;

import com.pldp.blackjack.Rules;
import com.pldp.cards.Card;
import org.junit.Test;
import static org.junit.Assert.*;

public class HandImplTest {
    private final Rules rules = new Rules();
    /**
     * Test of add method, of class HandImpl.
     */
    @Test
    public void testAdd() {
        HandImpl instance = new HandImpl();
        instance.add(new Card(1, 1), true);
        assertEquals(1, rules.minValue(instance));
        assertEquals(11, rules.score(instance));
    }

    /**
     * Test of flip method, of class HandImpl.
     */
    @Test
    public void testFlip() {
        HandImpl instance = new HandImpl();
        Card c1 = new Card(1, 1);
        Card c2 = new Card(1, 2);
        Card c3 = new Card(1, 3);
        instance.add(c1, true);
        instance.add(c2, false);
        instance.add(c3, true);
        Card result = instance.flip();
        assertEquals(c2, result);
    }

    /**
     * Test of getVisibleCards method, of class HandImpl.
     */
    @Test
    public void testGetVisibleCards() {
        HandImpl instance = new HandImpl();
        Card c1 = new Card(1, 1);
        Card c2 = new Card(1, 2);
        Card c3 = new Card(1, 3);
        instance.add(c1, true);
        instance.add(c2, false);
        instance.add(c3, true);
        assertEquals(1, instance.getVisibleCards().filter(x -> x.getValue() == 1).count());
        assertEquals(0, instance.getVisibleCards().filter(x -> x.getValue() == 2).count());
        assertEquals(1, instance.getVisibleCards().filter(x -> x.getValue() == 3).count());
    }

    /**
     * Test of hasHiddenCards method, of class HandImpl.
     */
    @Test
    public void testHasHiddenCards() {
        HandImpl instance = new HandImpl();
        Card c1 = new Card(1, 1);
        Card c2 = new Card(1, 2);
        Card c3 = new Card(1, 3);
        instance.add(c1, true);
        assertEquals(false, instance.hasHiddenCards());
        instance.add(c2, false);
        assertEquals(true, instance.hasHiddenCards());
        instance.add(c3, true);
        assertEquals(true, instance.hasHiddenCards());
    }
    
}
