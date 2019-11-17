package fr.utt.lo02.xfmv.jest.model.joueurs;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Joueur {

	protected int id;
	protected LinkedList<Carte> main;
	protected ArrayList<Carte> jest;
	protected String nom;

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
		return 0; //va appeller soit faireOffre de virtuel ou réel
		
	}
	
	public void prendreOffre() { //va appeller soit prendrOffre de virtuel ou réel
		
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
