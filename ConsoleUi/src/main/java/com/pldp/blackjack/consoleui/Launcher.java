package com.pldp.blackjack.consoleui;

import com.pldp.blackjack.consoleui.impl.PlayerBrainImpl;
import com.pldp.blackjack.consoleui.impl.DealerPlayerListenerImpl;
import com.pldp.blackjack.consoleui.impl.PlayerListenerImpl;
import com.pldp.blackjack.Rules;
import com.pldp.blackjack.api.Dealer;
import com.pldp.blackjack.Game;
import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.api.Player;
import com.pldp.blackjack.api.PlayerBrain;
import com.pldp.blackjack.impl.DealerBrainImpl;
import com.pldp.blackjack.impl.HandImpl;
import com.pldp.blackjack.impl.PlayerImpl;
import com.pldp.cards.Card;
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
        } while (playAgain(in));
    }

    private static boolean playAgain(In in) {
        return in.questionYN("Do you want to play again?");
    }

    private static void playGame(final Out out, final In in) {
        // Build the dealer
        final Rules rules = new Rules();
        //final GameListener gl = new GameListenerImpl(out, rules);
        final Deck deck = new Deck();
        final Hand dealerHand = new HandImpl();
        final Dealer dealer = createDealer(out, rules, dealerHand, deck);
        final Player player = createPlayer(out, rules, dealerHand, in);
        final Game game = new Game();
        Helper.describeResult(out, game.run(rules, deck, dealer, player));
    }

    private static Player createPlayer(final Out out, final Rules rules, final Hand dealerHand, final In in) {
        final Hand playerHand = new HandImpl();
        final PlayerBrain playerBrain = new PlayerBrainImpl(in);
        final PlayerListener pl = new PlayerListenerImpl(out, rules, dealerHand, playerHand);
        final Player player = new ObservedPlayer(pl, new PlayerImpl(rules, playerHand, playerBrain));
        return player;
    }

    private static Dealer createDealer(final Out out, final Rules rules, final Hand dealerHand, final Deck deck) {
        final PlayerBrain dealerBrain = new DealerBrainImpl(rules);
        final PlayerListener pl = new DealerPlayerListenerImpl(out, rules, dealerHand);
        final Player dealerPlayer = new ObservedPlayer(pl, new PlayerImpl(rules, dealerHand, dealerBrain));
        final DealerListener dl = (Card card) -> {
            out.println(String.format("The dealer flips a %s.", StandardDeck.describeCard(card)));
        };
        final Dealer dealer = new ObservedDealer(dl, dealerHand, dealerPlayer);
        return dealer;
    }

}
