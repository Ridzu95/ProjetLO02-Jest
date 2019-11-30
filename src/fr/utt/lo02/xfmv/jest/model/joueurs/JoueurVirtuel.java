package fr.utt.lo02.xfmv.jest.model.joueurs;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.strategie.StratAleatoire;
import fr.utt.lo02.xfmv.jest.model.strategie.StratBest;
import fr.utt.lo02.xfmv.jest.model.strategie.Strategie;


import java.util.ArrayList;
import java.util.LinkedList;

public class JoueurVirtuel extends Joueur{
	
	private Strategie strategie;
	
	public JoueurVirtuel(int id, int choicestrat) {
		super(id);
		if (choicestrat == 2) {
			Strategie strat = new StratBest();
			this.strategie = strat;
		} else {
			Strategie strat = new StratAleatoire();
			this.strategie = strat;
		}
		
		
	}
	
	public JoueurVirtuel(int id, Strategie strategie) {
		super(id);
		this.strategie = strategie;
	}
	
	/*L'offre se fait aléatoirement en renvoyant un entier entre 0 et 1 */
	public int faireOffre() {
		return (int) (Math.random() + 0.5) ;
	}
	
	public int prendreOffre(ArrayList<Carte> selectCards) {
		return this.strategie.executerStrategie(selectCards);
	}
	
	public String toString(){
	      return  "Bot " + this.id;
	}

}
