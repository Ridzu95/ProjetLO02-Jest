package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.joueurs.JoueurReel;
import fr.utt.lo02.xfmv.jest.model.joueurs.JoueurVirtuel;
import fr.utt.lo02.xfmv.jest.model.variantes.Variante;

import java.util.ArrayList;
import java.util.Scanner;

public class Partie implements Variante {

	private int nbJoueurs;
	// private Variante variante;
	private ArrayList<Carte> pioche;
	private int tour;

	public Partie(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
		// this.variante = variante;
		//this.nbJoueurs = variante.nbJoueurs(); // méthode qui va renvoyer le nb de joueurs selon la variante (3 ou 4)
		//on fait des actions en fonction de la variante
		this.tour = 1;
	}
	
	public Partie() { //partie créee par défaut
		this.tour = 1;
		//créer la pioche
	}
	
	public void debuterPartie() {

		System.out.println("Vous avez choisi une partie à " + this.nbJoueurs + " joueurs, il faut donc créer " + (this.nbJoueurs - 1) + " bots.");
		System.out.println("Choisissez votre nom d'utilisateur : ");
		Scanner sc = new Scanner(System.in);
		String username = sc.nextLine();
		JoueurReel humain = new JoueurReel(1, username);

		if (this.nbJoueurs == 3) {
			JoueurVirtuel joueur_2 = new JoueurVirtuel(1);
			JoueurVirtuel joueur_3 = new JoueurVirtuel(1);
		}
		else if (this.nbJoueurs == 4) {
			JoueurVirtuel joueur_2 = new JoueurVirtuel(1);
			JoueurVirtuel joueur_3 = new JoueurVirtuel(1);
			JoueurVirtuel joueur_4 = new JoueurVirtuel(1);
		}
		//créer les joueurs
		//le reste se fait dans le main ?
	}
	
	public void distribuerCartes() { //est appellé en début de chaque tour
		
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
