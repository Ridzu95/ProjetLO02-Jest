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
		joueurs = new ArrayList<Joueur>();
		this.tour = 1;
	}
	
	private static Partie partie = new Partie();

	public static Partie getInstance() {
		return partie;
	}

	public void initialiserPartie() {

		for (Couleurs couleur : Couleurs.values()) {
			for (Valeurs valeur : Valeurs.values()) {
				if (couleur != Couleurs.Joker && valeur != Valeurs.Joker) {
					this.pioche.add(new Carte(valeur, couleur));
				}
			}
		}

		this.pioche.add(new Carte(Valeurs.Joker, Couleurs.Joker));

		JoueurReel player = new JoueurReel(1, Console.playerUsernameChoice());
		JoueurVirtuel bot1 = new JoueurVirtuel(1);
		JoueurVirtuel bot2 = new JoueurVirtuel(1);

		this.joueurs.add(player);
		this.joueurs.add(bot1);
		this.joueurs.add(bot2);

		this.distribuerCartes();
		this.jouerPartie();

		return;
	}
	
	public void distribuerCartes() {
		Collections.shuffle(this.pioche);

		if (this.tour == 1) {

			this.tropheesPartie.add(this.pioche.poll());
			this.tropheesPartie.add(this.pioche.poll());

			this.activerTrophees();

			for (Joueur i : joueurs) {
				i.getMain().add(this.pioche.poll());
				i.getMain().add(this.pioche.poll());
			}

			return ;
		}
		else {

			for(Joueur i : joueurs) {
				this.pioche.add(i.getMain().poll());
			}

			Collections.shuffle(this.pioche);

			for (Joueur i : joueurs) {
				i.getMain().add(this.pioche.poll());
				i.getMain().add(this.pioche.poll());
			}

			return ;
		}
	}

	public void jouerPartie() {
		
		this.choisirCarteCachee();
		Console.showBoard(this.joueurs);
		
		return;
	}

	/* méthode qui permet à chaque joueur de cacher une carte de sa main */
	public void choisirCarteCachee() {
		for(Joueur joueur : joueurs) {
			joueur.getMain().get(joueur.faireOffre()).setVisible(false);
			
			if(joueur instanceof JoueurReel) {
				Console.tellHiddenCard(joueur);
			}
			
			
		}
	}
	
	public void declarerVainqueur() { //est appellé en fin de partie
		
	}
	
	
	
	
	/* getter setter */

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

	public LinkedList<Carte> getPioche() {
		return pioche;
	}

	public void setPioche(LinkedList<Carte> pioche) {
		this.pioche = pioche;
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public void activerTrophees() {

		for (Carte carte : tropheesPartie) {

		}

		return;

	}
}
