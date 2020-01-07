/*
 * Created by JFormDesigner on Mon Jan 06 20:57:34 CET 2020
 */

package fr.utt.lo02.xfmv.jest.vue.graphicInterface;

import java.awt.*;
import javax.swing.*;

import fr.utt.lo02.xfmv.jest.controller.Partie;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class Results extends JPanel {
    public Results() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        header = new JLabel();
        player1Label = new JLabel();
        label1 = new JLabel();
        player2Label = new JLabel();
        label2 = new JLabel();
        playe3Label = new JLabel();
        label3 = new JLabel();
        player4Label = new JLabel();
        label4 = new JLabel();
        winnerLabel = new JLabel();
        menuButton = new JButton();

        //======== this ========
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
        swing. border. EmptyBorder( 0, 0, 0, 0) , "", javax. swing. border
        . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog"
        ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) , getBorder
        ( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
        .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException
        ( ); }} );
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "0[160,fill]0" +
            "[160,fill]0" +
            "[160,fill]0" +
            "[160,fill]0" +
            "[160,fill]0" +
            "[160,fill]",
            // rows
            "0[]70" +
            "[]" +
            "[]para" +
            "[]" +
            "[]para" +
            "[]" +
            "[]para" +
            "[]rel" +
            "[]30" +
            "[]" +
            "[]" +
            "[]"));

        //---- header ----
        header.setIcon(new ImageIcon(getClass().getResource("/Results.png")));
        add(header, "cell 0 0 5 1");

        //---- player1Label ----
        player1Label.setText(Partie.getInstance().getJoueurs().get(0).toString() + " - " + Partie.getInstance().getJoueurs().get(0).getScore() + " Points");
        player1Label.setFont(new Font("Ubuntu Light", Font.BOLD, 18));
        add(player1Label, "cell 0 1 5 1,alignx center,growx 0");

        //---- label1 ----
        label1.setText(Partie.getInstance().getJoueurs().get(0).getJest().toString());
        add(label1, "cell 2 2,alignx center,growx 0");

        //---- player2Label ----
        player2Label.setText(Partie.getInstance().getJoueurs().get(1).toString() + " - " + Partie.getInstance().getJoueurs().get(1).getScore() + " Points");
        player2Label.setFont(new Font("Ubuntu Light", Font.BOLD, 18));
        add(player2Label, "cell 0 3 5 1,alignx center,growx 0");

        //---- label2 ----
        label2.setText(Partie.getInstance().getJoueurs().get(1).getJest().toString());
        add(label2, "cell 2 4,alignx center,growx 0");

        //---- playe3Label ----
        playe3Label.setText(Partie.getInstance().getJoueurs().get(2).toString() + " - " + Partie.getInstance().getJoueurs().get(2).getScore() + " Points");
        playe3Label.setFont(new Font("Ubuntu Light", Font.BOLD, 18));
        add(playe3Label, "cell 0 5 5 1,alignx center,growx 0");

        //---- label3 ----
        label3.setText(Partie.getInstance().getJoueurs().get(2).getJest().toString());
        add(label3, "cell 2 6,alignx center,growx 0");

        if (Partie.getInstance().getPlayerCount() == 4) {
            //---- player4Label ----
            player4Label.setText(Partie.getInstance().getJoueurs().get(3).toString() + " - " + Partie.getInstance().getJoueurs().get(3).getScore() + " Points");
            player4Label.setFont(new Font("Ubuntu Light", Font.BOLD, 18));
            add(player4Label, "cell 2 7,alignx center,growx 0");

            //---- label4 ----
            label4.setText(Partie.getInstance().getJoueurs().get(3).getJest().toString());
            add(label4, "cell 2 8,alignx center,growx 0");
        }


        //---- winnerLabel ----
        winnerLabel.setText(Partie.getInstance().getGameWinner().toString() + " a remporté la partie !");
        winnerLabel.setFont(new Font("Ubuntu Light", Font.BOLD, 26));
        add(winnerLabel, "cell 0 9 5 1,alignx center,growx 0");

        //---- menuButton ----
        menuButton.setText("Retour au menu");
        menuButton.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
        add(menuButton, "cell 1 10 3 1,align center top,grow 0 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel header;
    private JLabel player1Label;
    private JLabel label1;
    private JLabel player2Label;
    private JLabel label2;
    private JLabel playe3Label;
    private JLabel label3;
    private JLabel player4Label;
    private JLabel label4;
    private JLabel winnerLabel;
    private JButton menuButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JButton getMenuButton() {
        return menuButton;
    }

    public void setMenuButton(JButton menuButton) {
        this.menuButton = menuButton;
    }
}
