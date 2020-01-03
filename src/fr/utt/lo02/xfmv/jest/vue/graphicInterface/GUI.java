/*
 * Created by JFormDesigner on Fri Dec 20 13:12:37 CET 2019
 */

package fr.utt.lo02.xfmv.jest.vue.graphicInterface;

import javax.swing.*;

import fr.utt.lo02.xfmv.jest.controller.Partie;
import net.miginfocom.swing.*;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.BlockingQueue;

/**
 * @author unknown
 */
public class GUI extends JFrame implements Runnable, Observer {

    private BlockingQueue<Integer> queue;


    public GUI(BlockingQueue<Integer> queue) {
        this.queue = queue;
        initComponents();
        Partie.getInstance().addObserver(this);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]"));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    @Override
    public void run() {
        this.setVisible(true); //maj l'affichage
        this.getContentPane().add(new Menu(this.queue));
        this.pack();

    }

    @Override
    public void update(Observable observable, Object o) {

        if (observable instanceof Partie && ((Partie) observable).isStarted() && !((Partie) observable).isSetup()) {
            this.getContentPane().removeAll();
            this.getContentPane().add(new GameConfig());
            this.pack();
            System.out.println("aa");
        }
        if (observable instanceof Partie && ((Partie) observable).isStarted() && ((Partie) observable).isSetup()) {
            this.getContentPane().removeAll();
            this.getContentPane().add(new Game());
            this.pack();
        }
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
