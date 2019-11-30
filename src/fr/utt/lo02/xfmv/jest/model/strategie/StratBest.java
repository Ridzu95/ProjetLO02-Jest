package fr.utt.lo02.xfmv.jest.model.strategie;

import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;

public class StratBest implements Strategie{

	@Override
	public int executerStrategie(ArrayList<Carte> selectCards) {
		// TODO Auto-generated method stub
		
		Carte carteChoisie = new Carte();//on créer la carte qui sera renvoyée
		
		carteChoisie = selectCards.get(0); //par défaut on va mettre la première carte
		for (Carte carte : selectCards) {
			if (carte.isVisible() == true) {
				
				if (carte.getValeur().getValeur() > carteChoisie.getValeur().getValeur() ) {
					carteChoisie = carte;
				}
 				
			}
				
			
		}
		return selectCards.indexOf(carteChoisie);
	}

}
