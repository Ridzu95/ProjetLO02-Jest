package fr.utt.lo02.xfmv.jest;

import com.formdev.flatlaf.FlatLightLaf;
import fr.utt.lo02.xfmv.jest.controller.Game;
import fr.utt.lo02.xfmv.jest.controller.GameController;
import fr.utt.lo02.xfmv.jest.vue.CLI.CLI;
import fr.utt.lo02.xfmv.jest.vue.GUI.MainFrame;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        FlatLightLaf.install(); // installation du Look and Feel FlatLaf

        Game.getInstance();
        GameController gameController = new GameController(Game.getInstance());
        //on a besoin d'un controller sur la partie

        MainFrame mainFrame = new MainFrame(gameController.getQueue());
        gameController.setMainFrame(mainFrame);

        Game.getInstance().setQueue(gameController.getQueue());
        //la queue commune à tous les threads et les classes est créee dans PartieController et passée à Partie

        CLI CLI = new CLI(gameController.getQueue());
        gameController.setCLI(CLI);
        Game.getInstance().setCLI(CLI);


        Thread partieControllerThread = new Thread(gameController);
        partieControllerThread.start();
        //le thread de controller c'est la boucle qui en permanence lit les message qu'elle reçoit

        //il faut lancer une première fois les thread de gui et console
        new Thread(gameController.getMainFrame()).start();
        new Thread(gameController.getCLI()).start();

    }
}
