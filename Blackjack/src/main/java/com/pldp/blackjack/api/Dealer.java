package com.pldp.blackjack.api;

import com.pldp.cards.Card;
import java.util.List;

public interface Dealer extends Player {
    Card flip();
    void requestCard(Player p);
    void start(List<Player> players);
    void shuffleDeck();
    void giveCard(Player p, Card card, boolean shown);
}
