package com.epam.rd.autocode.startegy.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TexasHoldemCardDealingStrategy implements CardDealingStrategy {
    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> cardsStacks = new HashMap<>();

        for (int i = 0; i < players; i++) {
            cardsStacks.put("Player " + (i + 1), new ArrayList<>());
        }

        cardsStacks.put("Community", new ArrayList<>());

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < players; j++) {
                cardsStacks.get("Player " + (j + 1)).add(deck.dealCard());
            }
        }

        for (int i = 0; i < 5; i++) {
            cardsStacks.get("Community").add(deck.dealCard());
        }

        List<Card> remaining = deck.restCards();
        if (!remaining.isEmpty())
            cardsStacks.put("Remaining", remaining);

        return cardsStacks;
    }
}