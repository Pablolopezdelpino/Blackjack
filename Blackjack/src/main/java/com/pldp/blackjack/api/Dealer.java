package com.pldp.blackjack.api;

import com.pldp.cards.Card;

public interface Dealer extends Player {
    Card flip();
}
