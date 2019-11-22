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

import java.lang.reflect.Array;
import java.util.*;

public class Partie implements Variante {

	private LinkedList<Carte> basePioche;
	private LinkedList<Carte> tempPioche;
	private ArrayList<Carte> tropheesPartie;
	private ArrayList<Joueur> joueurs;
	private int tour;

	private Partie() {
		basePioche = new LinkedList<Carte>();
		tropheesPartie = new ArrayList<Carte>();
		joueurs = new ArrayList<Joueur>();
		tempPioche = new LinkedList<Carte>();
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

		JoueurReel player = new JoueurReel(1, Console.playerUsernameChoice());
		JoueurVirtuel bot1 = new JoueurVirtuel(1, 2);
		JoueurVirtuel bot2 = new JoueurVirtuel(1, 3);

		this.joueurs.add(player);
		this.joueurs.add(bot1);
		this.joueurs.add(bot2);

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

			this.activerTrophees();

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
			this.distribuerCartes();
			Console.showTurn(this.tour);
			this.choisirCarteCachee();
			Console.displayPlayerCards(joueurs);
			Collections.sort(joueurs);
			this.controlOffers();
			this.tour++;
		} while (basePioche.size() != 0);

		return;
	}

	public void controlOffers() {

		Joueur choosingPlayer = joueurs.get(0);
		boolean everyonePlayed = false;

		while (everyonePlayed == false) {

			System.out.println();

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

	/* méthode qui permet à chaque joueur de cacher une carte de sa main */
	public void choisirCarteCachee() {

		for(Joueur joueur : joueurs) {
			joueur.setHasPlayed(false);
			joueur.getMain().get(joueur.faireOffre()).setVisible(false);
		}

		return;
	}

	public void declarerVainqueur() { //est appellé en fin de partie
		
	}

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
