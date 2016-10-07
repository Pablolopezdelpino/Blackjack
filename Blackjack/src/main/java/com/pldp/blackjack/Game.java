package com.pldp.blackjack;

import com.pldp.blackjack.api.Dealer;
import com.pldp.blackjack.api.Player;
import java.util.Arrays;

public class Game {

    public Rules.Result run(Rules rules, Dealer dealer, Player player) {
        dealer.start(Arrays.asList(player));
        if (!rules.isBlackjack(player.getHand())) {
            player.onTurn(dealer);
            if (rules.isBust(player.getHand())) {
                return Rules.Result.lose;
            }
            dealer.onTurn(dealer);
            if (rules.isBust(dealer.getHand())) {
                return Rules.Result.win;
            }
        }
        dealer.flip();
        return rules.evaluate(player.getHand(), dealer.getHand());
    }
}
