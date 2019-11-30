package fr.utt.lo02.xfmv.jest.model.strategie;

import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;

public interface Strategie {
	
	public int executerStrategie(ArrayList<Carte> selectCards);
}
