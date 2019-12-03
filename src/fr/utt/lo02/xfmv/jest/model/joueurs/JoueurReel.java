package fr.utt.lo02.xfmv.jest.model.joueurs;

import fr.utt.lo02.xfmv.jest.controller.Partie;
import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.vue.console.Console;

import java.util.Scanner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class JoueurReel extends Joueur {
	
	private String username;
		
	public JoueurReel() {
		super();
	}

	public JoueurReel(int id, String username) {
		super();
		this.id = id;
		this.main = new LinkedList<Carte>();
		this.jest = new LinkedList<Carte>();
		this.username = username;
	}

	public int faireOffre() {
		Console.cardChoice(this);

        int choice = 0;

        do {
			choice = Console.choiceMaking();
        } while (choice != 1 && choice != 2);

        System.out.println("");

        return choice -1;
	}
	
	public int prendreOffre(ArrayList<Carte> selectCards) {
		Console.displaySelectCards(selectCards, this);

		int choice = 0;

		do {
			choice = Console.choiceMaking();
		} while (choice <= 0 || choice > selectCards.size());

		System.out.println("");

		return choice - 1;

	}
	

	public String toString() {
		return this.username;
	}
}
