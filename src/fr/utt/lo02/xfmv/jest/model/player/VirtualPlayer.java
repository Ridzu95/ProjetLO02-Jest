package fr.utt.lo02.xfmv.jest.model.player;

import fr.utt.lo02.xfmv.jest.model.card.Card;
import fr.utt.lo02.xfmv.jest.model.strategie.StratRandom;
import fr.utt.lo02.xfmv.jest.model.strategie.StratBest;
import fr.utt.lo02.xfmv.jest.model.strategie.Strategy;


import java.util.ArrayList;

public class VirtualPlayer extends Player {
	
	private Strategy strategy;
	
	public VirtualPlayer(int id, int choicestrat) {
		super(id);
		if (choicestrat == 2) {
			Strategy strat = new StratBest();
			this.strategy = strat;
		} else {
			Strategy strat = new StratRandom();
			this.strategy = strat;
		}
		
		
	}
	
	public VirtualPlayer(int id, Strategy strategy) {
		super(id);
		this.strategy = strategy;
	}
	
	public int makeOffer() {
		return (int) (Math.random() + 0.5) ;
	}
	
	public int takeOffer(ArrayList<Card> selectCards) {
		return this.strategy.executerStrategie(selectCards);
	}
	
	public String toString(){
	      return  "Bot " + this.id;
	}

}
