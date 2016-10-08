package com.pldp.blackjack.consoleui.impl;

import com.pldp.blackjack.Rules;
import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.consoleui.Helper;
import com.pldp.blackjack.consoleui.PlayerListener;
import com.pldp.cards.Card;
import com.pldp.cards.StandardDeck;
import com.pldp.util.console.Out;

public class DealerPlayerListenerImpl implements PlayerListener {
    
    private final Out out;
    private final Rules rules;
    private final Hand dealerHand;

    public DealerPlayerListenerImpl(Out out, Rules rules, Hand dealerHand) {
        this.out = out;
        this.rules = rules;
        this.dealerHand = dealerHand;
    }

    @Override
    public void onReceiveCard(Card card, boolean shown) {
        out.println(String.format("The dealer receives a %s.", shown ? StandardDeck.describeCard(card) : "Hole Card"));
    }

    @Override
    public void onPreDecision() {
        Helper.describeHand(out, "The dealer's hand is: ", rules, dealerHand);
    }

    @Override
    public void onPostDecision(boolean stand) {
        out.println(String.format("The dealer decides to %s.", stand ? "stand" : "hit"));
    }

    @Override
    public void onBlackjack() {
        out.println("The dealer got a blackjack!");
    }

    @Override
    public void onBust() {
        out.println("The dealer busts!");
    }
}