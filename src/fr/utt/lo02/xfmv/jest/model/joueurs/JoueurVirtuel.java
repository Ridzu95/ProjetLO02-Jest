package fr.utt.lo02.xfmv.jest.model.joueurs;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.strategie.Strategie;

import java.util.ArrayList;
import java.util.LinkedList;

public class JoueurVirtuel extends Joueur implements Strategie {
	
	// private Strategie strategie;
	private int niveau;
	
	public JoueurVirtuel(int niveau, int id) {
		super(id);
		this.niveau = niveau;
	}
	
	/*L'offre se fait aléatoirement en renvoyant un entier entre 0 et 1 */
	public int faireOffre() {
		return (int) ( Math.random() + 0.5 ) ;
	}
	
	public int prendreOffre(ArrayList<Carte> selectCards) {
		return (int) Math.round(Math.random() * (selectCards.size() - 1));
	}
	
	public String toString(){
	      return  "Bot " + this.id;
	}


	public void executerStrategie() {
		// TODO Auto-generated method stub
		
	}
}
