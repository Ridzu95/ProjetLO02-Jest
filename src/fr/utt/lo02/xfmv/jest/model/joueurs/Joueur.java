package fr.utt.lo02.xfmv.jest.model.joueurs;

import fr.utt.lo02.xfmv.jest.controller.Compteur;
import fr.utt.lo02.xfmv.jest.model.cartes.Carte;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Joueur implements Comparable<Joueur> {

	protected int id;
	protected LinkedList<Carte> main;
	protected LinkedList<Carte> jest;
	protected boolean hasPlayed;
	protected int score;

	public Joueur() {
		this.id = 0;
		this.score = 0;
		this.hasPlayed = false;
		this.main = new LinkedList<Carte>();
		this.jest = new LinkedList<Carte>();
	}

	public Joueur(int id) {
		this.id = id;
		this.score = 0;
		this.hasPlayed = false;
		this.main = new LinkedList<Carte>();
		this.jest = new LinkedList<Carte>();
	}

	public LinkedList<Carte> getMain() {
		return this.main;
	}
	
	public int faireOffre() {
		return 0; //va appeller soit faireOffre de virtuel ou réel
		
	}

	public void setHasPlayed(boolean hasPlayed) {
		this.hasPlayed = hasPlayed;
	}

	public LinkedList<Carte> getJest() {
		return jest;
	}

	public int prendreOffre(ArrayList<Carte> selectCards) { //va appeller soit prendrOffre de virtuel ou réel
		return 0;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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

	public void accept(Compteur compteur) {
		compteur.compterJests(this);
	}
	
	
	
}
