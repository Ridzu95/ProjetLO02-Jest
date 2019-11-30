package fr.utt.lo02.xfmv.jest.model.variantes;


import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;

public interface Variante {
	
	public void showTrophies(ArrayList<Carte> trophees);
	
	public void activerTrophees(ArrayList<Carte> tropheesPartie);
	
}
