package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.cartes.Couleurs;
import fr.utt.lo02.xfmv.jest.model.cartes.Valeurs;
import fr.utt.lo02.xfmv.jest.model.joueurs.Joueur;
import fr.utt.lo02.xfmv.jest.model.joueurs.JoueurReel;
import fr.utt.lo02.xfmv.jest.model.joueurs.JoueurVirtuel;
import fr.utt.lo02.xfmv.jest.model.variantes.Variante;
import fr.utt.lo02.xfmv.jest.model.variantes.Variante1;
import fr.utt.lo02.xfmv.jest.model.variantes.Variante2;
import fr.utt.lo02.xfmv.jest.model.variantes.Variantebase;
import fr.utt.lo02.xfmv.jest.vue.console.Console;

import java.util.*;

public class Partie {

	private Variante variante;
	private LinkedList<Carte> basePioche;
	private LinkedList<Carte> tempPioche;
	private ArrayList<Carte> tropheesPartie;
	private ArrayList<Joueur> joueurs;
	private int tour;

	private Partie() {
		basePioche = new LinkedList<Carte>();
		tempPioche = new LinkedList<Carte>();
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
					this.basePioche.add(new Carte(valeur, couleur));
				}
			}
		}

		this.basePioche.add(new Carte(Valeurs.Joker, Couleurs.Joker));
		
		//Création des joueurs
		
		int temp1 = Console.demanderNombreJoueurs();
		int temp2 = Console.demanderJoueursReels(temp1);
		
		for ( int i = 0; i < temp2 ; i++ ) {
			JoueurReel player = new JoueurReel(i, Console.playerUsernameChoice(i+1));
			this.joueurs.add(player);
		}
		
		for ( int i = 0; i < temp1 - temp2 ; i++ ) {
			JoueurVirtuel bot = new JoueurVirtuel(i,Console.demanderStrategie(i));
			this.joueurs.add(bot);
		}
		
		/* Choix de la variante avec le choix de l'utilisateur */
		
		int choixVariante = Console.demanderVariante();
		
		if ( choixVariante == 1) {
			this.variante = new Variantebase();
		} else if ( choixVariante == 2) {
			this.variante = new Variante1();
		} else {
			this.variante = new Variante2();
		}
		
		
		Collections.shuffle(this.basePioche);
		this.jouerPartie();

		return;
	}
	
	public void distribuerCartes() {

		if (this.tour == 1) {


			this.tropheesPartie.add(this.basePioche.poll());

			if (joueurs.size() == 3) {
				this.tropheesPartie.add(this.basePioche.poll());
			}

			this.variante.activerTrophees(this.tropheesPartie);
			this.variante.showTrophies(tropheesPartie);


			for (Joueur i : joueurs) {
				i.getMain().add(this.basePioche.poll());
				i.getMain().add(this.basePioche.poll());
			}

			return ;
		}
		else {

			for(Joueur i : joueurs) {
				if (i.getMain().get(0) == null) {
					this.tempPioche.add(i.getMain().pollLast());
				}
				else {
					this.tempPioche.add(i.getMain().pollFirst());
				}
			}

			for (Joueur i : joueurs) {
				this.tempPioche.add(this.basePioche.poll());
			}

			for (Carte carte : tempPioche) {
				carte.setVisible(true);
			}

			Collections.shuffle(this.tempPioche);

			for (Joueur i : joueurs) {
				i.getMain().add(this.tempPioche.poll());
				i.getMain().add(this.tempPioche.poll());
			}

			return ;
		}
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void jouerPartie() {

		do {
			this.distribuerCartes(); //on redistribue les mains
			Console.showTurn(this.tour); 
			this.choisirCarteCachee(); //chacun choisis la carte qu'il veut cacher
			Console.displayPlayerCards(joueurs); //montre les mains des joueurs
			Collections.sort(joueurs); //trie la collection des joueurs
			this.controlOffers(); //méthode qui permet aux joueurs de faire leurs offres
			this.tour++;
		} while (basePioche.size() != 0);

		return;
	}
	
	public void controlOffers() {

		Joueur choosingPlayer = joueurs.get(0);
		boolean everyonePlayed = false;

		while (everyonePlayed == false) {


			ArrayList<Carte> selectCards = new ArrayList<Carte>();
			ArrayList<Joueur> selectJoueurs = new ArrayList<Joueur>();
			for (Joueur joueur : joueurs) {
				if (joueur.getMain().size() == 2 && joueur != choosingPlayer) {
					for (Carte carte : joueur.getMain()) {
						selectCards.add(carte);
						selectJoueurs.add(joueur);
					}
				}
			}
			if (selectCards.size() == 0) {
				for (Carte carte : choosingPlayer.getMain()) {
					selectCards.add(carte);
					selectJoueurs.add(choosingPlayer);
				}
			}

			int choice = choosingPlayer.prendreOffre(selectCards);

			if (choice % 2 == 0) {
				choosingPlayer.getJest().add(selectJoueurs.get(choice).getMain().pollFirst());
			}
			else {
				choosingPlayer.getJest().add(selectJoueurs.get(choice).getMain().pollLast());
			}

			System.out.println("Le joueur " + choosingPlayer.toString() + " a mis la carte " + selectCards.get(choice) + " dans son Jest !");
			System.out.println("");

			choosingPlayer.setHasPlayed(true);
			choosingPlayer = selectJoueurs.get(choice);

			if (choosingPlayer.getHasPlayed() == true) {
				int a = 0;
				while (a < joueurs.size() && joueurs.get(a).getHasPlayed() == true) {
					a++;
				}
				if (a > joueurs.size() - 1) {
					everyonePlayed = true;
				}
				else {
					choosingPlayer = joueurs.get(a);
				}
			}

		}



	}

	// méthode qui permet à chaque joueur de cacher une carte de sa main 
	public void choisirCarteCachee() {

		for(Joueur joueur : joueurs) {
			joueur.setHasPlayed(false);
			joueur.getMain().get(joueur.faireOffre()).setVisible(false);
		}

		return;
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


	public LinkedList<Carte> getBasePioche() {
		return basePioche;
	}
	
	public LinkedList<Carte> getTempPioche() {
		return tempPioche;
	}

	public ArrayList<Carte> getTropheesPartie() {
		return tropheesPartie;
	}

}
