package com.pldp.blackjack;

import com.pldp.blackjack.api.Hand;
import com.pldp.cards.Deck;
import com.pldp.cards.StandardDeck;
import com.pldp.cards.Card;

public class Rules {
    
    public static enum Result {
        lose,
        win,
        blackjack,
        push
    }
    /** Our target score. */
    public static final int GOAL_SCORE = 21;
    public static final int NUM_DECKS = 4;
    /** Build a deck. */
    public void genDeck(Deck deck) {
        for(int i=0;i<NUM_DECKS;++i) {
            StandardDeck.genStandardDeck(deck);
        }
    }
    
    /** Evaluate the hand relative to the dealer's hand. */
    public Result evaluate(Hand h, Hand dealerHand) {
        if(isBust(h)) {
            return Result.lose;
        }
        if(isBust(dealerHand)) {
            return Result.win;
        }
        int score = score(h);
        int dealerScore = score(dealerHand);
        if(score < dealerScore) {
            return Result.lose;
        } else if(score > dealerScore) {
            return Result.win;
        } else /*if(score == dealerScore)*/ {
            return Result.push;
        }
    }
    
    public int score(Hand h) {
        // this is the minimum score
        int score = h.getVisibleCards()
                .mapToInt(x -> minValue(x))
                .sum();
        // we have this number of aces
        int numAces = (int) h.getVisibleCards()
                .filter(x -> isAce(x))
                .count();
        
        // question: how many aces may we use with a value of 11 instead of 1
        // in order to get optimal score?
        
        // Solution 1
        /*while(score + 10 <= GOAL && numAces > 0) {
            score += 10;
            numAces--;
        }*/
        
        // Solution 2
        int diff = (GOAL_SCORE - score);
        int numAcesRequired = diff/ 10;
        // we need numAcesRequired aces to get to the optimal score, but unfortunately we only have numAces
        int acesToTransform = Math.min(numAcesRequired, numAces);
        score += 10 * acesToTransform;
        
        return score;
    }
    
    public boolean isAce(Card c) {
        return c.getValue() == 1;
    }
    
    public boolean isBlackjack(Hand h) {
        return score(h) == GOAL_SCORE;
    }
    
    public int minValue(Hand h) {
        return h.getVisibleCards()
                .mapToInt(x -> minValue(x))
                .sum();
    }
    
    public boolean isBust(Hand h) {
        return score(h) > Rules.GOAL_SCORE;
    }
    
    public int minValue(Card card) {
        return Math.min(card.getValue(), 10);
    }
}
