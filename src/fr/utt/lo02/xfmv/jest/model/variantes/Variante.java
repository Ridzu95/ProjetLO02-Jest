package fr.utt.lo02.xfmv.jest.model.variantes;

import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;

public interface Variante {
	
	public void activerTrophees();
	
	public ArrayList<Carte> getTrophees();
}
