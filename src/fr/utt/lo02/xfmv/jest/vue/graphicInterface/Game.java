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
        nextPhase = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
        EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing
        . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
        java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
        { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () ))
        throw new RuntimeException( ); }} );
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
        label3.setText("Tour " + Partie.getInstance().getTour());
        add(label3, "cell 5 1 8 1,alignx center,growx 0");

        //---- phaseTextLabel ----
        phaseTextLabel.setText("Phase : " + Partie.getInstance().getGamePhase());
        add(phaseTextLabel, "cell 5 3 8 1,alignx center,growx 0");

        //---- label2 ----
        label2.setText(Partie.getInstance().getBasePioche().size() + " cartes dans la pioche");
        add(label2, "cell 5 2 8 1,alignx center,growx 0");

        //---- player1Card1 ----
        player1Card1.setText(Partie.getInstance().getJoueurs().get(0).getMain().get(0).toString());
        add(player1Card1, "cell 5 4");

        //---- player1Card2 ----
        player1Card2.setText(Partie.getInstance().getJoueurs().get(0).getMain().get(1).toString());
        add(player1Card2, "cell 6 4");

        //---- player2Card1 ----
        player2Card1.setText(Partie.getInstance().getJoueurs().get(1).getMain().get(0).toString());
        add(player2Card1, "cell 8 4");

        //---- player2Card2 ----
        player2Card2.setText(Partie.getInstance().getJoueurs().get(1).getMain().get(1).toString());
        add(player2Card2, "cell 9 4");

        //---- player3Card1 ----
        player3Card1.setText(Partie.getInstance().getJoueurs().get(2).getMain().get(0).toString());
        add(player3Card1, "cell 11 4");

        //---- player3Card2 ----
        player3Card2.setText(Partie.getInstance().getJoueurs().get(2).getMain().get(1).toString());
        add(player3Card2, "cell 12 4");

        //---- player1Label ----
        player1Label.setText(Partie.getInstance().getJoueurs().get(0).toString());
        add(player1Label, "cell 5 5 2 1,alignx center,growx 0");

        //---- player2Label ----
        player2Label.setText(Partie.getInstance().getJoueurs().get(1).toString());
        add(player2Label, "cell 8 5 2 1,alignx center,growx 0");

        //---- player2Label2 ----
        player2Label2.setText(Partie.getInstance().getJoueurs().get(2).toString());
        add(player2Label2, "cell 11 5 2 1,alignx center,growx 0");

        //---- button5 ----
        nextPhase.setText("Phase suivante");
        add(nextPhase, "cell 8 8 2 1");
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
    private JButton nextPhase;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JLabel getLabel3() {
        return label3;
    }

    public void setLabel3(JLabel label3) {
        this.label3 = label3;
    }

    public JLabel getPhaseTextLabel() {
        return phaseTextLabel;
    }

    public void setPhaseTextLabel(JLabel phaseTextLabel) {
        this.phaseTextLabel = phaseTextLabel;
    }

    public JLabel getLabel2() {
        return label2;
    }

    public void setLabel2(JLabel label2) {
        this.label2 = label2;
    }

    public JButton getPlayer1Card1() {
        return player1Card1;
    }

    public void setPlayer1Card1(JButton player1Card1) {
        this.player1Card1 = player1Card1;
    }

    public JButton getPlayer1Card2() {
        return player1Card2;
    }

    public void setPlayer1Card2(JButton player1Card2) {
        this.player1Card2 = player1Card2;
    }

    public JButton getPlayer2Card1() {
        return player2Card1;
    }

    public void setPlayer2Card1(JButton player2Card1) {
        this.player2Card1 = player2Card1;
    }

    public JButton getPlayer2Card2() {
        return player2Card2;
    }

    public void setPlayer2Card2(JButton player2Card2) {
        this.player2Card2 = player2Card2;
    }

    public JButton getPlayer3Card1() {
        return player3Card1;
    }

    public void setPlayer3Card1(JButton player3Card1) {
        this.player3Card1 = player3Card1;
    }

    public JButton getPlayer3Card2() {
        return player3Card2;
    }

    public void setPlayer3Card2(JButton player3Card2) {
        this.player3Card2 = player3Card2;
    }

    public JLabel getPlayer1Label() {
        return player1Label;
    }

    public void setPlayer1Label(JLabel player1Label) {
        this.player1Label = player1Label;
    }

    public JLabel getPlayer2Label() {
        return player2Label;
    }

    public void setPlayer2Label(JLabel player2Label) {
        this.player2Label = player2Label;
    }

    public JLabel getPlayer2Label2() {
        return player2Label2;
    }

    public void setPlayer2Label2(JLabel player2Label2) {
        this.player2Label2 = player2Label2;
    }

    public JButton getNextPhase() {
        return nextPhase;
    }

    public void setNextPhase(JButton nextPhase) {
        this.nextPhase = nextPhase;
    }

    @Override
    public void update(Observable observable, Object o) {
        this.revalidate();
    }
}
