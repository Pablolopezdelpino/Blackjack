package com.pldp.blackjack.impl;

import com.pldp.blackjack.Rules;
import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.api.Player;
import com.pldp.blackjack.api.PlayerBrain;
import com.pldp.cards.Card;

public class PlayerImpl implements Player {
    private final Hand hand;
    private final PlayerBrain brain;
    private final Rules rules;
    public PlayerImpl(Rules rules, Hand hand, PlayerBrain brain) {
        this.hand = hand;
        this.brain = brain;
        this.rules = rules;
    }

    @Override
    public int getScore() {
        return rules.score(hand);
    }
    
    @Override
    public void receive(Card card, boolean shown) {
        hand.add(card, shown);
    }

    @Override
    public boolean isBlackjack() {
        return rules.isBlackjack(rules.score(hand));
    }

    @Override
    public boolean isBust() {
        return rules.isBust(rules.score(hand));
    }

    @Override
    public boolean stand() {
        return brain.shouldPass(getScore());
    }
}
