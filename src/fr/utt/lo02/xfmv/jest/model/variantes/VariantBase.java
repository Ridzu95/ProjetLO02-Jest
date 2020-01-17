package fr.utt.lo02.xfmv.jest.model.variantes;

import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.card.Card;
import fr.utt.lo02.xfmv.jest.model.card.Trophies;

/**
 * Official rules of the Jest game. Refer to the game rules to understand the attributions here.
 */

public class VariantBase implements Variant {
	
	public void showTrophies(ArrayList<Card> trophies) {
        System.out.println("Les trophées pour cette partie sont :");
        for(Card card : trophies) {
            System.out.println(card.toString() + " --- " + card.getTrophy().getDescription());
        }
    }
	
	public void enableTrophies(ArrayList<Card> trophies) {
		// TODO Auto-generated method stub
		for (Card card : trophies) {
			switch(card.toString()) {
				case "2♥":
				case "4♥":
				case "3♥":
				case "1♥":
					card.setTrophy(Trophies.Joker);
					break;
				case "2♦":
					card.setTrophy(Trophies.HighestCarreau);
					break;
				case "3♦":
					card.setTrophy(Trophies.LowestCarreau);
					break;
				case "4♦":
					card.setTrophy(Trophies.BestJestNoJoker);
					break;
				case "1♦":
					card.setTrophy(Trophies.MajorityQuatre);
					break;
				case "2♣":
					card.setTrophy(Trophies.LowestCoeur);
					break;
				case "3♣":
					card.setTrophy(Trophies.HighestCoeur);
					break;
				case "4♣":
					card.setTrophy(Trophies.LowestPique);
					break;
				case "1♣":
					card.setTrophy(Trophies.HighestPique);
					break;
				case "2♠":
					card.setTrophy(Trophies.MajorityTrois);
					break;
				case "3♠":
					card.setTrophy(Trophies.MajorityDeux);
					break;
				case "4♠":
					card.setTrophy(Trophies.LowestTrefle);
					break;
				case "1♠":
					card.setTrophy(Trophies.HighestTrefle);
					break;
				case "★":
					card.setTrophy(Trophies.BestJest);
					break;
			}
		}
	}
	
	
}
