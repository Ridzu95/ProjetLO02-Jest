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

import java.lang.reflect.Array;
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
		    this.joueurs.add(new JoueurReel(i, Console.playerUsernameChoice(i+1)));
		}
		
		for ( int i = 0; i < temp1 - temp2 ; i++ ) {
		    this.joueurs.add(new JoueurVirtuel(i,Console.demanderStrategie(i)));
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
				System.out.println(this.tropheesPartie);
			}

			this.variante.activerTrophees(this.tropheesPartie);
			this.variante.showTrophies(this.tropheesPartie);

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

		for (Joueur joueur : joueurs) {
		    joueur.getJest().add(joueur.getMain().poll());
        }
		
		this.attribuerTrophees();
		Console.showJests();
		CompteurVarianteBase compteur = new CompteurVarianteBase();

		for (Joueur joueur : joueurs) {
			joueur.accept(compteur);
		}
		
		Console.showScores();

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
	
	public void attribuerTrophees() { //permet d'attribuer les trophées de la partie
		
		System.out.println(this.tropheesPartie);
		for ( Joueur joueur : this.joueurs) {
			for (Carte carte : joueur.getJest()) {
				carte.setVisible(true);
			}
			Collections.sort(joueur.getJest());
			System.out.println(joueur);
			System.out.println(joueur.getJest()); //utilisé pour le debug
		}
		
		int i = 0, pos1 = 0, pos2 = 0, position = -1;
		
		for (Carte trophee : this.tropheesPartie) {
			i += 1;
			System.out.println(trophee.getTrophee().getDescription());
			
			//on analyse le type de trophée
			if (trophee.getTrophee().getId() == 1) { //HighestTrefle
				for ( Joueur joueur : this.joueurs) {
					for (Carte carte : joueur.getJest()) {
						if (carte.getCouleur().getCouleur() == "♣" && carte.getValeur().getValeur() == 4) {
							
							position = this.joueurs.indexOf(joueur);
							System.out.println(joueur + " a remporté le trophée");
							System.out.println("");

						}
					}
				}
			}
			
			if (trophee.getTrophee().getId() == 3) { //HighestCoeur
				for ( Joueur joueur : this.joueurs) {
					for (Carte carte : joueur.getJest()) {
						if (carte.getCouleur().getCouleur() == "♥" && carte.getValeur().getValeur() == 4) {
							position = this.joueurs.indexOf(joueur);
							System.out.println(joueur + " a remporté le trophée");
							System.out.println("");

						}
					}
				}
			}
			
			if (trophee.getTrophee().getId() == 4 ) {//HighestPique
				for ( Joueur joueur : this.joueurs) {
					for (Carte carte : joueur.getJest()) {
						if (carte.getCouleur().getCouleur() == "♠" && carte.getValeur().getValeur() == 4) {
							position = this.joueurs.indexOf(joueur);
							System.out.println(joueur + " a remporté le trophée");
							System.out.println("");

						}
					}
				}
			}
			
			if (trophee.getTrophee().getId() == 2) {//"HighestCarreau"
				for ( Joueur joueur : this.joueurs) {
					for (Carte carte : joueur.getJest()) {
						if (carte.getCouleur().getCouleur() == "♦" && carte.getValeur().getValeur() == 4) {
							position = this.joueurs.indexOf(joueur);
							System.out.println(joueur + " a remporté le trophée");
							System.out.println("");

						}
					}
				}
			}
			
			if (trophee.getTrophee().getId() == 6) {//LowestCarreau"
				for ( Joueur joueur : this.joueurs) {
					for (Carte carte : joueur.getJest()) {
						if (carte.getCouleur().getCouleur() == "♦" && carte.getValeur().getValeur() == 1) {
							position = this.joueurs.indexOf(joueur);
							System.out.println(joueur + " a remporté le trophée");
							System.out.println("");

						}
					}
				}
			}
			
			if (trophee.getTrophee().getId() == 8) {//"LowestPique"
				for ( Joueur joueur : this.joueurs) {
					for (Carte carte : joueur.getJest()) {
						if (carte.getCouleur().getCouleur() == "♠" && carte.getValeur().getValeur() == 1) {
							position = this.joueurs.indexOf(joueur);
							System.out.println(joueur + " a remporté le trophée");
							System.out.println("");

						}
					}
				}
			}
			
			if (trophee.getTrophee().getId() == 7) {//"LowestCoeur"
				for ( Joueur joueur : this.joueurs) {
					for (Carte carte : joueur.getJest()) {
						if (carte.getCouleur().getCouleur() == "♥" && carte.getValeur().getValeur() == 1) {
							position = this.joueurs.indexOf(joueur);
							System.out.println(joueur + " a remporté le trophée");
							System.out.println("");

						}
					}
				}
			}
			
			if (trophee.getTrophee().getId() == 5) {//"LowestTrefle"
				for ( Joueur joueur : this.joueurs) {
					for (Carte carte : joueur.getJest()) {
						if (carte.getCouleur().getCouleur() == "♣" && carte.getValeur().getValeur() == 1) {
							position = this.joueurs.indexOf(joueur);
							System.out.println(joueur + " a remporté le trophée");
							System.out.println("");

						}
					}
				}
			}
			
			if (trophee.getTrophee().getId() == 14) {//"Joker"
				for ( Joueur joueur : this.joueurs) {
					for (Carte carte : joueur.getJest()) {
						if (carte.getCouleur().getCouleur() == "★") {
							position = this.joueurs.indexOf(joueur);
							System.out.println(joueur + " a remporté le trophée");
							System.out.println("");

						}
					}
				}
			}
			
			if (trophee.getTrophee().getId() == 9) {//"MajorityDeux"
				int nb;
				position = 0;
				int nbest = 0;
				for ( Joueur joueur : this.joueurs) {
					nb = 0;
					for (Carte carte : joueur.getJest()) {
						if (carte.getValeur().getValeur() == 2) {
							nb+= 1;
						}
					}
					if (nb > nbest) {
						position = joueurs.indexOf(joueur);
						nbest = nb;
					} else if (nb == nbest) { //si égalité on garde la position du joueur qui possède le pique
						for (Carte carte : joueur.getJest()) {
							if (carte.getValeur().getValeur() == 2 && carte.getCouleur().getCouleur() == "♠") {
								position = joueurs.indexOf(joueur);
								nbest = nb;
							}
						}
					}
					
					
				}
				System.out.println(joueurs.get(position) + " a remporté le trophée !");
				System.out.println("");


			}
			
			if (trophee.getTrophee().getId() == 10) {//"MajorityTrois"
				int nb;
				position = 0;
				int nbest = 0;
				for ( Joueur joueur : this.joueurs) {
					nb = 0;
					for (Carte carte : joueur.getJest()) {
						if (carte.getValeur().getValeur() == 3) {
							nb+= 1;
						}
					}
					if (nb > nbest) {
						position = joueurs.indexOf(joueur);
						nbest = nb;
					} else if (nb == nbest) { //si égalité on garde la position du joueur qui possède le pique
						for (Carte carte : joueur.getJest()) {
							if (carte.getValeur().getValeur() == 3 && carte.getCouleur().getCouleur() == "♠") {
								position = joueurs.indexOf(joueur);
								nbest = nb;
							}
						}
					}
				}
				System.out.println(joueurs.get(position) + " a remporté le trophée !");
				System.out.println("");


			}
			
			if (trophee.getTrophee().getId() == 11) {//"MajorityQuatre"
				int nb;
				position = 0;
				int nbest = 0;
				for ( Joueur joueur : this.joueurs) {
					nb = 0;
					for (Carte carte : joueur.getJest()) {
						if (carte.getValeur().getValeur() == 4) {
							nb+= 1;
						}
					}
					if (nb > nbest) {
						position = joueurs.indexOf(joueur);
						nbest = nb;
					} else if (nb == nbest) { //si égalité on garde la position du joueur qui possède le pique
						for (Carte carte : joueur.getJest()) {
							if (carte.getValeur().getValeur() == 4 && carte.getCouleur().getCouleur() == "♠") {
								position = joueurs.indexOf(joueur);
								nbest = nb;
							}
						}
					}
				}
				System.out.println(joueurs.get(position) + " a remporté le trophée !");
				System.out.println("");


			}
			
			if (trophee.getTrophee().getId() == 12) {//"BestJest"
				CompteurVarianteBase compteur = new CompteurVarianteBase();
				int score;
				position = 0;
				int bestscore = 0;
				for ( Joueur joueur : this.joueurs) {
					joueur.accept(compteur);
					score = joueur.getScore();
					if (score > bestscore) {
						position = joueurs.indexOf(joueur);
						bestscore = score;
					}
					joueur.setScore(0); //à vérifier si c'est nécessaure
				}
				System.out.println(joueurs.get(position) + " a remporté le trophée !");
				System.out.println("");

			}
			
			if (trophee.getTrophee().getId() == 13) {//"BestJestNoJoker"
				CompteurVarianteBase compteur = new CompteurVarianteBase();
				int score;
				position = -1;
				int bestscore = 0;
				boolean hasJoker;
				for ( Joueur joueur : this.joueurs){
					hasJoker = false;
					for (Carte carte : joueur.getJest()) {
						if ( carte.getCouleur().getCouleur() == "★" ) {
							hasJoker = true;
						}
					}
					
					if (hasJoker == false) {
						joueur.accept(compteur);
					score = joueur.getScore();
					if (score > bestscore) {
						position += 1;
						bestscore = score;
						}
					joueur.setScore(0); //à vérifier si c'est nécessaire
					}
					
				}
				System.out.println(joueurs.get(position) + " a remporté le trophée !");
				System.out.println("");

			}
			
			if ( i == 1) {
				pos1 = position;
			} else {
				pos2 = position;
			}
				
			
			
			
			
		}
		
		this.joueurs.get(pos1).getJest().add(this.tropheesPartie.get(0)); //fait en dehors de la boucle sinon erreur de concurrence
		
		if (this.tropheesPartie.size() == 2) {
			this.joueurs.get(pos2).getJest().add(this.tropheesPartie.get(1));
		}
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
