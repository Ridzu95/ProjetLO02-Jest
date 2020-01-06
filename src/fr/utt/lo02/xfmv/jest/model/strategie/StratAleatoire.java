package fr.utt.lo02.xfmv.jest.model.strategie;

import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;

public class StratAleatoire implements Strategie{


	public int executerStrategie(ArrayList<Carte> selectCards) {
		
		return (int) Math.round(Math.random() * (selectCards.size() - 1));

	}

}
