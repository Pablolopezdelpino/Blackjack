package com.pldp.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/** A collection of cards. */
public class Deck {
    private final List<Card> cards = new ArrayList<>();
    
    public void add(Card card) {
        cards.add(card);
    }    
    
    public boolean isEmpty() {
        return cards.isEmpty();
    }
    
    /** pop a card. Die if empty. Better check firsts with isEmpty. */
    public Card retrieve() {
        if(cards.isEmpty()) {
            // at least give some useful info
            throw new RuntimeException("Retrieving card from empty deck");
        }
        int index = cards.size() - 1;
        Card ret = cards.get(index);
        cards.remove(index);
        return ret;        
    }
    
    /** Shuffle the deck randomly. */
    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(cards, new Random(seed));
    }
}
