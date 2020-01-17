package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.card.Card;
import fr.utt.lo02.xfmv.jest.model.card.Colors;
import fr.utt.lo02.xfmv.jest.model.card.Values;
import fr.utt.lo02.xfmv.jest.model.player.Player;

/**
 * Basic Jest points counter based off Jest game official rules.
 * Implements Counter interface.
 */

public class JestCounterBase implements JestCounter {

    @Override

    /**
     * Jest-counting method.
     * Handles a player's Jest cards to checks some parameters useful to count the score.
     */
    public void countJest(Player player) {

        int score = 0;
        int heartCounter = 0;
        int asCounter = 0;
        boolean hasJoker = false;

        for (Card cardTest : player.getJest()) {
            if (cardTest.getColor() == Colors.Heart) {
                heartCounter++;
            }
            if (cardTest.getValue() == Values.Joker) {
                hasJoker = true;
            }
        }

        if (hasJoker == true && heartCounter == 0) {
            score += 4;
        }

        for (Card card : player.getJest()) {

            int cardValue = 0;
            boolean onlyAceInThatSuit = true;

            if (card.getValue() == Values.As) {
                for (Card cardTestAs : player.getJest()) {
                    if (cardTestAs.getColor() == card.getColor()) {
                        onlyAceInThatSuit = false;
                    }
                }
                if (onlyAceInThatSuit) {
                    cardValue = 5;
                }
                else {
                    cardValue = 1;
                }
            }
            else {
                cardValue = card.getValue().getValeur();
            }

            if (card.getColor() == Colors.Club) {
                score += cardValue;
            }
            else if (card.getColor() == Colors.Diamond) {
                score -= cardValue;
            }
            else if (card.getColor() == Colors.Heart) {
                if (hasJoker == true) {
                    if (heartCounter == 4) {
                        score += cardValue;
                    }
                    else {
                        score -= cardValue;
                    }
                }
            }
            else if (card.getColor() == Colors.Spade) {
                for (Card cardTestPaire : player.getJest()) {
                    if (cardTestPaire.getColor() == Colors.Club && cardTestPaire.getValue() == card.getValue()) {
                        score += 2;
                    }
                }
                score += cardValue;
            }

            player.setScore(score);

        }

    }

}
