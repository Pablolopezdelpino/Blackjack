package com.pldp.blackjack.consoleui;

import com.pldp.blackjack.api.Dealer;
import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.api.Player;
import com.pldp.cards.Card;

public class ObservedPlayerImpl implements Player {
    private final Player player;
    private final GameListener gl;
    public ObservedPlayerImpl(GameListener gl, Player p) {
        this.player = p;
        this.gl = gl;
    }
    @Override
    public Hand getHand() {
        return player.getHand();
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
    public void onTurn(Dealer dealer) {
        gl.onTurnStart(player);
        player.onTurn(dealer);
        gl.onTurnEnd(player);
    }
    
}
