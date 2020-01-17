package fr.utt.lo02.xfmv.jest.model.strategie;

import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.card.Card;

/**
 * Interface to implement different Strategies following the design pattern asked in the project subject.
 */

public interface Strategy {
	
	int executerStrategie(ArrayList<Card> selectCards);
}
