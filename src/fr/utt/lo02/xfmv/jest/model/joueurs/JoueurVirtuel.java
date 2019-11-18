package fr.utt.lo02.xfmv.jest.model.joueurs;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.strategie.Strategie;

import java.util.ArrayList;
import java.util.LinkedList;

public class JoueurVirtuel extends Joueur implements Strategie {
	
	// private Strategie strategie;
	private int niveau;
	
	public JoueurVirtuel(int niveau) {
		super();
		this.niveau = niveau;
		this.main = new LinkedList<Carte>();
		this.jest = new ArrayList<Carte>();
	}
	
	/*L'offre se fait aléatoirement en renvoyant un entier entre 0 et 1 */
	public int faireOffre() {
		return (int) ( Math.random() + 0.5 ) ;
		
	}
	
	public void prendreOffre() {
		
	}
	
	public String toString(){
	      return  "Bot de niveau" + niveau;
	}


	public void executerStartegie() {
		// TODO Auto-generated method stub
		
	}
}
