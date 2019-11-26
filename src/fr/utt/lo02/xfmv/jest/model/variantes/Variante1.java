package fr.utt.lo02.xfmv.jest.model.variantes;

import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.cartes.Trophees;

public class Variante1 implements Variante {
	
	public void showTrophies(ArrayList<Carte> trophees) {
        System.out.println("Le(s) trophée(s) pour cette partie sont :");
        for(Carte carte : trophees) {
            System.out.println(carte.toString() + " --- " + carte.getTrophee().getDescription());
        }
    }
	
	public void activerTrophees(ArrayList<Carte> trophees) {
		for (Carte carte : trophees) {
			carte.setTrophee(Trophees.getRandomTrophee());
		}
		
	}

	

}
