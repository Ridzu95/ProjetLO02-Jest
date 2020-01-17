package fr.utt.lo02.xfmv.jest.model.strategie;

import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.card.Card;

/**
 * Strategy for bots where the bot select the best visible card he can choose every time.
 */

public class StratBest implements Strategy {

	@Override
	public int executerStrategie(ArrayList<Card> selectCards) {
		// TODO Auto-generated method stub
		
		Card cardChoisie = new Card();//on créer la carte qui sera renvoyée
		
		cardChoisie = selectCards.get(0); //par défaut on va mettre la première carte
		for (Card card : selectCards) {
			if (card.isVisible() == true) {
				
				if (card.getValue().getValeur() > cardChoisie.getValue().getValeur() ) {
					cardChoisie = card;
				}
 				
			}
				
			
		}
		return selectCards.indexOf(cardChoisie);
	}

}
