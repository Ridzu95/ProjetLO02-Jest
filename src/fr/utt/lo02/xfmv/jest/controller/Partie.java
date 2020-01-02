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
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.GameConfig;

import java.lang.reflect.Array;
import java.util.*;

public class Partie extends Observable implements Runnable {

	private Variante variante;
	private LinkedList<Carte> basePioche;
	private LinkedList<Carte> tempPioche;
	private ArrayList<Carte> tropheesPartie;
	private ArrayList<Joueur> joueurs;
	private int tour;
	private boolean isStarted;
	private boolean isSetup;
	private boolean hidingPhasePlayed;
	private int playerCount;
	private int realPlayerCount;
	private String gamePhase;
	private boolean jestingPhasePlayed;

	private Partie() {
		basePioche = new LinkedList<Carte>();
		tempPioche = new LinkedList<Carte>();
		tropheesPartie = new ArrayList<Carte>();
		joueurs = new ArrayList<Joueur>();
		tempPioche = new LinkedList<Carte>();
		this.tour = 1;
		isStarted = false;
		isSetup = false;
		playerCount = 0;
		realPlayerCount = 0;
		gamePhase = "init";
		hidingPhasePlayed = false;
		jestingPhasePlayed = false;
	}
	
	private static Partie partie = new Partie();

	public static Partie getInstance() {
		return partie;
	}

	public void initialiserPartie() throws InterruptedException {

		for (Couleurs couleur : Couleurs.values()) {
			for (Valeurs valeur : Valeurs.values()) {
				if (couleur != Couleurs.Joker && valeur != Valeurs.Joker) {
					this.basePioche.add(new Carte(valeur, couleur));
				}
			}
		}

		this.basePioche.add(new Carte(Valeurs.Joker, Couleurs.Joker));
		
		//Création des joueurs

		do {
			Thread.sleep(500);
		} while (!this.isSetup());
		
		for ( int i = 0; i < realPlayerCount ; i++ ) {
		    this.joueurs.add(new JoueurReel(i, "realPlayer"));
		}
		
		for ( int i = 0; i < (playerCount - realPlayerCount) ; i++ ) {
		    this.joueurs.add(new JoueurVirtuel(i,1));
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
			this.variante.showTrophies(this.tropheesPartie);


			for (Joueur i : joueurs) {
				i.getMain().add(this.basePioche.poll());
				i.getMain().add(this.basePioche.poll());
				if (i instanceof JoueurVirtuel) {
					for (Carte card : i.getMain()) {
						card.setVisible(false);
					}
				}
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

	public void jouerPartie() throws InterruptedException {

		do {

			this.distribuerCartes();

			this.gamePhase = "sélection de la carte à cacher";

			this.setChanged();
			this.notifyObservers();

			// Console.showTurn(this.tour);
			// Console.displayPlayerCards(joueurs);

			/**
			do {
				Thread.sleep(500);
			} while (!this.hidingPhasePlayed);
			**/

			this.choisirCarteCachee();

			this.gamePhase = "sélection de la carte à mettre dans le Jest";
			Collections.sort(joueurs);

			this.setChanged();
			this.notifyObservers();

			this.controlOffers();

			/**
			do {
				Thread.sleep(500);
			} while (!this.jestingPhasePlayed);
			 **/


			this.tour++;
		} while (basePioche.size() != 0);

		for (Joueur joueur : joueurs) {
		    joueur.getJest().add(joueur.getMain().poll());
        }

		Console.showJests();
		CompteurVarianteBase compteur = new CompteurVarianteBase();

		for (Joueur joueur : joueurs) {
			joueur.accept(compteur);
		}

		Console.showScores();
		this.terminerPartie();
		// Console.endOfGame();

		return;
	}

	public void terminerPartie() {

		Joueur winner = joueurs.get(0);
		for (Joueur joueur : joueurs) {
			if (joueur.getScore() > winner.getScore()) {
				winner = joueur;
			}
		}

		Console.showWinner(winner);

		this.basePioche.clear();
		this.tempPioche.clear();
		this.joueurs.clear();
		this.tour = 1;
		this.tropheesPartie.clear();
	}

	public void controlOffers() {

		Joueur choosingPlayer = joueurs.get(0);
		boolean everyonePlayed = false;

		for (Joueur joueur : joueurs) {
			joueur.setHasPlayed(false);
		}

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

	// méthode qui permet à chaque joueur de cacher une carte de sa main

	public void choisirCarteCachee() {

		for(Joueur joueur : joueurs) {
			if (!hidingPhasePlayed && joueur instanceof JoueurReel) {
				joueur.getMain().get(joueur.faireOffre()).setVisible(false);
				joueur.setHasPlayed(true);
				this.setChanged();
				this.notifyObservers();
			}
		}

		for (Joueur joueur : joueurs) {
			if (joueur instanceof JoueurVirtuel) {
				joueur.getMain().get(joueur.faireOffre()).setVisible(true);
				joueur.setHasPlayed(true);
				this.setChanged();
				this.notifyObservers();
			}
		}

		return;
	}

	public void declarerVainqueur() { //est appellé en fin de partie
		
	}
	
	public boolean checkCardsStates() {
		if (this.gamePhase == "sélection de la carte à cacher") {
			for (Joueur player : joueurs) {
				if (player instanceof JoueurReel) {
					if (player.getMain().get(0).isVisible() && player.getMain().get(1).isVisible()) {
						return false;
					}
					else if (!player.getMain().get(0).isVisible() && !player.getMain().get(1).isVisible()) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	/* getter setter */

	public int getTour() {
		return this.tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
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

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean started) {
		isStarted = started;
	}

	public boolean isSetup() {
		return isSetup;
	}

	public void setSetup(boolean setup) {
		isSetup = setup;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	public int getRealPlayerCount() {
		return realPlayerCount;
	}

	public void setRealPlayerCount(int realPlayerCount) {
		this.realPlayerCount = realPlayerCount;
	}

	public Variante getVariante() {
		return variante;
	}

	public void setVariante(Variante variante) {
		this.variante = variante;
	}

	public String getGamePhase() {
		return gamePhase;
	}

	public void setGamePhase(String gamePhase) {
		this.gamePhase = gamePhase;
	}

	public boolean isHidingPhasePlayed() {
		return hidingPhasePlayed;
	}

	public void setHidingPhasePlayed(boolean hidingPhasePlayed) {
		this.hidingPhasePlayed = hidingPhasePlayed;
	}

	@Override
	public void run() {
		this.isStarted = true;
		this.setChanged();
		this.notifyObservers();
		try {
			this.initialiserPartie();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
