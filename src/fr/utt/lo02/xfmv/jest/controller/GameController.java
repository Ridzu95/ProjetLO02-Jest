package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.variantes.Variant1;
import fr.utt.lo02.xfmv.jest.model.variantes.Variant2;
import fr.utt.lo02.xfmv.jest.model.variantes.VariantBase;
import fr.utt.lo02.xfmv.jest.vue.GUI.MainFrame;
import fr.utt.lo02.xfmv.jest.vue.CLI.CLI;

import java.awt.*;
import java.net.URI;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Consumer in the Producer/Consumer design pattern implemented in the project : CLI and GUI controllers (producers) put int messages in this class's queue, and this class (consumer) process the messages.
 */

public class GameController implements Runnable {

    private Game game;
    private BlockingQueue<Integer> queue;
    private MainFrame mainFrame;
    private CLI CLI;

    public GameController(Game game){
        this.game = game;
        this.queue =  new LinkedBlockingQueue<>(2);
    }

    /**
     * Inherited from the Runnable interface.
     * The GameController waits for a message to enter its queue, then process it via process() method.
     */

    public void run() {

        while (true){ //le thread tourne en boucle pour reçevoir des informations
            try {
                int msg = queue.take(); //fait rien sauf si un message est présent
                this.process(msg); //dans ce cas on envoie le message dans process pour le traiter
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to process messages entering the controller's queue.
     * Depending on the situation ("hiding" phase, "jesting" phase, initialization of the game, menu...), the process() method will mainly take the int message received in parameter and transfer it to the Game's message variable (which is used to select cards to pick etc),
     * but it can eventually start a GUI thread if we're in the menu.
     * Both CLI and GUI send messages and that is why everything runs in separate threads and we implemented the Producer/Consumer pattern.
     * @param msg
     * @throws InterruptedException
     */

    public void process(int msg) throws InterruptedException { //traitement du message

        if (this.game.isStarted() == false){
            switch (msg) {
                case 1 :
                    this.game.setStarted(true);
                    new Thread(mainFrame).start();
                    break;
                case 2 :
                    try {
                        URI uri = new URI("https://puu.sh/EKl29/655216593a.png");
                        Desktop.getDesktop().browse(uri);
                    }
                    catch(Exception ex) {}
                    //this.showMenu();
                    break;
                case 3 :
                    System.exit(0);
                    break;
                default :
                    System.out.println("Format incorrecte");
            }
        }

        else if (this.game.getPlayerCount() == -1){
            if (msg == 4 || msg == 3){
                this.game.setPlayerCount(msg);
            } else {
                System.out.println("Format incorrect");
            }
        }

        else if (this.game.getRealPlayerCount() == -1){
            if (msg > Game.getInstance().getPlayerCount() || msg < 0){
                System.out.println("Format incorrect");
            } else {
                this.game.setRealPlayerCount(msg);
            }
        }

        else if (this.game.getVariant() == null){
            switch (msg){
                case 1:
                    this.game.setVariant(new VariantBase());
                    break;
                case 2:
                    this.game.setVariant(new Variant1());
                    break;
                case 3:
                    this.game.setVariant(new Variant2());
                default:
                    System.out.println("Format incorrect");
            }
        }

        if ( this.game.getGamePhase() == "hiding"){
            if (msg == 1 || msg == 2){
                this.game.setMessage(msg);
            } else {
                System.out.println("Format incorrect");
            }
        }

        if ( this.game.getVariant() != null && this.game.getRealPlayerCount() != -1 && this.game.getPlayerCount() != -1 && this.game.getGamePhase() == "init"){
            this.game.setSetup(true);
            new Thread(Game.getInstance()).start();
            new Thread(this.mainFrame).start();
        }

        if (Game.getInstance().getGamePhase() == "jesting"){
            this.game.setMessage(msg);
        }

        if (Game.getInstance().getGamePhase() == "results") {
            new Thread(mainFrame).start();
        }

        this.run();

    }

    public Game getGame() {
        return game;
    }

    public CLI getCLI() {
        return CLI;
    }

    public void setCLI(CLI CLI) {
        this.CLI = CLI;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public BlockingQueue<Integer> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
