package fr.utt.lo02.xfmv.jest.model.variantes;

import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.cartes.Trophees;

public class Variantebase implements Variante{
	
	private ArrayList<Carte> trophees;

	
	
	public void activerTrophees() {
		// TODO Auto-generated method stub
		for (Carte carte : trophees) {
			switch(carte.toString()) {
				case "2♥":
				case "4♥":
				case "3♥":
				case "5♥":
					carte.setTrophee(Trophees.Joker);
					break;
				case "2♦":
					carte.setTrophee(Trophees.HighestCarreau);
					break;
				case "3♦":
					carte.setTrophee(Trophees.LowestCarreau);
					break;
				case "4♦":
					carte.setTrophee(Trophees.BestJestNoJoker);
					break;
				case "5♦":
					carte.setTrophee(Trophees.MajorityQuatre);
					break;
				case "2♣":
					carte.setTrophee(Trophees.LowestCoeur);
					break;
				case "3♣":
					carte.setTrophee(Trophees.HighestCoeur);
					break;
				case "4♣":
					carte.setTrophee(Trophees.LowestPique);
					break;
				case "5♣":
					carte.setTrophee(Trophees.HighestPique);
					break;
				case "2♠":
					carte.setTrophee(Trophees.MajorityTrois);
					break;
				case "3♠":
					carte.setTrophee(Trophees.MajorityDeux);
					break;
				case "4♠":
					carte.setTrophee(Trophees.LowestTrefle);
					break;
				case "5♠":
					carte.setTrophee(Trophees.HighestTrefle);
					break;
				case "★":
					carte.setTrophee(Trophees.BestJest);
					break;
			}
		}
	}

	public ArrayList<Carte> getTrophees() {
		return trophees;
		
	}
	
	public String toString() {
		return "Cette variante attribue les trophées selon les règles de base";
	}
	
	
	
}
