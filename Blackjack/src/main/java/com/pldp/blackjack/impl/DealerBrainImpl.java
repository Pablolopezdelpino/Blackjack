package com.pldp.blackjack.impl;

import com.pldp.blackjack.Rules;
import com.pldp.blackjack.api.PlayerBrain;

public class DealerBrainImpl implements PlayerBrain {
    private final Rules rules;
    public DealerBrainImpl(Rules rules) {
        this.rules = rules;
    }
    
    @Override
    public boolean shouldPass(int score) {
        return rules.dealerShouldPass(score);
    }
}
