package com.pldp.blackjack.consoleui;

import com.pldp.blackjack.api.Player;
import com.pldp.blackjack.impl.DealerImpl;
import com.pldp.cards.Card;
import com.pldp.cards.Deck;
import java.util.List;

public class ObservedDealer extends DealerImpl {
    private GameListener gl;
    public ObservedDealer(GameListener gl, Deck deck, Player player) {
        super(deck, player);
        this.gl = gl;
    }
    
    @Override
    public Card flip() {
        Card card = super.flip();
        gl.onFlip(this, card);
        return card;
    }

    @Override
    public void start(List<Player> players) {
        gl.onStartDealPhase(this);
        super.start(players);
        gl.onEndDealPhase(this);
    }

    @Override
    public void giveCard(Player p, Card card, boolean shown) {
        super.giveCard(p, card, shown);
        if(shown) {
            gl.onGiveCard(p, card);
        } else {
            gl.onGiveHiddenCard(p);
        }
    }

    @Override
    public void shuffleDeck() {
        super.shuffleDeck();
        gl.onShuffleCards(this);
    }
}
