package fr.utt.lo02.xfmv.jest.model.player;

import fr.utt.lo02.xfmv.jest.controller.JestCounter;
import fr.utt.lo02.xfmv.jest.model.card.Card;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class for the Player model in the Jest game.
 * Each player has an id, a collection for its hand, a collection for its Jest, a collection filled with references to the "cards" in GUI it is associated with.
 * This specific class is not instantiable, methods change between real players and bots so this specific one is only useful for inheritance.
 */

public abstract class Player implements Comparable<Player> {

	protected int id;
	protected LinkedList<Card> main;
	protected LinkedList<Card> jest;
	protected LinkedList<JButton> guiCards;
	protected boolean hasPlayed;
	protected int score;

	public Player() {
		this.id = 0;
		this.score = 0;
		this.hasPlayed = false;
		this.main = new LinkedList<Card>();
		this.jest = new LinkedList<Card>();
		this.guiCards = new LinkedList<JButton>();
	}

	public Player(int id) {
		this.id = id;
		this.score = 0;
		this.hasPlayed = false;
		this.main = new LinkedList<Card>();
		this.jest = new LinkedList<Card>();
		this.guiCards = new LinkedList<JButton>();
	}

	public LinkedList<Card> getMain() {
		return this.main;
	}
	
	public int makeOffer() {
		return 0; //va appeller soit faireOffre de virtuel ou réel
	}

	public void setHasPlayed(boolean hasPlayed) {
		this.hasPlayed = hasPlayed;
	}

	public LinkedList<Card> getJest() {
		return jest;
	}

	public int takeOffer(ArrayList<Card> selectCards) { //va appeller soit prendrOffre de virtuel ou réel
		return 0;
	}

	public Card getVisibleCard() {
		return (this.getMain().get(0).isVisible() ? this.getMain().get(0) : this.getMain().get(1));
	}

	public boolean getHasPlayed() {
		return this.hasPlayed;
	}

	public int getId() {
		return id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public LinkedList<JButton> getGuiCards() {
		return guiCards;
	}

	public void setGuiCards(LinkedList<JButton> guiCards) {
		this.guiCards = guiCards;
	}


	@Override
	public int compareTo(Player player) {

		int sorter = player.getVisibleCard().getValue().getValeur() - this.getVisibleCard().getValue().getValeur();
		if (sorter == 0) {
			sorter = player.getVisibleCard().getColor().getOrdre() - this.getVisibleCard().getColor().getOrdre();
			return sorter;
		}
		else {
			return sorter;
		}

	}

	public void accept(JestCounter jestCounter) {
		jestCounter.countJest(this);
	}
	
	
	
}
