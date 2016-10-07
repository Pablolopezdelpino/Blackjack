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
        Hand blackjackHand = genHand(new Card(1, 1), new Card(1, 10));
        for (int j = 0; j < 300; ++j) {
            Hand hand = randomHand(3);
            Hand dealersHand = blackjackHand;
            Rules.Result result = rules.evaluate(hand, dealersHand);
            if (rules.isBlackjack(hand)) { // check blackjack always pushes against blackjack
                assertEquals(Rules.Result.push, result);
            } else { // check anything not blackjack always loses against blackjack
                assertEquals(Rules.Result.lose, result);
            }
        }
        Hand nonBlackjackHand = genHand(new Card(1, 1), new Card(1, 9));
        for (int j = 0; j < 300; ++j) {
            Hand hand = randomHand(3);
            Hand dealersHand = nonBlackjackHand;
            Rules.Result result = rules.evaluate(hand, dealersHand);
            if (rules.isBlackjack(hand)) { // check blackjack always wins against no blackjack
                assertEquals(Rules.Result.win, result);
            } else if(rules.isBust(hand)) { // check bust always loses
                assertEquals(Rules.Result.lose, result);
            } else if(rules.score(hand) < rules.score(dealersHand)){
                assertEquals(Rules.Result.lose, result);
            } else {
                assertEquals(rules.score(hand), rules.score(dealersHand));
            }
        }
    }

    /**
     * Check if the two cards constitute a blackjack, must equal t.
     */
    private void checkBlackjack(Card c1, Card c2, boolean t) {
        assertEquals(rules.isBlackjack(genHand(c1, c2)), t);
    }
    /**
     * Test of isBlackjack method, of class Rules.
     */
    @Test
    public void testIsBlackjack() {
        Card ace = new Card(1, 1);
        for (int v = StandardDeck.MIN_VALUE; v <= StandardDeck.MAX_VALUE; ++v) {
            Card card = new Card(1, v);
            boolean isFigure = v >= 10;
            checkBlackjack(ace, card, isFigure); // ace + figure -> blackjack
            checkBlackjack(card, ace, isFigure); // figure + ace -> blackjack
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
}
