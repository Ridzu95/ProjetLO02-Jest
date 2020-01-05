/*
 * Created by JFormDesigner on Wed Jan 01 12:48:27 CET 2020
 */

package fr.utt.lo02.xfmv.jest.vue.graphicInterface;

import javax.swing.*;

import fr.utt.lo02.xfmv.jest.controller.GameController;
import fr.utt.lo02.xfmv.jest.controller.Partie;
import net.miginfocom.swing.*;

import java.util.Observable;
import java.util.Observer;

/**
 * @author unknown
 */
public class Game extends JPanel implements Observer {
    public Game() {
        initComponents();
        Partie.getInstance().addObserver(this);
        GameController gameController = new GameController(this);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label3 = new JLabel();
        label2 = new JLabel();
        phaseTextLabel = new JLabel();
        player1Card1 = new JButton();
        player1Card2 = new JButton();
        player2Card1 = new JButton();
        player2Card2 = new JButton();
        player3Card1 = new JButton();
        player3Card2 = new JButton();
        player1Label = new JLabel();
        player2Label = new JLabel();
        player2Label2 = new JLabel();
        button5 = new JButton();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
        border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER
        ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font
        .BOLD,12),java.awt.Color.red), getBorder())); addPropertyChangeListener(
        new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r"
        .equals(e.getPropertyName()))throw new RuntimeException();}});
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label3 ----
        label3.setText("Tour");
        add(label3, "cell 5 1 8 1,alignx center,growx 0");

        //---- label2 ----
        label2.setText("Nombre de cartes pioche");
        add(label2, "cell 5 2 8 1,alignx center,growx 0");

        //---- phaseTextLabel ----
        phaseTextLabel.setText("phaseText");
        add(phaseTextLabel, "cell 5 3 8 1,alignx center,growx 0");

        //---- player1Card1 ----
        player1Card1.setText("Carte 1");
        add(player1Card1, "cell 5 5");

        //---- player1Card2 ----
        player1Card2.setText("Carte 2");
        add(player1Card2, "cell 6 5");

        //---- player2Card1 ----
        player2Card1.setText("Carte 1");
        add(player2Card1, "cell 8 5");

        //---- player2Card2 ----
        player2Card2.setText("Carte 2");
        add(player2Card2, "cell 9 5");

        //---- player3Card1 ----
        player3Card1.setText("Carte 1");
        add(player3Card1, "cell 11 5");

        //---- player3Card2 ----
        player3Card2.setText("Carte 2");
        add(player3Card2, "cell 12 5");

        //---- player1Label ----
        player1Label.setText("Joueur 1");
        add(player1Label, "cell 5 6 2 1,alignx center,growx 0");

        //---- player2Label ----
        player2Label.setText("Joueur 2");
        add(player2Label, "cell 8 6 2 1,alignx center,growx 0");

        //---- player2Label2 ----
        player2Label2.setText("Joueur 3");
        add(player2Label2, "cell 11 6 2 1,alignx center,growx 0");

        //---- button5 ----
        button5.setText("Phase suivante");
        add(button5, "cell 8 8 2 1");
        //GEN-END:initComponents
    }



    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label3;
    private JLabel label2;
    private JLabel phaseTextLabel;
    private JButton player1Card1;
    private JButton player1Card2;
    private JButton player2Card1;
    private JButton player2Card2;
    private JButton player3Card1;
    private JButton player3Card2;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel player2Label2;
    private JButton button5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JLabel getLabel3() {
        return label3;
    }

    public void setLabel3(JLabel label3) {
        this.label3 = label3;
    }

    @Override
    public void update(Observable observable, Object o) {
        this.revalidate();
    }
}
