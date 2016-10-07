package com.pldp.blackjack.impl;

import com.pldp.blackjack.api.Dealer;
import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.api.Player;
import com.pldp.cards.Card;
import com.pldp.cards.Deck;
import java.util.List;

public class DealerImpl implements Dealer {
    private final Deck deck;
    private final Player player; // prefer composition to inheritance
    public DealerImpl(Deck deck, Player player) {
        this.deck = deck;
        this.player = player;
    }
    
    @Override
    public void start(List<Player> players) {
        shuffleDeck();
        selectAndGiveCard(player, true);
        selectAndGiveCard(player, false);
        for (Player p : players) {
            selectAndGiveCard(p, true);
            selectAndGiveCard(p, true);
        }
    }
    
    @Override
    public Card flip() {
        return player.getHand().flip();
    }
    
    @Override
    public void onTurn(Dealer dealer) {
        player.onTurn(dealer);
    }
    
    @Override
    public Hand getHand() {
        return player.getHand();
    }
    
    @Override
    public void requestCard(Player p) {
        selectAndGiveCard(p, true);
    }
    
    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public void receive(Card card, Boolean shown) {
        player.receive(card, shown);
    }
    
    @Override
    public void shuffleDeck() {   
        deck.shuffle();
    }
    
    @Override
    public void giveCard(Player p, Card card, boolean shown) {
        p.receive(card, shown); 
    }
    
    private void selectAndGiveCard(Player p, boolean shown) {
        giveCard(p, deck.retrieve(), shown);
    }
}
