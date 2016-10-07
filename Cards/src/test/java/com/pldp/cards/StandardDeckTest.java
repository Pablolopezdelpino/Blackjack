package com.pldp.cards;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.pldp.cards.StandardDeck.*;

public class StandardDeckTest {
    
    public StandardDeckTest() {
    }

    @Test
    public void testGenStandardDeck() {
        Deck deck = mock(Deck.class);
        genStandardDeck(deck);
        verify(deck, times(NUM_CARDS)).add(any()); // it has added NUM_CARDS cards
        for(Suit suit: Suit.values()) {
            for(int value = MIN_VALUE; value <= MAX_VALUE; ++value) {
                verify(deck).add(new Card(suit.ordinal(), value)); // it has added each legal card once
            }
        }
    }

    void testDesc(Suit suit, int value, String desc) {
        assertEquals(desc, describeCard(new Card(suit.ordinal(), value)));
    }
    @Test
    public void testDescribeCard() {
        testDesc(Suit.Clubs, 1, "Ace of Clubs");
        testDesc(Suit.Clubs, 2, "Two of Clubs");
        testDesc(Suit.Clubs, 3, "Three of Clubs");
        testDesc(Suit.Clubs, 4, "Four of Clubs");
        testDesc(Suit.Clubs, 5, "Five of Clubs");
        testDesc(Suit.Clubs, 6, "Six of Clubs");
        testDesc(Suit.Clubs, 7, "Seven of Clubs");
        testDesc(Suit.Clubs, 8, "Eight of Clubs");
        testDesc(Suit.Clubs, 9, "Nine of Clubs");
        testDesc(Suit.Clubs, 10, "Ten of Clubs");
        testDesc(Suit.Clubs, 11, "Jack of Clubs");
        testDesc(Suit.Clubs, 12, "Queen of Clubs");
        testDesc(Suit.Clubs, 13, "King of Clubs");
        
        testDesc(Suit.Spades, 1, "Ace of Spades");
        testDesc(Suit.Spades, 2, "Two of Spades");
        testDesc(Suit.Spades, 3, "Three of Spades");
        testDesc(Suit.Spades, 4, "Four of Spades");
        testDesc(Suit.Spades, 5, "Five of Spades");
        testDesc(Suit.Spades, 6, "Six of Spades");
        testDesc(Suit.Spades, 7, "Seven of Spades");
        testDesc(Suit.Spades, 8, "Eight of Spades");
        testDesc(Suit.Spades, 9, "Nine of Spades");
        testDesc(Suit.Spades, 10, "Ten of Spades");
        testDesc(Suit.Spades, 11, "Jack of Spades");
        testDesc(Suit.Spades, 12, "Queen of Spades");
        testDesc(Suit.Spades, 13, "King of Spades");
        
        testDesc(Suit.Hearts, 1, "Ace of Hearts");
        testDesc(Suit.Hearts, 2, "Two of Hearts");
        testDesc(Suit.Hearts, 3, "Three of Hearts");
        testDesc(Suit.Hearts, 4, "Four of Hearts");
        testDesc(Suit.Hearts, 5, "Five of Hearts");
        testDesc(Suit.Hearts, 6, "Six of Hearts");
        testDesc(Suit.Hearts, 7, "Seven of Hearts");
        testDesc(Suit.Hearts, 8, "Eight of Hearts");
        testDesc(Suit.Hearts, 9, "Nine of Hearts");
        testDesc(Suit.Hearts, 10, "Ten of Hearts");
        testDesc(Suit.Hearts, 11, "Jack of Hearts");
        testDesc(Suit.Hearts, 12, "Queen of Hearts");
        testDesc(Suit.Hearts, 13, "King of Hearts");
        
        testDesc(Suit.Diamonds, 1, "Ace of Diamonds");
        testDesc(Suit.Diamonds, 2, "Two of Diamonds");
        testDesc(Suit.Diamonds, 3, "Three of Diamonds");
        testDesc(Suit.Diamonds, 4, "Four of Diamonds");
        testDesc(Suit.Diamonds, 5, "Five of Diamonds");
        testDesc(Suit.Diamonds, 6, "Six of Diamonds");
        testDesc(Suit.Diamonds, 7, "Seven of Diamonds");
        testDesc(Suit.Diamonds, 8, "Eight of Diamonds");
        testDesc(Suit.Diamonds, 9, "Nine of Diamonds");
        testDesc(Suit.Diamonds, 10, "Ten of Diamonds");
        testDesc(Suit.Diamonds, 11, "Jack of Diamonds");
        testDesc(Suit.Diamonds, 12, "Queen of Diamonds");
        testDesc(Suit.Diamonds, 13, "King of Diamonds");
    }
    
}
