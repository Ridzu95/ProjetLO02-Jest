package fr.utt.lo02.xfmv.jest.model.variantes;


import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.card.Card;

/**
 * Interface to implement different variants of the game rules.
 */

public interface Variant {
	
	void showTrophies(ArrayList<Card> trophees);
	
	void enableTrophies(ArrayList<Card> tropheesPartie);
	
}
