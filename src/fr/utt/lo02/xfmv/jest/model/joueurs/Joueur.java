package fr.utt.lo02.xfmv.jest.model.joueurs;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Joueur implements Comparable<Joueur> {

	protected int id;
	protected LinkedList<Carte> main;
	protected ArrayList<Carte> jest;

	public Joueur() {
		this.id = 0;
	}

	public Joueur(int id) {
		this.id = id;
	}

	public LinkedList<Carte> getMain() {
		return this.main;
	}
	
	public int faireOffre() {
		return id; //va appeller soit faireOffre de virtuel ou réel
		
	}
	
	public void prendreOffre() { //va appeller soit prendrOffre de virtuel ou réel
		
	}

	public Carte getVisibleCard() {
		return (this.getMain().get(0).isVisible() ? this.getMain().get(0) : this.getMain().get(1));
	}

	@Override
	public int compareTo(Joueur joueur) {

		int sorter = this.getVisibleCard().getValeur().getValeur() - joueur.getVisibleCard().getValeur().getValeur();
		if (sorter == 0) {
			sorter = this.getVisibleCard().getCouleur().getOrdre() - joueur.getVisibleCard().getCouleur().getOrdre();
			return sorter;
		}
		else {
			return sorter;
		}

	}
	
}
