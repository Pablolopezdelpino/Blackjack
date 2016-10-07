package com.pldp.cards;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeckTest {
    
    public DeckTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAdd() {
        Deck instance = new Deck();
        assertTrue(instance.isEmpty());
        instance.add(new Card(1, 1));
        assertFalse(instance.isEmpty());
    }

    @Test
    public void testRetrieve() {
        Deck instance = new Deck();
        assertTrue(instance.isEmpty());
        Card inputCard = new Card(1, 1);
        instance.add(inputCard);
        assertFalse(instance.isEmpty());
        Card result = instance.retrieve();
        assertEquals(inputCard, result);
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testShuffle() {
        // You can never be sure
        // http://dilbert.com/strip/2001-10-25
        new Deck().shuffle();
    }
    
    @Test(expected = RuntimeException.class)
    public void testRetrieveFromEmptyDeck() {
        Deck instance = new Deck();
        assertTrue(instance.isEmpty());
        instance.retrieve();
    }
    
}
