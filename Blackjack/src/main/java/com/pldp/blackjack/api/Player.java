package com.pldp.blackjack.api;

import com.pldp.cards.Card;


public interface Player {
    int getScore();
    void receive(Card card, boolean shown);
    boolean isBlackjack();
    boolean isBust();
    boolean stand();
}
