package com.pldp.blackjack.impl;

import com.pldp.blackjack.Rules;
import com.pldp.blackjack.api.Dealer;
import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.api.Player;
import com.pldp.blackjack.api.PlayerBrain;
import com.pldp.cards.Card;

public class PlayerImpl implements Player {

    private final String name;
    private final Hand hand;
    private final PlayerBrain brain;
    private final Rules rules;
    public PlayerImpl(Rules rules, String name, Hand hand, PlayerBrain brain) {
        this.name = name;
        this.hand = hand;
        this.brain = brain;
        this.rules = rules;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Hand getHand() {
        return hand;
    }
    
    
    @Override
    public void receive(Card card, Boolean shown) {
        hand.add(card, shown);
    }

    @Override
    public void onTurn(Dealer dealer) {
        while (!rules.isBust(hand)
                && !rules.isBlackjack(hand)
                && !brain.shouldPass(hand)) {
            dealer.requestCard(this);
        }
    }
}
