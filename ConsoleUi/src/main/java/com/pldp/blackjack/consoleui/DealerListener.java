package com.pldp.blackjack.consoleui;

import com.pldp.cards.Card;

public interface DealerListener {
    void onFlip(Card card);
}
