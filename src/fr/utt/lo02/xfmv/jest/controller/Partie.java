package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.cartes.Couleurs;
import fr.utt.lo02.xfmv.jest.model.cartes.Valeurs;
import fr.utt.lo02.xfmv.jest.model.joueurs.Joueur;
import fr.utt.lo02.xfmv.jest.model.joueurs.JoueurReel;
import fr.utt.lo02.xfmv.jest.model.joueurs.JoueurVirtuel;
import fr.utt.lo02.xfmv.jest.model.variantes.Variante;
import fr.utt.lo02.xfmv.jest.vue.console.Console;

import java.util.*;

public class Partie implements Variante {

	private int nbJoueurs;
	// private Variante variante;
	private LinkedList<Carte> pioche;
	private ArrayList<Carte> tropheesPartie;
	private ArrayList<Joueur> joueurs;
	private int tour;

	private Partie() {
		pioche = new LinkedList<Carte>();
		tropheesPartie = new ArrayList<Carte>();
		this.tour = 1;
	}
	
	private static Partie partie = new Partie();

	public static Partie getInstance() {
		return partie;
	}

	public void debuterPartie() {

		for (Couleurs couleur : Couleurs.values()) {
			for (Valeurs valeur : Valeurs.values()) {
				this.pioche.add(new Carte(valeur, couleur));
			}
		}

		JoueurReel player = new JoueurReel(1, Console.playerUsernameChoice());
		JoueurVirtuel bot1 = new JoueurVirtuel(1);
		JoueurVirtuel bot2 = new JoueurVirtuel(1);
		joueurs.add(player);
		joueurs.add(bot1);
		joueurs.add(bot2);

		this.distribuerCartes();


		return;
	}
	
	public void distribuerCartes() {
		Collections.shuffle(pioche);

		if (this.tour == 1) {

			this.tropheesPartie.add(this.pioche.poll());
			this.tropheesPartie.add(this.pioche.poll());

			for (Joueur i : joueurs) {
				i.getMain().add(this.pioche.poll());
				i.getMain().add(this.pioche.poll());
			}

			return ;
		}
		else {
			return ;
		}
	}
	
	public void terminerTour() { 
		
	}
	
	public void declarerVainqueur() { //est appellé en fin de partie
		
	}

	public int getTour() {
		return this.tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public int getNbJoueurs() {
		return this.nbJoueurs;
	}

	public ArrayList<Carte> getPioche() {
		return pioche;
	}

	public void setPioche(ArrayList<Carte> pioche) {
		this.pioche = pioche;
	}

	public void addToPioche(Carte carte) {
		this.pioche.add(carte);
	}
}
