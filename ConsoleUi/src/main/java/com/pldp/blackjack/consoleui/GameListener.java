package com.pldp.blackjack.consoleui;

import com.pldp.blackjack.Rules;
import com.pldp.blackjack.api.Player;
import com.pldp.cards.Card;

public interface GameListener {
    void onStartDealPhase(Player player);
    void onEndDealPhase(Player player);
    void onGiveCard(Player player, Card card);
    void onGiveHiddenCard(Player player);
    void onShuffleCards(Player player);
    void onTurnStart(Player player);
    void onHit(Player player);
    void onStand(Player player);
    void onFlip(Player player, Card card);
    void onTurnEnd(Player player);
    void onResult(Player player, Rules.Result result);
}
