package com.pldp.blackjack.impl;

import com.pldp.blackjack.api.Dealer;
import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.api.Player;
import com.pldp.cards.Card;

public class DealerImpl implements Dealer {
    private final Hand hand;
    private final Player player; // prefer composition to inheritance
    public DealerImpl(Hand hand, Player player) {
        this.hand = hand;
        this.player = player;
    }
    
    @Override
    public Card flip() {
        return hand.flip();
    }
    
    @Override
    public boolean stand() {
        return player.stand();
    }
    
    @Override
    public int getScore() {
        return player.getScore();
    }
    
    @Override
    public void receive(Card card, boolean shown) {
        player.receive(card, shown);
    }

    @Override
    public boolean isBlackjack() {
        return player.isBlackjack();
    }

    @Override
    public boolean isBust() {
        return player.isBust();
    }
}
