package com.pldp.cards;

public class StandardDeck {
    public static enum Suit {
        Clubs,
        Diamonds,
        Hearts,
        Spades
    }
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 13;
    public static final int NUM_CARDS = (MAX_VALUE + 1 - MIN_VALUE) * Suit.values().length;
    /**
     * Add a standard french card set to a deck.
     * @param deck deck to be modified
     */
    public static void genStandardDeck(Deck deck) {
        for(Suit s: Suit.values()) {
            for(int i=MIN_VALUE; i <= MAX_VALUE; ++i) {
                deck.add(new Card(s.ordinal(), i));
            }
        }
    }
    
    /**
     * Card in human readable form.
     * @param c The card.
     * @return String in the form "{Value} of {Suit}"
     */
    public static String describeCard(Card c) {
        StringBuilder sb = new StringBuilder();
        sb.append(nameForValue(c.getValue()));
        sb.append(" of ");
        sb.append(nameForSuit(c.getSuit()));
        return sb.toString();
    }
    private static final String NAMES[]={"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    private static String nameForValue(int v) {
        return NAMES[v - 1];
    }
    private static String nameForSuit(int suit) {
        return StandardDeck.Suit.values()[suit].name();
    }
}
