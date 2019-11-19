package fr.utt.lo02.xfmv.jest.model.variantes;

import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;

public class Variante1 implements Variante {

	public void activerTrophees() {
		
	}

	public ArrayList<Carte> getTrophees() {
		return null;
	}
	
	public String toString() {
		return "Cette variante attribue les trophées aléatoirement";
	}

}
