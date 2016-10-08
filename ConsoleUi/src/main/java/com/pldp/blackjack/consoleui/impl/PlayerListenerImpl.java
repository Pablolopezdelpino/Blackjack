package com.pldp.blackjack.consoleui.impl;

import com.pldp.blackjack.Rules;
import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.consoleui.Helper;
import com.pldp.blackjack.consoleui.PlayerListener;
import com.pldp.cards.Card;
import com.pldp.cards.StandardDeck;
import com.pldp.util.console.Out;

public class PlayerListenerImpl implements PlayerListener {
    
    private final Out out;
    private final Rules rules;
    private final Hand dealerHand;
    private final Hand playerHand;

    public PlayerListenerImpl(Out out, Rules rules, Hand dealerHand, Hand playerHand) {
        this.out = out;
        this.rules = rules;
        this.dealerHand = dealerHand;
        this.playerHand = playerHand;
    }

    @Override
    public void onReceiveCard(Card card, boolean shown) {
        out.println(String.format("You receive a %s.", StandardDeck.describeCard(card)));
    }

    @Override
    public void onPreDecision() {
        Helper.describeHand(out, "The dealer's hand is: ", rules, dealerHand);
        Helper.describeHand(out, "Your hand is: ", rules, playerHand);
    }

    @Override
    public void onPostDecision(boolean stand) {
        out.println(String.format("You decide to %s.", stand ? "stand" : "hit"));
    }

    @Override
    public void onBlackjack() {
        out.println("You got a blackjack!");
    }

    @Override
    public void onBust() {
        out.println("You bust!");
    }
}
