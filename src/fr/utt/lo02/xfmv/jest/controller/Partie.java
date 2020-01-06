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
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.GUI;
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.Game;
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.GameConfig;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Partie implements Runnable {

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
	private ArrayList<Carte> selectCards;
	private ArrayList<Joueur> selectJoueurs;

	private boolean ready;

	private int message;
	private Console console;
	private Joueur currentPlaying;
	private Game game;

	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	public BlockingQueue<Integer> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	private BlockingQueue<Integer> queue;


	private Partie() {
		basePioche = new LinkedList<Carte>();
		tempPioche = new LinkedList<Carte>();
		tropheesPartie = new ArrayList<Carte>();
		joueurs = new ArrayList<Joueur>();
		tempPioche = new LinkedList<Carte>();
		this.tour = 1;
		isStarted = false;
		isSetup = false;
		playerCount = -1;
		realPlayerCount = -1;
		gamePhase = "init";
		hidingPhasePlayed = false;
		jestingPhasePlayed = false;
		variante = null;
		ready = true;

		message = -1;
	}

	private static Partie partie = new Partie();

	public static Partie getInstance() {
	 	return partie;
	}

	public void initialiserPartie() throws InterruptedException {

		System.out.println("\nLa partie a commencé \n");

		for (Couleurs couleur : Couleurs.values()) {
			for (Valeurs valeur : Valeurs.values()) {
				if (couleur != Couleurs.Joker && valeur != Valeurs.Joker) {
					this.basePioche.add(new Carte(valeur, couleur));
				}
			}
		}

		this.basePioche.add(new Carte(Valeurs.Joker, Couleurs.Joker));

		for ( int i = 0; i < realPlayerCount ; i++ ) {
		    this.joueurs.add(new JoueurReel(i, "realPlayer " + i));
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

			this.gamePhase = "hiding";
			System.out.println("Phase de " + this.gamePhase);
			this.console.showTurn(this.tour);
			int iteratorJoueur = 0;
			this.currentPlaying = joueurs.get(0);

			for (Joueur joueur : joueurs) {
				if (joueur instanceof JoueurReel){
					while (this.message <= 0 || this.message > 2) {

						if (this.message != -1){ //traite les mauvais unput
							System.out.println("Format incorrecte \n");
							this.console.majAffichage();
							this.message = -1;
						}
						Thread.sleep(1000);
					}
					message -= 1;
					joueur.getMain().get(this.message).setVisible(false);

					this.message = -1;
				} else {
					int random = (int) (Math.random() + 0.5);
					joueur.getMain().get(random).setVisible(true);
					this.message = -1;
				}

				System.out.println("\nLe joueur " + joueur + " a caché une carte");
				iteratorJoueur++;
				if (iteratorJoueur < this.playerCount) {
					this.currentPlaying = joueurs.get(iteratorJoueur);
				}
				this.game.guiUpdate();
			}

			this.gamePhase = "jesting";
			System.out.println("\n Phase de " + this.gamePhase +"\n");

			Collections.sort(joueurs);
			this.controlOffers();
			while (this.gamePhase !="gg" ) {

			}



			this.tour++;
		} while (basePioche.size() != 0);

		for (Joueur joueur : joueurs) {
		    joueur.getJest().add(joueur.getMain().poll());
        }

		this.console.showJests();
		CompteurVarianteBase compteur = new CompteurVarianteBase();

		for (Joueur joueur : joueurs) {
			joueur.accept(compteur);
		}

		this.console.showScores();
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

	public void controlOffers() throws InterruptedException {


		Joueur choosingPlayer = joueurs.get(0);
		boolean everyonePlayed = false;

		while (everyonePlayed == false) {

			this.selectCards = new ArrayList<Carte>();
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

			if (choosingPlayer instanceof JoueurReel){
				this.currentPlaying = choosingPlayer;
				System.out.println(this.selectCards.size());
				while (this.message > this.selectCards.size() || this.message <  0 ){

					if (this.message != -1){
						System.out.println("Format incorrect");
						this.message = -1;
					}
					Thread.sleep(2000);
				}
				System.out.println("sorti");
				this.message -= 1;
				Thread.sleep(2000);
				System.out.println("message :" + this.message);

			} else {
				this.message = choosingPlayer.prendreOffre(selectCards);
			}

			//int choice = choosingPlayer.prendreOffre(selectCards);
			// this.message > selectCards.size()
			if (this.message % 2 == 0) {
				choosingPlayer.getJest().add(selectJoueurs.get(this.message).getMain().pollFirst());
			}
			else {
				choosingPlayer.getJest().add(selectJoueurs.get(this.message).getMain().pollLast());
			}


			System.out.println("Le joueur " + choosingPlayer.toString() + " a mis la carte " + selectCards.get(this.message) + " dans son Jest !");
			System.out.println(""); //réinitialisation

			choosingPlayer.setHasPlayed(true);
			choosingPlayer = selectJoueurs.get(this.message);
			this.message = -1;

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

		this.gamePhase = "hiding";
		System.out.println("Phase de jesting terminée");

	}

	//méthode qui permet de savoir si tout les joueurs ont caché une carte

	public boolean checkCardsStates() {
		if (this.gamePhase == "hiding") {
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
			return true; //tous les joueurs on choisis leurs cartes
		}
		return false; //les joueurs n'ont pas tous choisis leurs cartes
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

	public void setVariante(Variante variante) {
		this.variante = variante;
	}

	public Variante getVariante() {
		return variante;
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

	public Joueur getCurrentPlaying() {
		return currentPlaying;
	}

	public void setCurrentPlaying(Joueur currentPlaying) {
		this.currentPlaying = currentPlaying;
	}

	public void setMessage(int message) {
		this.message = message;
	}

	public int getMessage() {
		return message;
	}

	public ArrayList<Carte> getSelectCards() {
		return selectCards;
	}

	public void setSelectCards(ArrayList<Carte> selectCards) {
		this.selectCards = selectCards;
	}

	public ArrayList<Joueur> getSelectJoueurs() {
		return selectJoueurs;
	}

	public void setSelectJoueurs(ArrayList<Joueur> selectJoueurs) {
		this.selectJoueurs = selectJoueurs;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void run() {

		try {
			this.initialiserPartie(); //lancer le thread = initialiser et jouer la partie
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
