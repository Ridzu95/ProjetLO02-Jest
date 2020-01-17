package fr.utt.lo02.xfmv.jest.model.player;

import fr.utt.lo02.xfmv.jest.model.card.Card;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class for real players model.
 * @extends Player
 */

public class RealPlayer extends Player {
	
	private String username;
		
	public RealPlayer() {
		super();
	}

	public RealPlayer(int id, String username) {
		super();
		this.id = id;
		this.main = new LinkedList<Card>();
		this.jest = new LinkedList<Card>();
		this.username = username;
	}
	
	public int takeOffer(ArrayList<Card> selectCards) {
		//Console.displaySelectCards(selectCards, this);

		Scanner sc = new Scanner(System.in);
		int choice = 0;

		do {
			System.out.print("Votre choix : ");
			choice = sc.nextInt();
		} while (choice == 0 && choice > selectCards.size());

		System.out.println();

		return choice - 1;

	}
	

	public String toString() {
		return this.username;
	}
}
