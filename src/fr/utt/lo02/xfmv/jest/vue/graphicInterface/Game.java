/*
 * Created by JFormDesigner on Wed Jan 01 12:48:27 CET 2020
 */

package fr.utt.lo02.xfmv.jest.vue.graphicInterface;

import java.awt.*;
import javax.swing.*;

import fr.utt.lo02.xfmv.jest.controller.GameController;
import fr.utt.lo02.xfmv.jest.controller.Partie;
import net.miginfocom.swing.*;

import java.util.Observable;
import java.util.Observer;

/**
 * @author unknown
 */
public class Game extends JPanel{
    public Game() {
        initComponents();
        GameController gameController = new GameController(this);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        header = new JLabel();
        labelTurn = new JLabel();
        pioche = new JLabel();
        trophy1 = new JLabel();
        trophy2 = new JLabel();
        labelPioche = new JLabel();
        labelTrophies = new JLabel();
        labelWhosPlaying = new JLabel();
        labelPhase = new JLabel();
        player1Card1 = new JLabel();
        player1Card2 = new JLabel();
        player2Card1 = new JLabel();
        player2Card2 = new JLabel();
        player3Card1 = new JLabel();
        player3Card2 = new JLabel();
        player1Label = new JLabel();
        player2Label = new JLabel();
        player3Label = new JLabel();

        //======== this ========
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border
        .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax
        . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,
        12 ) ,java . awt. Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans
        .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e.
        getPropertyName () ) )throw new RuntimeException( ) ;} } );
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "0[grow,sizegroup 1,fill]" +
            "[grow,sizegroup 1,fill]" +
            "[grow,sizegroup 1,fill]" +
            "[grow,sizegroup 1,fill]" +
            "[grow,sizegroup 1,fill]" +
            "[127,grow,sizegroup 1,fill]0",
            // rows
            "0[]20" +
            "[]" +
            "[]" +
            "[44]" +
            "[]" +
            "[]" +
            "[16]" +
            "[]"));

        //---- header ----
        header.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/Rules.png"));
        add(header, "cell 0 0 6 1");

        //---- labelTurn ----
        labelTurn.setText("Tour 1");
        labelTurn.setFont(new Font("Ubuntu Light", Font.BOLD, 22));
        add(labelTurn, "cell 0 1 2 1,alignx center,growx 0");

        //---- pioche ----
        pioche.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
        add(pioche, "cell 2 1 2 1,alignx center,growx 0");

        //---- trophy1 ----
        trophy1.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/4Pique.png"));
        add(trophy1, "cell 4 1,alignx right,growx 0");

        //---- trophy2 ----
        trophy2.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/3Carreau.png"));
        add(trophy2, "cell 5 1,alignx left,growx 0");

        //---- labelPioche ----
        labelPioche.setText("9 cartes dans la pioche");
        labelPioche.setFont(new Font("Ubuntu Light", Font.BOLD | Font.ITALIC, 10));
        add(labelPioche, "cell 2 2 2 1,alignx center,growx 0");

        //---- labelTrophies ----
        labelTrophies.setText("Troph\u00e9es de la partie");
        labelTrophies.setFont(new Font("Ubuntu Light", Font.BOLD | Font.ITALIC, 10));
        add(labelTrophies, "cell 4 2 2 1,alignx center,growx 0");

        //---- labelWhosPlaying ----
        labelWhosPlaying.setText("C'est \u00e0 Player 1 de jouer !");
        labelWhosPlaying.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
        add(labelWhosPlaying, "cell 1 3 4 1,align center bottom,grow 0 0");

        //---- labelPhase ----
        labelPhase.setText("Clique sur la carte que tu souhaites cacher.");
        labelPhase.setFont(new Font("Ubuntu Light", Font.BOLD | Font.ITALIC, 16));
        add(labelPhase, "cell 1 4 4 1,alignx center,growx 0");

        //---- player1Card1 ----
        player1Card1.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/2Pique.png"));
        add(player1Card1, "cell 0 5,alignx right,growx 0");

        //---- player1Card2 ----
        player1Card2.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/APique.png"));
        add(player1Card2, "cell 1 5,alignx left,growx 0");

        //---- player2Card1 ----
        player2Card1.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
        add(player2Card1, "cell 2 5,alignx right,growx 0");

        //---- player2Card2 ----
        player2Card2.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
        add(player2Card2, "cell 3 5,alignx left,growx 0");

        //---- player3Card1 ----
        player3Card1.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
        add(player3Card1, "cell 4 5,alignx right,growx 0");

        //---- player3Card2 ----
        player3Card2.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
        add(player3Card2, "cell 5 5,alignx left,growx 0");

        //---- player1Label ----
        player1Label.setText("Player 1");
        add(player1Label, "cell 0 6 2 1,alignx center,growx 0");

        //---- player2Label ----
        player2Label.setText("Player 2");
        add(player2Label, "cell 2 6 2 1,alignx center,growx 0");

        //---- player3Label ----
        player3Label.setText("Player 3");
        add(player3Label, "cell 4 6 2 1,alignx center,growx 0");
        //GEN-END:initComponents
    }



    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel header;
    private JLabel labelTurn;
    private JLabel pioche;
    private JLabel trophy1;
    private JLabel trophy2;
    private JLabel labelPioche;
    private JLabel labelTrophies;
    private JLabel labelWhosPlaying;
    private JLabel labelPhase;
    private JLabel player1Card1;
    private JLabel player1Card2;
    private JLabel player2Card1;
    private JLabel player2Card2;
    private JLabel player3Card1;
    private JLabel player3Card2;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel player3Label;
    // JFormDesigner - End of variables declaration  //GEN-END:variables




}
