package com.pldp.blackjack.consoleui;

import com.pldp.blackjack.Rules;
import com.pldp.blackjack.api.Dealer;
import com.pldp.blackjack.Game;
import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.api.Player;
import com.pldp.blackjack.api.PlayerBrain;
import com.pldp.blackjack.impl.HandImpl;
import com.pldp.blackjack.impl.PlayerImpl;
import com.pldp.cards.Deck;
import com.pldp.cards.StandardDeck;
import com.pldp.util.console.In;
import com.pldp.util.console.Out;

public class Launcher {

    public static void main(String[] args) {
        Out out = new Out();
        In in = new In(out);
        do {
            playGame(out, in);
        } while (in.questionYN("Do you want to play again?"));
    }

    private static void playGame(final Out out, final In in) {
        // Build the dealer
        final Rules rules = new Rules();
        final GameListener gl = new GameListenerImpl(out, rules);
        final Deck deck = new Deck();
        rules.genDeck(deck);
        final Hand dealerHand = new HandImpl();
        final PlayerBrain dealerBrain = new PlayerBrain() {
            @Override
            public boolean shouldPass(Hand h) {
                out.println("The dealer's hand is: ");
                describeHand(out, rules, dealerHand);
                boolean passes = rules.score(h) >= 17;
                if (passes) {
                    out.println("The dealer stands!");
                } else {
                    out.println("The dealer hits!");
                }
                return passes;
            }
        };
        final Player dealerPlayer = new ObservedPlayerImpl(gl, new PlayerImpl(rules, "Dealer", dealerHand, dealerBrain));
        final Dealer dealer = new ObservedDealer(gl, deck, dealerPlayer);
        
        final Hand playerHand = new HandImpl();
        final PlayerBrain playerBrain = new PlayerBrain() {
            @Override
            public boolean shouldPass(Hand h) {
                out.println("The dealer's hand is: ");
                describeHand(out, rules, dealerHand);
                out.println("Your hand is: ");
                describeHand(out, rules, h);
                return in.questionYN("Should you stand?");
            }
        };
        final Player player = new ObservedPlayerImpl(gl, new PlayerImpl(rules, "Player", playerHand, playerBrain));
        Game game = new Game();
        describeResult(out, game.run(rules, dealer, player));
    }

    private static void describeHand(Out out, Rules rules, Hand h) {
        h.getVisibleCards().forEach(x -> out.println(String.format("\t - %s", StandardDeck.describeCard(x))));
        if (h.hasHiddenCards()) {
            out.println("\t - Hole Card");
        }
        out.println(String.format("Score: %d", rules.score(h)));
    }

    private static void describeResult(Out out, Rules.Result r) {
        out.println(String.format("Result: you %s", r.toString()));
    }
}
