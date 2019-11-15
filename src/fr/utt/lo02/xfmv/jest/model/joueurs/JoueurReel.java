package fr.utt.lo02.xfmv.jest.model.joueurs;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class JoueurReel extends Joueur {
	
	private String pseudo;

	public JoueurReel() {
		super();
		this.pseudo = "null";
	}

	public JoueurReel(int id, String username) {
		super();
		this.id = id;
		this.pseudo = username;
		this.main = new ArrayList<Carte>();
		this.jest = new ArrayList<Carte>();
	}

	public void faireOffre() {
		
	}
	
	public void prendreOffre() {
		
	}
	
	public String toString(){
	      return  pseudo;
	}
}
