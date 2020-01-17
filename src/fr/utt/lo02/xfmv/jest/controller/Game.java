package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.card.Card;
import fr.utt.lo02.xfmv.jest.model.card.Colors;
import fr.utt.lo02.xfmv.jest.model.card.Values;
import fr.utt.lo02.xfmv.jest.model.player.Player;
import fr.utt.lo02.xfmv.jest.model.player.RealPlayer;
import fr.utt.lo02.xfmv.jest.model.player.VirtualPlayer;
import fr.utt.lo02.xfmv.jest.model.variantes.Variant;
import fr.utt.lo02.xfmv.jest.vue.CLI.CLI;
import fr.utt.lo02.xfmv.jest.vue.GUI.GameGUI;

import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 * Core game engine. Runs in a separate thread and waits for messages in its queue.
 * Handles a lot of objects and collections necessary to play a Jest game : players instances, base and temporary card stacks used in a game turn, trophies, reference on current playing player, booleans to know which phase to play...
 * Each method in this class is explained separately.
 * Singleton (so the constructor is private and you access it by a getter method), since only one is needed and we need to access its attributes and methods A LOT and from quite a lot of objects.
 */

public class Game implements Runnable {

	private Variant variant;
	private LinkedList<Card> baseStack;
	private LinkedList<Card> tempStack;
	private ArrayList<Card> gameTrophies;
	private ArrayList<Player> players;
	private int turn;
	private boolean isStarted;
	private boolean isSetup;
	private boolean hidingPhasePlayed;
	private int playerCount;
	private int realPlayerCount;
	private String gamePhase;
	private ArrayList<Card> selectCards;
	private ArrayList<Player> selectPlayers;
	private boolean ready;
	private int message;
	private CLI CLI;
	private Player currentPlaying;
	private GameGUI gameGUI;
	private Player gameWinner;
	private BlockingQueue<Integer> queue;


	private Game() {
		baseStack = new LinkedList<Card>();
		tempStack = new LinkedList<Card>();
		gameTrophies = new ArrayList<Card>();
		players = new ArrayList<Player>();
		tempStack = new LinkedList<Card>();
		this.turn = 1;
		isStarted = false;
		isSetup = false;
		playerCount = -1;
		realPlayerCount = -1;
		gamePhase = "init";
		hidingPhasePlayed = false;
		variant = null;
		ready = true;
		selectCards = new ArrayList<Card>();
		selectPlayers = new ArrayList<Player>();
		message = -1;
	}

	private static Game game = new Game();

	public static Game getInstance() {
	 	return game;
	}

	/**
	 * Method used to initialize a game of Jest.
	 * The idea here is to create every card needed for the game using Colors and Values enums, then creating real players and/or bots according to what the user wanted in the game config CLI/GUI.
	 * It finally shuffles the card and directly ends by calling playGame() method.
	 * @throws InterruptedException
	 */

	public void initGame() throws InterruptedException {

		System.out.println("\nLa partie a commencé \n");

		for (Colors color : Colors.values()) {
			for (Values value : Values.values()) {
				if (color != Colors.Joker && value != Values.Joker) {
					this.baseStack.add(new Card(value, color));
				}
			}
		}

		this.baseStack.add(new Card(Values.Joker, Colors.Joker));

		for ( int i = 0; i < realPlayerCount ; i++ ) {
		    this.players.add(new RealPlayer(i, "realPlayer " + i));
		}

		for ( int i = 0; i < (playerCount - realPlayerCount) ; i++ ) {
		    this.players.add(new VirtualPlayer(i,1));
		}

		Collections.shuffle(this.baseStack);


		this.playGame();
		return;
	}

	/**
	 * Method used to hand out cards to players instances at the beginning of the game and between each turn.
	 * Also used in first turn to put one or two cards in the trophies collection.
	 * Draws and hands out cards from the base cards stack for the first turn.
	 * For other turns, it takes back all the players' remaining cards, put them in a temporary stack, adds cards from the base cards stack and finally hands all those out to the players for the next round.
	 */

	public void distributeCards() {

		if (this.turn == 1) {


			this.gameTrophies.add(this.baseStack.poll());

			if (players.size() == 3) {
				this.gameTrophies.add(this.baseStack.poll());
			}

			this.variant.enableTrophies(this.gameTrophies);
			this.variant.showTrophies(this.gameTrophies);


			for (Player i : players) {
				i.getMain().add(this.baseStack.poll());
				i.getMain().add(this.baseStack.poll());

			}

			return ;
		}
		else {

			for(Player i : players) {
				if (i.getMain().get(0) == null) {
					this.tempStack.add(i.getMain().pollLast());
				}
				else {
					this.tempStack.add(i.getMain().pollFirst());
				}
			}

			for (Player i : players) {
				this.tempStack.add(this.baseStack.poll());
			}

			for (Card card : tempStack) {
				card.setVisible(true);
			}

			Collections.shuffle(this.tempStack);

			for (Player i : players) {
				i.getMain().add(this.tempStack.poll());
				i.getMain().add(this.tempStack.poll());
			}

			return ;
		}
	}

	/**
	 * Central method of the class. Loops through distributeCards(), hidingControl() and jestingControl() until the base cards stack is empty.
	 * After that, the method put the last card in the hand of each player in their respective Jests.
	 * Instantiate a Jest-counting object, then each player accepts the counter (based on a Visitor design pattern) to calculate scores.
	 * Ends by calling the method finishGame().
	 * @throws InterruptedException
	 */

	public void playGame() throws InterruptedException {

		do {

			this.distributeCards();
			this.hidingControl();
			this.jestingControl();
			this.turn++;

		} while (baseStack.size() != 0);

		for (Player player : players) {
		    player.getJest().add(player.getMain().poll());
        }

		this.handOutTrophies();

		this.CLI.showJests();
		JestCounterBase counter = new JestCounterBase();

		for (Player player : players) {
			player.accept(counter);
		}

		this.CLI.showScores();
		this.finishGame();
		//Console.endOfGame();

		return;
	}

	/**
	 * Method that handles the "hiding" phase of the game.
	 * Cycles through the players to ask which card they want to hide.
	 * If it asks a real player, then it waits for a response (so a "1" or "2" message from the game's message queue : refer to the project's controllers to get the full grasp of how the "message" system works).
	 * If it asks a bot, it chooses randomly between 1 and 2.
	 * Once a card is selected, it becomes hidden, update the GUI and loop to the next player, until everyone has played.
	 * @throws InterruptedException
	 */

	public void hidingControl() throws InterruptedException {

		this.gamePhase = "hiding";
		System.out.println("Phase de " + this.gamePhase);
		this.CLI.showTurn(this.turn);
		int iteratorPlayer = 0;
		this.currentPlaying = players.get(0);

		if (this.turn != 1) {
			this.gameGUI.guiUpdate();
		}

		for (Player player : players) {
			if (player instanceof RealPlayer){
				while (this.message <= 0 || this.message > 2) {

					if (this.message != -1){ //traite les mauvais input
						System.out.println("Format incorrect \n");
						this.message = -1;
					}
					Thread.sleep(1000);
				}
				message -= 1;
				player.getMain().get(this.message).setVisible(false);

				this.message = -1;
			} else {

				int random = (int) (Math.random() + 0.5);
				player.getMain().get(random).setVisible(false);
				this.message = -1;
				System.out.println("Le bot " + Game.getInstance().getCurrentPlaying() + " choisis une carte à mettre dans son Jest ...");

			}

			System.out.println("\nLe joueur " + player + " a caché une carte");
			iteratorPlayer++;
			if (iteratorPlayer < this.playerCount) {
				this.currentPlaying = players.get(iteratorPlayer);
			}
			this.gameGUI.guiUpdate();
		}

	}

	/**
	 * Method that handles the "jesting" phase of the game.
	 * Cycles through the players to ask which card they want to put in their Jest.
	 * According to the official rules of the game, the player with the best visible card after the "hiding" phase is the first to play, so we sort the players collection (refer to Player class compareTo()).
	 * Two temporary collections are created : one with references to the pickable cards and one with references of the player(s) it is from (we should have done this with a Map but couldn't find the time to).
	 * Same way that for every user input in this project : the method waits for a message ("1", "2" or eventually "3" and "4" depending on the situation here) to be put in the message queue from GUI or CLI.
	 * Stops when everyone has played.
	 * @throws InterruptedException
	 */

	public void jestingControl() throws InterruptedException {

		this.gamePhase = "jesting";
		System.out.println("Phase de " + this.gamePhase);
		Collections.sort(players);
		this.currentPlaying = players.get(0);
		boolean everyonePlayed = false;

		for (Player player : players) {
			player.setHasPlayed(false);
		}

		while (everyonePlayed == false) {

			this.selectCards.clear();
			this.selectPlayers.clear();
			for (Player player : players) {
				if (player.getMain().size() == 2 && player != currentPlaying) {
					for (Card card : player.getMain()) {
						selectCards.add(card);
						selectPlayers.add(player);
					}
				}
			}
			if (selectCards.size() == 0) {
				for (Card card : currentPlaying.getMain()) {
					selectCards.add(card);
					selectPlayers.add(currentPlaying);
				}
			}

			if (currentPlaying instanceof RealPlayer){
				this.currentPlaying = currentPlaying;
				this.gameGUI.guiUpdate();
				while (this.message > this.selectCards.size() || this.message <  0 ){

					if (this.message != -1){
						System.out.println("Format incorrect");
						this.message = -1;
					}
					Thread.sleep(500);
				}
				this.message -= 1;


			} else {
				this.message = currentPlaying.takeOffer(selectCards);
				Thread.sleep(2000);
			}

			if (this.message % 2 == 0) {
				currentPlaying.getJest().add(selectPlayers.get(this.message).getMain().pollFirst());
			}
			else {
				currentPlaying.getJest().add(selectPlayers.get(this.message).getMain().pollLast());
			}


			System.out.println("Le joueur " + currentPlaying.toString() + " a mis la carte " + selectCards.get(this.message) + " dans son Jest !");
			System.out.println(); //réinitialisation

			currentPlaying.setHasPlayed(true);
			currentPlaying = selectPlayers.get(this.message);
			this.message = -1;

			if (currentPlaying.getHasPlayed() == true) {
				int a = 0;
				while (a < players.size() && players.get(a).getHasPlayed() == true) {
					a++;
				}
				if (a > players.size() - 1) {
					everyonePlayed = true;
				}
				else {
					currentPlaying = players.get(a);
				}
			}

		}

		System.out.println("Phase de jesting terminée");

	}

	/**
	 * Method to hand out trophies to players who should get them.
	 * Kind of a barbaric function, since every trophy has different conditions to be redeemable. So it tests out different things depending on the trophy.
	 * The trophy card then go into the player's Jest.
	 */

	public void handOutTrophies() {


		int i = 0, pos1 = 0, pos2 = 0, position = -1; //on a besoin de positions pour garder en mémoire à qui on va attribuer les trophées

		for (Card trophy : this.gameTrophies) {
			i += 1;
			if (trophy.getTrophy().getId() == 1) { //HighestTrefle
				for ( Player player : this.players) { //test pour chaque joueur s'il possède le 4 de trèfle
					for (Card card : player.getJest()) {
						if (card.getColor().getColor() == "♣" && card.getValue().getValeur() == 4) {

							position = this.players.indexOf(player); //on sauvegarde la position du joueur qui possède le trophée
							fr.utt.lo02.xfmv.jest.vue.CLI.CLI.showTrophyWinner(players.get(position), trophy);


						}
					}
				}
			}

			if (trophy.getTrophy().getId() == 3) { //HighestCoeur
				for ( Player player : this.players) {
					for (Card card : player.getJest()) {
						if (card.getColor().getColor() == "♥" && card.getValue().getValeur() == 4) {
							position = this.players.indexOf(player);
							fr.utt.lo02.xfmv.jest.vue.CLI.CLI.showTrophyWinner(players.get(position), trophy);


						}
					}
				}
			}

			if (trophy.getTrophy().getId() == 4 ) {//HighestPique
				for ( Player player : this.players) {
					for (Card card : player.getJest()) {
						if (card.getColor().getColor() == "♠" && card.getValue().getValeur() == 4) {
							position = this.players.indexOf(player);
							fr.utt.lo02.xfmv.jest.vue.CLI.CLI.showTrophyWinner(players.get(position), trophy);


						}
					}
				}
			}

			if (trophy.getTrophy().getId() == 2) {//"HighestCarreau"
				for ( Player player : this.players) {
					for (Card card : player.getJest()) {
						if (card.getColor().getColor() == "♦" && card.getValue().getValeur() == 4) {
							position = this.players.indexOf(player);
							fr.utt.lo02.xfmv.jest.vue.CLI.CLI.showTrophyWinner(players.get(position), trophy);


						}
					}
				}
			}

			if (trophy.getTrophy().getId() == 6) {//LowestCarreau"
				for ( Player player : this.players) {
					for (Card card : player.getJest()) {
						if (card.getColor().getColor() == "♦" && card.getValue().getValeur() == 1) {
							position = this.players.indexOf(player);
							fr.utt.lo02.xfmv.jest.vue.CLI.CLI.showTrophyWinner(players.get(position), trophy);


						}
					}
				}
			}

			if (trophy.getTrophy().getId() == 8) {//"LowestPique"
				for ( Player player : this.players) {
					for (Card card : player.getJest()) {
						if (card.getColor().getColor() == "♠" && card.getValue().getValeur() == 1) {
							position = this.players.indexOf(player);
							fr.utt.lo02.xfmv.jest.vue.CLI.CLI.showTrophyWinner(players.get(position), trophy);


						}
					}
				}
			}

			if (trophy.getTrophy().getId() == 7) {//"LowestCoeur"
				for ( Player player : this.players) {
					for (Card card : player.getJest()) {
						if (card.getColor().getColor() == "♥" && card.getValue().getValeur() == 1) {
							position = this.players.indexOf(player);
							fr.utt.lo02.xfmv.jest.vue.CLI.CLI.showTrophyWinner(players.get(position), trophy);


						}
					}
				}
			}

			if (trophy.getTrophy().getId() == 5) {//"LowestTrefle"
				for ( Player player : this.players) {
					for (Card card : player.getJest()) {
						if (card.getColor().getColor() == "♣" && card.getValue().getValeur() == 1) {
							position = this.players.indexOf(player);
							fr.utt.lo02.xfmv.jest.vue.CLI.CLI.showTrophyWinner(players.get(position), trophy);


						}
					}
				}
			}

			if (trophy.getTrophy().getId() == 14) {//"Joker"
				for ( Player player : this.players) {
					for (Card card : player.getJest()) {
						if (card.getColor().getColor() == "★") {
							position = this.players.indexOf(player);
							fr.utt.lo02.xfmv.jest.vue.CLI.CLI.showTrophyWinner(players.get(position), trophy);


						}
					}
				}
			}

			if (trophy.getTrophy().getId() == 9) {//"MajorityDeux"
				int nb;
				position = 0;
				int nbest = 0;
				for ( Player player : this.players) {
					nb = 0;
					for (Card card : player.getJest()) {
						if (card.getValue().getValeur() == 2) {
							nb+= 1;
						}
					}
					if (nb > nbest) {
						position = players.indexOf(player);
						nbest = nb;
					} else if (nb == nbest) { //si égalité on garde la position du joueur qui possède le pique
						for (Card card : player.getJest()) {
							if (card.getValue().getValeur() == 2 && card.getColor().getColor() == "♠") {
								position = players.indexOf(player);
								nbest = nb;
							}
						}
					}


				}
				fr.utt.lo02.xfmv.jest.vue.CLI.CLI.showTrophyWinner(players.get(position), trophy);



			}

			if (trophy.getTrophy().getId() == 10) {//"MajorityTrois"
				int nb;
				position = 0;
				int nbest = 0;
				for ( Player player : this.players) {
					nb = 0;
					for (Card card : player.getJest()) {
						if (card.getValue().getValeur() == 3) {
							nb+= 1;
						}
					}
					if (nb > nbest) {
						position = players.indexOf(player);
						nbest = nb;
					} else if (nb == nbest) { //si égalité on garde la position du joueur qui possède le pique
						for (Card card : player.getJest()) {
							if (card.getValue().getValeur() == 3 && card.getColor().getColor() == "♠") {
								position = players.indexOf(player);
								nbest = nb;
							}
						}
					}
				}
				fr.utt.lo02.xfmv.jest.vue.CLI.CLI.showTrophyWinner(players.get(position), trophy);



			}

			if (trophy.getTrophy().getId() == 11) {//"MajorityQuatre"
				int nb;
				position = 0;
				int nbest = 0;
				for ( Player player : this.players) {
					nb = 0;
					for (Card card : player.getJest()) {
						if (card.getValue().getValeur() == 4) {
							nb+= 1;
						}
					}
					if (nb > nbest) {
						position = players.indexOf(player);
						nbest = nb;
					} else if (nb == nbest) { //si égalité on garde la position du joueur qui possède le pique
						for (Card card : player.getJest()) {
							if (card.getValue().getValeur() == 4 && card.getColor().getColor() == "♠") {
								position = players.indexOf(player);
								nbest = nb;
							}
						}
					}
				}
				fr.utt.lo02.xfmv.jest.vue.CLI.CLI.showTrophyWinner(players.get(position), trophy);



			}

			if (trophy.getTrophy().getId() == 12) {//"BestJest"
				JestCounterBase compteur = new JestCounterBase();
				int score;
				position = 0;
				int bestscore = 0;
				for ( Player player : this.players) {

					player.accept(compteur);
					score = player.getScore();
					if (score > bestscore) {
						position = players.indexOf(player);
						bestscore = score;
					} else if (score == bestscore) {
						Card bestCardJest = player.getJest().get(0);

						Collections.sort(player.getJest());
						Collections.sort(this.players.get(position).getJest());

						if (bestCardJest.getValue().getValeur() > this.players.get(position).getJest().get(0).getValue().getValeur()) {
							position = players.indexOf(player);
							bestscore = score;
						} else if (bestCardJest.getValue().getValeur() == this.players.get(position).getJest().get(0).getValue().getValeur()
								&& bestCardJest.getColor().getOrdre() > this.players.get(position).getJest().get(0).getColor().getOrdre()) {
							position = players.indexOf(player);
							bestscore = score;
						}
					}
					player.setScore(0); //à vérifier si c'est nécessaure
				}
				fr.utt.lo02.xfmv.jest.vue.CLI.CLI.showTrophyWinner(players.get(position), trophy);
			}

			if (trophy.getTrophy().getId() == 13) {//"BestJestNoJoker"
				JestCounterBase compteur1 = new JestCounterBase(); //compteur pour comparer la valeur des Jest
				int score;
				position = 0;
				int bestscore = 0;
				boolean hasJoker; //permet d'exclure le joueur qui possède le Joker
				for ( Player player : this.players){
					hasJoker = false;
					for (Card card : player.getJest()) {
						if ( card.getColor().getColor() == "★" ) {
							hasJoker = true;
						}
					}

					if (hasJoker == false) {
						player.accept(compteur1);
						score = player.getScore();
						if (score > bestscore) {
							position = players.indexOf(player);
							bestscore = score;
						}  else if (score == bestscore) { // si égalité on va tester qui a la plus grande valeur
							Card bestCardJest = player.getJest().get(0);
							Collections.sort(player.getJest()); //sort permet de mettre la carte la plus forte du joueur en position 0 du Jest
							Collections.sort(this.players.get(position).getJest());
							if (bestCardJest.getValue().getValeur() > this.players.get(position).getJest().get(0).getValue().getValeur()) {
								position = players.indexOf(player);
								bestscore = score;
							} else if //si à nouveau égalité on va tester qui a la couleur la plus hate
							(bestCardJest.getValue().getValeur() == this.players.get(position).getJest().get(0).getValue().getValeur()
											&& bestCardJest.getColor().getOrdre() > this.players.get(position).getJest().get(0).getColor().getOrdre()) {
								position = players.indexOf(player);
								bestscore = score;
							}
						}
						player.setScore(0); //à vérifier si c'est nécessaire
					}

				}
				fr.utt.lo02.xfmv.jest.vue.CLI.CLI.showTrophyWinner(players.get(position), trophy);


			}

			if ( i == 1) {
				pos1 = position;
			} else {
				pos2 = position;
			}



		}
		//enfin on ajoute les trophées dans les Jest des joueurs
		this.players.get(pos1).getJest().add(this.gameTrophies.get(0)); //fait en dehors de la boucle sinon erreur de concurrence

		if (this.gameTrophies.size() == 2) {
			this.players.get(pos2).getJest().add(this.gameTrophies.get(1));
		}
	}

	/**
	 * Method to "close" the game at the end.
	 * Cycles through the players' scores to see who is the winner, and create a reference to it for the CLI and GUI to display it !
	 * Puts every variable necessary to start and play a game to default.
	 * @throws InterruptedException
	 */

	public void finishGame() throws InterruptedException {

		Player winner = players.get(0);
		for (Player player : players) {
			if (player.getScore() > winner.getScore()) {
				winner = player;
			}
		}

		this.gameWinner = winner;
		this.CLI.showWinner(winner);

		this.gamePhase = "results";
		this.queue.put(1);

		while (this.getGamePhase() != "init") {
			Thread.sleep(500);
		}

		turn = 1;
		isStarted = false;
		isSetup = false;
		playerCount = -1;
		realPlayerCount = -1;
		hidingPhasePlayed = false;
		variant = null;
		ready = true;
		message = -1;
		this.baseStack.clear();
		this.tempStack.clear();
		this.players.clear();
		this.gameTrophies.clear();
	}

	/**
	 * Inherited from the Runnable interface.
	 * Just call the method initGame().
	 * We need this so that this Game singleton runs in a separate thread.
	 */

	public void run() {

		try {
			this.initGame(); //lancer le thread = initialiser et jouer la partie
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getTurn() {
		return this.turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public LinkedList<Card> getBaseStack() {
		return baseStack;
	}

	public LinkedList<Card> getTempStack() {
		return tempStack;
	}

	public ArrayList<Card> getGameTrophies() {
		return gameTrophies;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean started) {
		isStarted = started;
	}

	public boolean isSetup() {
		return isSetup;
	}

	public void setSetup(boolean setup) {
		isSetup = setup;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	public int getRealPlayerCount() {
		return realPlayerCount;
	}

	public void setRealPlayerCount(int realPlayerCount) {
		this.realPlayerCount = realPlayerCount;
	}

	public void setVariant(Variant variant) {
		this.variant = variant;
	}

	public Variant getVariant() {
		return variant;
	}

	public String getGamePhase() {
		return gamePhase;
	}

	public void setGamePhase(String gamePhase) {
		this.gamePhase = gamePhase;
	}

	public boolean isHidingPhasePlayed() {
		return hidingPhasePlayed;
	}

	public void setHidingPhasePlayed(boolean hidingPhasePlayed) {
		this.hidingPhasePlayed = hidingPhasePlayed;
	}

	public Player getCurrentPlaying() {
		return currentPlaying;
	}

	public void setCurrentPlaying(Player currentPlaying) {
		this.currentPlaying = currentPlaying;
	}

	public void setMessage(int message) {
		this.message = message;
	}

	public int getMessage() {
		return message;
	}

	public ArrayList<Card> getSelectCards() {
		return selectCards;
	}

	public void setSelectCards(ArrayList<Card> selectCards) {
		this.selectCards = selectCards;
	}

	public ArrayList<Player> getSelectPlayers() {
		return selectPlayers;
	}

	public void setSelectPlayers(ArrayList<Player> selectPlayers) {
		this.selectPlayers = selectPlayers;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public GameGUI getGameGUI() {
		return gameGUI;
	}

	public void setGameGUI(GameGUI gameGUI) {
		this.gameGUI = gameGUI;
	}

	public Player getGameWinner() {
		return gameWinner;
	}

	public void setGameWinner(Player gameWinner) {
		this.gameWinner = gameWinner;
	}

	public CLI getCLI() {
		return CLI;
	}

	public void setCLI(CLI CLI) {
		this.CLI = CLI;
	}

	public BlockingQueue<Integer> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

}
