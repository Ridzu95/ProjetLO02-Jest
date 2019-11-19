package fr.utt.lo02.xfmv.jest.model.joueurs;

import fr.utt.lo02.xfmv.jest.controller.Partie;
import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.vue.console.Console;

import java.util.Scanner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class JoueurReel extends Joueur {
	
	private String pseudo;

	public JoueurReel() {
		super();
		this.pseudo = "Joueur";
	}

	public JoueurReel(int id, String username) {
		super();
		this.id = id;
		this.pseudo = username;
		this.main = new LinkedList<Carte>();
		this.jest = new ArrayList<Carte>();
	}

	public int faireOffre() {
		Console.cardChoice(this);

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.print("Votre choix : ");
            choice = sc.nextInt();
        } while (choice != 1 && choice != 2);

        System.out.println("");

        return choice -1;
	}
	
	public void prendreOffre() {
		Console.displayPlayerCards(Partie.getInstance().getJoueurs());

	}
	
	public String toString(){
	      return  pseudo;
	}
}
