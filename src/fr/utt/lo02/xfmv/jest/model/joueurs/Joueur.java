package fr.utt.lo02.xfmv.jest.model.joueurs;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Joueur {

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
	
	public void faireOffre() { //va appeller soit faireOffre de virtuel ou réel
		
	}
	
	public void prendreOffre() { //va appeller soit prendrOffre de virtuel ou réel
		
	}
	
}
