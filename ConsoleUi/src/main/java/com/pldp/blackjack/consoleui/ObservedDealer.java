package com.pldp.blackjack.consoleui;

import com.pldp.blackjack.api.Hand;
import com.pldp.blackjack.api.Player;
import com.pldp.blackjack.impl.DealerImpl;
import com.pldp.cards.Card;

public class ObservedDealer extends DealerImpl {
    private final DealerListener gl;
    public ObservedDealer(DealerListener gl, Hand hand, Player player) {
        super(hand, player);
        this.gl = gl;
    }
    
    @Override
    public Card flip() {
        Card card = super.flip();
        gl.onFlip(card);
        return card;
    }
}
