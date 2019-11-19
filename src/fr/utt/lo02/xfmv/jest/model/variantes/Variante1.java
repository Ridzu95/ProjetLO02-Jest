package fr.utt.lo02.xfmv.jest.model.variantes;

import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.cartes.Trophees;

public class Variante1 implements Variante {

	@Override
	public void activerTrophees(ArrayList<Carte> trophees) {
		for (Carte carte : trophees) {
			carte.setTrophee(Trophees.getRandomTrophee());
		}
		
	}

	

}
