package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.cartes.Couleurs;
import fr.utt.lo02.xfmv.jest.model.cartes.Trophees;
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
					this.pioche.add(new Carte(valeur, couleur)); /** Boucles qui ajoutent les 16 cartes */
				}
			}
		}

		this.pioche.add(new Carte(Valeurs.Joker, Couleurs.Joker)); /** Le joker est particulier donc on le créé "à la main" */

		/** On ajoute les joueurs à une liste joueurs, qui servira à déterminer qui joue après qui au début du tour */
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

		/** Si c'est le premier tour, on pioche deux cartes pour les ajouter aux trophées, et on donne deux cartes à chaque joueur.
		 * Ensuite pour les autres tours, on récupère la carte qui reste dans la main de chaque joueur, on mélange, et on redistribue. */
		if (this.tour == 1) {

			Collections.shuffle(this.pioche);

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

	/** Méthode de déroulement des tours de jeu */
	public void jouerPartie() {
		this.choisirCarteCachee();
	}

	/* méthode qui permet à chaque joueur de cacher une carte de sa main */
	public void choisirCarteCachee() {

		for(Joueur joueur : joueurs) {
			Console.showTrophies(this.tropheesPartie);

			/** On utilise instanceof pour récupérer uniquement les joueurs réels, puisque les bots choisissent automatiquement. */
			for (Joueur joueur : joueurs) {
				if (joueur instanceof JoueurReel) {
					this.choisirCarteCachee((JoueurReel) joueur);
				}
			}

		}

		return;
	}

	public void choisirCarteCachee(JoueurReel joueur) {
		/** La première méthode à s'éxécuter est Console.cardChoice() qui renvoie l'index de la carte choisie, on récupère la référence sur la carte puis on la met cachée ! */
		joueur.getMain().get(Console.cardChoice(joueur)).setVisible(false);
		Console.tellHiddenCard(joueur);
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

	public LinkedList<Carte> getPioche() {
		return pioche;
	}

	public void setPioche(LinkedList<Carte> pioche) {
		this.pioche = pioche;
	}

	public void activerTrophees() {

		for (Carte carte : tropheesPartie) {
			switch(carte.toString()) {
				case "2♥":
				case "4♥":
				case "3♥":
				case "5♥":
					carte.setTrophee(Trophees.Joker);
					break;
				case "2♦":
					carte.setTrophee(Trophees.HighestCarreau);
					break;
				case "3♦":
					carte.setTrophee(Trophees.LowestCarreau);
					break;
				case "4♦":
					carte.setTrophee(Trophees.BestJestNoJoker);
					break;
				case "5♦":
					carte.setTrophee(Trophees.MajorityQuatre);
					break;
				case "2♣":
					carte.setTrophee(Trophees.LowestCoeur);
					break;
				case "3♣":
					carte.setTrophee(Trophees.HighestCoeur);
					break;
				case "4♣":
					carte.setTrophee(Trophees.LowestPique);
					break;
				case "5♣":
					carte.setTrophee(Trophees.HighestPique);
					break;
				case "2♠":
					carte.setTrophee(Trophees.MajorityTrois);
					break;
				case "3♠":
					carte.setTrophee(Trophees.MajorityDeux);
					break;
				case "4♠":
					carte.setTrophee(Trophees.LowestTrefle);
					break;
				case "5♠":
					carte.setTrophee(Trophees.HighestTrefle);
					break;
				case "★":
					carte.setTrophee(Trophees.BestJest);
					break;
			}
		}

		return;

	}
}
