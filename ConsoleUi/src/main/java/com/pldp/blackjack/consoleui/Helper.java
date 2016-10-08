package com.pldp.blackjack.consoleui;

import com.pldp.blackjack.Rules;
import com.pldp.blackjack.api.Hand;
import com.pldp.cards.StandardDeck;
import com.pldp.util.console.Out;

public class Helper {

    public static void describeHand(Out out, String desc, Rules rules, Hand h) {
        out.println(desc);
        h.getVisibleCards().forEach(x -> out.println(String.format("\t - %s", StandardDeck.describeCard(x))));
        if (h.hasHiddenCards()) {
            out.println("\t - Hole Card");
        }
        out.println(String.format("Score: %d.", rules.score(h)));
    }

    public static void describeResult(Out out, Rules.Result r) {
        out.println(String.format("Result: you %s.", r.toString()));
    }
}
