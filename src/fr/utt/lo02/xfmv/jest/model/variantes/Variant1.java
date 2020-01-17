package fr.utt.lo02.xfmv.jest.model.variantes;

import java.util.ArrayList;

import fr.utt.lo02.xfmv.jest.model.card.Card;
import fr.utt.lo02.xfmv.jest.model.card.Trophies;

/**
 * First variant of the game where the trophies are enabled in random to the 2 trophy cards.
 * Originally, each card has a corresponding trophy, but in this variant, we pick a random trophy effect in the enum and associate it with the trophy cards.
 */

public class Variant1 implements Variant {
	
	public void showTrophies(ArrayList<Card> trophies) {
        System.out.println("Le(s) trophée(s) pour cette partie sont :");
        for(Card card : trophies) {
            System.out.println(card.toString() + " --- " + card.getTrophy().getDescription());
        }
    }
	
	//Variante 1 génères des trophées aléatoires grâce à la méthode getRandomTrophee de Trophees
	public void enableTrophies(ArrayList<Card> trophies) {
		for (Card card : trophies) {
			card.setTrophy(Trophies.getRandomTrophy());
		}
		
	}

	

}
