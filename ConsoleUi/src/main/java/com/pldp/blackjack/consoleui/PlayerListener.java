package com.pldp.blackjack.consoleui;

import com.pldp.cards.Card;

public interface PlayerListener {
    void onReceiveCard(Card card, boolean shown);
    void onPreDecision();
    void onPostDecision(boolean ret);
    void onBlackjack();
    void onBust();
}
