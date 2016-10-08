package com.pldp.blackjack;

import com.pldp.blackjack.api.Dealer;
import com.pldp.blackjack.api.Player;
import com.pldp.cards.Deck;

public class Game {

    private boolean tooGreedy(Deck deck, Player player) {
        while (true) {
            if (player.isBlackjack() || player.stand()) {
                return false;
            }
            player.receive(deck.retrieve(), true);
            if (player.isBust()) { // oops
                return true;
            }
        }
    }

    public Rules.Result run(Rules rules, Deck deck, Dealer dealer, Player player) {
        rules.genDeck(deck);
        deck.shuffle();
        dealer.receive(deck.retrieve(), true);
        dealer.receive(deck.retrieve(), false);
        player.receive(deck.retrieve(), true);
        player.receive(deck.retrieve(), true);
        if (!player.isBlackjack()) {
            if(tooGreedy(deck, player)) {
                return Rules.Result.lose;
            }
            if(tooGreedy(deck, dealer)) {
                return Rules.Result.win;
            }
        }
        dealer.flip();
        return rules.evaluate(player.getScore(), dealer.getScore());
    }
}
