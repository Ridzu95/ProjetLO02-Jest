package fr.utt.lo02.xfmv.jest.model.strategie;

import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;

public class StratBest implements Strategie{

	//le bot va choisir la meilleur carte en comparant leurs valeur
	public int executerStrategie(ArrayList<Carte> selectCards) {
		
		
		Carte carteChoisie = new Carte();//on créer la carte qui sera renvoyée
		
		carteChoisie = selectCards.get(0); //par défaut on va mettre la première carte
		for (Carte carte : selectCards) {
			if (carte.isVisible() == true) {		
				if (carte.getValeur().getValeur() > carteChoisie.getValeur().getValeur() ) {
					carteChoisie = carte;
				} //si pas respecté on garde la carte choisie par défaut
			}
		}
		return selectCards.indexOf(carteChoisie);
	}

}
