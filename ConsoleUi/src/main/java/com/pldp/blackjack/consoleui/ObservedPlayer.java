package com.pldp.blackjack.consoleui;

import com.pldp.blackjack.api.Player;
import com.pldp.cards.Card;

public class ObservedPlayer implements Player {
    private final Player player;
    private final PlayerListener pl;
    public ObservedPlayer(PlayerListener pl, Player p) {
        this.player = p;
        this.pl = pl;
    }
    
    @Override
    public int getScore() {
        return player.getScore();
    }

    @Override
    public boolean isBlackjack() {
        boolean ret = player.isBlackjack();
        if(ret) {
            pl.onBlackjack();
        }
        return ret;
    }

    @Override
    public boolean isBust() {
        boolean ret = player.isBust();
        if(ret) {
            pl.onBust();
        }
        return ret;
    }

    @Override
    public void receive(Card card, boolean shown) {
        player.receive(card, shown);
        pl.onReceiveCard(card, shown);
    }

    @Override
    public boolean stand() {
        pl.onPreDecision();
        boolean ret = player.stand();
        pl.onPostDecision(ret);
        return ret;
    }
    
}
