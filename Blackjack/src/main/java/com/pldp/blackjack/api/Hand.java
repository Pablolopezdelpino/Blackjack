package com.pldp.blackjack.api;

import com.pldp.cards.Card;
import java.util.stream.Stream;

public interface Hand {
    void add(Card card, boolean shown);
    Card flip();
    Stream<Card> getVisibleCards();
    boolean hasHiddenCards();
}
