package com.pldp.blackjack.api;

import com.pldp.cards.Card;


public interface Player {
    Hand getHand();
    String getName();
    void onTurn(Dealer dealer);
    void receive(Card card, Boolean shown);
}
