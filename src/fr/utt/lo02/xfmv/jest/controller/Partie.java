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

	private Partie() {}
	
	private static Partie partie = new Partie();

	public static Partie getInstance() {
		return partie;
	}

	public void debuterPartie() {
		
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
