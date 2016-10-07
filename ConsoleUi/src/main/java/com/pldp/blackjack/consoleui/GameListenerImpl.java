package com.pldp.blackjack.consoleui;

import com.pldp.blackjack.Rules;
import com.pldp.blackjack.api.Player;
import com.pldp.cards.Card;
import com.pldp.cards.StandardDeck;
import com.pldp.util.console.Out;

public class GameListenerImpl implements GameListener {
    private final Rules rules;
    private final Out out;
    GameListenerImpl(Out out, Rules rules){
        this.rules = rules;
        this.out = out;
    }
    @Override
    public void onStartDealPhase(Player player) {
        out.println("Starting deal phase");
    }

    @Override
    public void onEndDealPhase(Player player) {
        out.println("Deal phase finished");
    }
    
    @Override
    public void onGiveCard(Player player, Card card) {
        out.print(String.format("%s receives the %s. ", player.getName(), StandardDeck.describeCard(card)));
        reportScore(player);
    }

    private void reportScore(Player player) {
        out.println(String.format("Score: %d", rules.score(player.getHand())));
        if(rules.isBust(player.getHand())) {
            out.println(String.format("%s Bust!", player.getName()));
        }
        if(rules.isBlackjack(player.getHand())) {
            out.println(String.format("%s Blackjack!", player.getName()));
        }
    }

    @Override
    public void onGiveHiddenCard(Player player) {
        out.println(String.format("%s receives the Hole Card", player.getName()));
    }

    @Override
    public void onShuffleCards(Player player) {
        out.println(String.format("%s shuffles the cards", player.getName()));
    }

    @Override
    public void onTurnStart(Player player) {
        out.println(String.format("%s's turn starts", player.getName()));
    }

    @Override
    public void onHit(Player player) {
        out.println(String.format("%s hits", player.getName()));
    }

    @Override
    public void onStand(Player player) {
        out.println(String.format("%s stands", player.getName()));
    }

    @Override
    public void onFlip(Player player, Card card) {
        out.print(String.format("%s flips the %s. ", player.getName(), StandardDeck.describeCard(card)));
        reportScore(player);
    }

    @Override
    public void onTurnEnd(Player player) {
        //Output.println(String.format("%s's turn ended", player.getName()));
    }

    @Override
    public void onResult(Player player, Rules.Result r) {
        out.println(String.format("%s's result: %s", player.getName(), r.name()));
    }
    
}
