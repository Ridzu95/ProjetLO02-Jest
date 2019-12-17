/*
 * Created by JFormDesigner on Tue Dec 17 12:53:56 CET 2019
 */

package fr.utt.lo02.xfmv.jest.vue.graphicInterface;

import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import fr.utt.lo02.xfmv.jest.controller.MenuController;
import fr.utt.lo02.xfmv.jest.controller.Partie;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class Menu extends JFrame implements Observer {

    public Menu() {
        initComponents();
        MenuController menuController = new MenuController(playButton, rulesButton, quitButton);
        Partie.getInstance().addObserver(this);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        title = new JLabel();
        playButton = new JButton();
        rulesButton = new JButton();
        quitButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- title ----
        title.setText("Jest");
        contentPane.add(title, "cell 0 0,alignx center,growx 0");

        //---- playButton ----
        playButton.setText("Jouer");
        contentPane.add(playButton, "cell 0 2,alignx center,growx 0");

        //---- rulesButton ----
        rulesButton.setText("R\u00e8gles");
        contentPane.add(rulesButton, "cell 0 3,alignx center,growx 0");

        //---- quitButton ----
        quitButton.setText("Quitter");
        contentPane.add(quitButton, "cell 0 4,alignx center,growx 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel title;
    private JButton playButton;
    private JButton rulesButton;
    private JButton quitButton;

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("UPDATE");
        if (observable instanceof Partie) {
            if (((Partie) observable).isStarted() == true) {
                this.setVisible(false);
                this.dispose();
            }
        }
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
