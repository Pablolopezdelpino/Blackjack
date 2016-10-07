package com.pldp.cards;

import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {
    @Test
    public void testGetValue() {
        for(int value = 1; value < 3000; ++value) {
            Card card = new Card(1, value);
            assertEquals(value, card.getValue());
        }
    }

    @Test
    public void testGetSuit() {
        for(int suit = 1; suit < 3000; ++suit) {
            Card card = new Card(suit, 1);
            assertEquals(suit, card.getSuit());
        }
    }

    @Test
    public void testEquals() {
        for(int value = 1; value < 3000; ++value) {
            for(int suit = 1; suit < 3000; ++suit) {
                assertEquals(new Card(suit, value), new Card(suit, value));
                assertNotEquals(new Card(suit, value), null);
                assertNotEquals(new Card(suit, value), 1L);
            }
        }
    }
    
}
