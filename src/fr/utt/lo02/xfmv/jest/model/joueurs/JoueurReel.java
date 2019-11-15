package fr.utt.lo02.xfmv.jest.model.joueurs;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class JoueurReel extends Joueur {
	
	private String pseudo;

	public JoueurReel() {
		super();
		this.pseudo = "Joueur";
	}

	public JoueurReel(int id, String username) {
		super();
		this.id = id;
		this.pseudo = username;
		this.main = new LinkedList<Carte>();
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
