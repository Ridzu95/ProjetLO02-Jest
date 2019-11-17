package fr.utt.lo02.xfmv.jest.model.joueurs;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import java.util.Scanner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class JoueurReel extends Joueur {
	

	public JoueurReel() {
		super();
		this.nom = "Joueur";
	}

	public JoueurReel(int id, String username) {
		super();
		this.id = id;
		this.nom = username;
		this.main = new LinkedList<Carte>();
		this.jest = new ArrayList<Carte>();
	}

	public int faireOffre() {
		System.out.println("Choisissez la carte à cacher :");

        System.out.println("(1) --- " + this.getMain().get(0));
        System.out.println("(2) --- " + this.getMain().get(1));

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
		
	}
	
	public String toString(){
	      return  nom;
	}
}
