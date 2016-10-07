package com.pldp.blackjack.impl;

import com.pldp.blackjack.api.Hand;
import com.pldp.cards.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class HandImpl implements Hand {

    private static class Entry {
        boolean shown;
        Card card;
        Entry(Card card, boolean shown) {
            this.shown = shown;
            this.card = card;
        }
    }
    private final List<Entry> hand = new ArrayList<>();
    @Override
    public void add(Card card, Boolean shown) {
        hand.add(new Entry(card, shown));
    }

    @Override
    public Card flip() {
        Optional<Entry> opt = hand
                .stream()
                .filter(x -> !x.shown)
                .findFirst();
        opt.get().shown = true;
        return opt.get().card;
    }
    
    @Override
    public Stream<Card> getVisibleCards() {
        return hand
                .stream()
                .filter(e -> e.shown)
                .map(e->e.card);
    }
    @Override
    public boolean hasHiddenCards() {
        return hand
                .stream()
                .anyMatch(e -> !e.shown);
    }
}
