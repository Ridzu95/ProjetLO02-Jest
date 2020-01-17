package fr.utt.lo02.xfmv.jest.model.strategie;

import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.card.Card;

/**
 * Strategy for bots where the bot select a random card in the cards he can choose.
 * Uses Math.random() to make his choice between 1 and 2 or 1, 2, 3 and 4.
 */

public class StratRandom implements Strategy {


	public int executerStrategie(ArrayList<Card> selectCards) {
		
		return (int) Math.round(Math.random() * (selectCards.size() - 1));

	}

}
