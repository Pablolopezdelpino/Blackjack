package com.pldp.blackjack.consoleui.impl;

import com.pldp.blackjack.api.PlayerBrain;
import com.pldp.util.console.In;

public class PlayerBrainImpl implements PlayerBrain {
    private final In in;
    public PlayerBrainImpl(In in) {
        this.in = in;
    }
    
    @Override
    public boolean shouldPass(int score) {
        return in.questionYN("Should you stand?");
    }
    
}
