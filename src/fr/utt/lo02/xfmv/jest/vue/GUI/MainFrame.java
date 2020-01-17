/*
 * Created by JFormDesigner on Fri Dec 20 13:12:37 CET 2019
 */

package fr.utt.lo02.xfmv.jest.vue.GUI;

import javax.swing.*;

import fr.utt.lo02.xfmv.jest.controller.Game;
import net.miginfocom.swing.*;

import java.util.concurrent.BlockingQueue;

/**
 * @author unknown
 */
public class MainFrame extends JFrame implements Runnable {

    private BlockingQueue<Integer> queue;


    public MainFrame(BlockingQueue<Integer> queue) {
        this.queue = queue;
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown

        //======== this ========
        setTitle("Jest");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3,align left top",
            // columns
            "0[fill]0",
            // rows
            "0[]0"));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    @Override
    public void run() {

        if (!Game.getInstance().isStarted() && !Game.getInstance().isSetup()) {
            this.setVisible(true); //maj l'affichage
            this.getContentPane().add(new Menu());
            this.pack();
        }
        if (Game.getInstance().isStarted() && !Game.getInstance().isSetup() && Game.getInstance().getGamePhase() != "results") {
            this.getContentPane().removeAll();
            this.getContentPane().add(new GameConfig());
            this.pack();
        }
        if (Game.getInstance().isStarted() && Game.getInstance().isSetup()) {

            this.getContentPane().removeAll();
            GameGUI gameGUI = null;
            try {
                gameGUI = new GameGUI();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.getContentPane().add(gameGUI);
            Game.getInstance().setGameGUI(gameGUI);
            this.pack();
        }
        if (Game.getInstance().getGamePhase() == "results") {
            this.getContentPane().removeAll();
            this.getContentPane().add(new Results());
            this.pack();
        }

    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
