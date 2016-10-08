package com.pldp.blackjack;

import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.impl.HandImpl;
import com.pldp.cards.Card;
import com.pldp.cards.Deck;
import com.pldp.cards.StandardDeck;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class RulesTest {
    Rules rules;
    @Before
    public void setUp() {
        rules = new Rules();
    }
    
    @After
    public void tearDown() {
        rules = null;
    }
    
    @Test
    public void testGenDeck() {
        Deck deck = Mockito.mock(Deck.class);
        rules.genDeck(deck);
        int numCards = StandardDeck.NUM_CARDS * Rules.NUM_DECKS;
        verify(deck, times(numCards)).add(any(Card.class)); // Should have added 
        for(int v = StandardDeck.MIN_VALUE; v <= StandardDeck.MAX_VALUE; ++v) {
            for(StandardDeck.Suit s: StandardDeck.Suit.values()) {
                verify(deck, times(Rules.NUM_DECKS)).add(new Card(s.ordinal(), v)); // each card must have been added 4 times
            }
        }
    }

    /**
     * Helper.
     */
    private static HandImpl genHand(Card... cards) {
        HandImpl h = new HandImpl();
        for (Card c : cards) {
            h.add(c, true);
        }
        return h;
    }

    private Hand randomHand(int numCards) {
        Deck deck = new Deck();
        rules.genDeck(deck);
        Hand hand = new HandImpl();
        for (int i = 0; i < numCards; ++i) {
            hand.add(deck.retrieve(), true);
        }
        return hand;
    }
    /**
     * Test of evaluate method, of class Rules.
     */
    @Test
    public void testEvaluate() {
        for(int myScore = 1; myScore < 30; ++myScore) {
            for(int dealerScore = 1; dealerScore < 30; ++dealerScore) {
                Rules.Result result = rules.evaluate(myScore, dealerScore);
                if(rules.isBust(myScore)) {
                    assertEquals(Rules.Result.lose, result); // lose if I bust
                } else if(rules.isBust(dealerScore)) {
                    assertEquals(Rules.Result.win, result); // win if he busts
                } else {
                    if(myScore < dealerScore) {
                        assertEquals(Rules.Result.lose, result); // lose if lower score
                    } else if(myScore > dealerScore) { 
                        assertEquals(Rules.Result.win, result); // win if higher score
                    } else {
                        assertEquals(Rules.Result.push, result); // push if same score
                    }
                }
            }
        }
    }

    /**
     * Check if the two cards constitute a blackjack, must equal t.
     */
    private void checkBlackjack(int score, boolean t) {
        assertEquals(t, rules.isBlackjack(score));
    }
    /**
     * Test of isBlackjack method, of class Rules.
     */
    @Test
    public void testIsBlackjack() {
        for (int score = 1; score < 30; ++score) {
            checkBlackjack(score, score == 21); // blackjack when score = 21
        }
    }
    
    private void checkBust(int score, boolean t) {
        assertEquals(t, rules.isBust(score));
    }
    
    @Test
    public void testIsBust() {
        for (int score = 1; score < 30; ++score) {
            checkBust(score, score > 21); // bust when score > 21
        }
    }

    /**
     * Test of minValue method, of class Rules.
     */
    @Test
    public void testMinValue() {
        for(StandardDeck.Suit suit: StandardDeck.Suit.values()) {
            for(int value=StandardDeck.MIN_VALUE;value<=StandardDeck.MAX_VALUE;++value) {
                assertEquals(Math.min(value, 10), rules.minValue(new Card(suit.ordinal(), value))); // check min value of all cards (1 - 10)
            }
        }
    }
    
    @Test
    public void testDealerShouldPass() {
        for(int score=1;score<=30;++score) {
            assertEquals(score >= 17, rules.dealerShouldPass(score));
        }
    }
}
