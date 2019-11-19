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
					this.pioche.add(new Carte(valeur, couleur));
				}
			}
		}

		this.pioche.add(new Carte(Valeurs.Joker, Couleurs.Joker));

		JoueurReel player = new JoueurReel(1, Console.playerUsernameChoice());
		JoueurVirtuel bot1 = new JoueurVirtuel(1, 2);
		JoueurVirtuel bot2 = new JoueurVirtuel(1, 3);

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

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void jouerPartie() {

		this.choisirCarteCachee();
		Console.displayPlayerCards(joueurs);
		Joueur choosingPlayer = joueurs.get(0);
		Collections.sort(joueurs);


		while (choosingPlayer.getHasPlayed() == false) {
			System.out.println(choosingPlayer.getId());
			ArrayList<Carte> selectCards = new ArrayList<Carte>();
			for (Joueur joueur : joueurs) {
				if (joueur.getMain().size() == 2 && joueur != choosingPlayer) {
					for (Carte carte : joueur.getMain()) {
						selectCards.add(carte);
					}
				}
			}
			if (selectCards.size() == 0) {
				for (Carte carte : choosingPlayer.getMain()) {
					selectCards.add(carte);
				}
			}
			choosingPlayer.prendreOffre(selectCards);

		}

		return;
	}

	/* méthode qui permet à chaque joueur de cacher une carte de sa main */
	public void choisirCarteCachee() {

		for(Joueur joueur : joueurs) {
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
