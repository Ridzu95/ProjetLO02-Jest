package fr.utt.lo02.xfmv.jest.model.joueurs;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Joueur implements Comparable<Joueur> {

	protected int id;
	protected LinkedList<Carte> main;
	protected ArrayList<Carte> jest;
	protected String nom;
	protected boolean hasPlayed;

	public Joueur() {
		this.id = 0;
		this.hasPlayed = false;
	}

	public Joueur(int id) {
		this.id = id;
		this.hasPlayed = false;
	}

	public LinkedList<Carte> getMain() {
		return this.main;
	}
	
	public int faireOffre() {
		return 0; //va appeller soit faireOffre de virtuel ou réel
		
	}
	
	public Carte prendreOffre(ArrayList<Carte> selectCards) { //va appeller soit prendrOffre de virtuel ou réel
		return this.getMain().get(0);
	}

	public Carte getVisibleCard() {
		return (this.getMain().get(0).isVisible() ? this.getMain().get(0) : this.getMain().get(1));
	}

	public boolean getHasPlayed() {
		return this.hasPlayed;
	}

	public int getId() {
		return id;
	}

	@Override
	public int compareTo(Joueur joueur) {

		int sorter = joueur.getVisibleCard().getValeur().getValeur() - this.getVisibleCard().getValeur().getValeur();
		if (sorter == 0) {
			sorter = joueur.getVisibleCard().getCouleur().getOrdre() - this.getVisibleCard().getCouleur().getOrdre();
			return sorter;
		}
		else {
			return sorter;
		}

	}
	
	public Carte getCarteVisible() {
		
		Carte carteVisible = null;
		for(Carte carte : this.getMain()) {
            if (carte.isVisible() == true) {
            	carteVisible = carte;
            }
        }
		
		return carteVisible;
	}
	
	public Carte getCarteCachee() {
		
		Carte carteCachee = null;
		for(Carte carte : this.getMain()) {
            if (carte.isVisible() == false) {
            	carteCachee = carte;
            }
        }
		
		return carteCachee;
	}
	
}
