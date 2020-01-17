/*
 * Created by JFormDesigner on Wed Jan 01 12:48:27 CET 2020
 */

package fr.utt.lo02.xfmv.jest.vue.GUI;

import java.awt.*;
import javax.swing.*;

import fr.utt.lo02.xfmv.jest.controller.Game;
import fr.utt.lo02.xfmv.jest.controller.GameGUIController;
import fr.utt.lo02.xfmv.jest.model.card.Card;
import fr.utt.lo02.xfmv.jest.model.player.Player;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class GameGUI extends JPanel{



    public GameGUI() throws InterruptedException {
        initComponents();
        Game.getInstance().getPlayers().get(0).getGuiCards().add(this.player1Card1);
        Game.getInstance().getPlayers().get(0).getGuiCards().add(this.player1Card2);
        Game.getInstance().getPlayers().get(1).getGuiCards().add(this.player2Card1);
        Game.getInstance().getPlayers().get(1).getGuiCards().add(this.player2Card2);
        Game.getInstance().getPlayers().get(2).getGuiCards().add(this.player3Card1);
        Game.getInstance().getPlayers().get(2).getGuiCards().add(this.player3Card2);
        if (Game.getInstance().getPlayerCount() == 4) {
            Game.getInstance().getPlayers().get(3).getGuiCards().add(this.player4Card1);
            Game.getInstance().getPlayers().get(3).getGuiCards().add(this.player4Card2);
            player4Label.setText(Game.getInstance().getPlayers().get(3).toString());
        }
        player1Label.setText(Game.getInstance().getPlayers().get(0).toString());
        player2Label.setText(Game.getInstance().getPlayers().get(1).toString());
        player3Label.setText(Game.getInstance().getPlayers().get(2).toString());
        header.setIcon(new ImageIcon(getClass().getResource("/Rules.png")));
        stack.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        labelStack.setText(Game.getInstance().getBaseStack().size() + " cartes dans la pioche");
        labelWhosPlaying.setText("C'est \u00e0 " + Game.getInstance().getCurrentPlaying() + " de jouer !");
        trophy1.setIcon(new ImageIcon(getClass().getResource(this.displayCards(Game.getInstance().getGameTrophies().get(0)))));
        if (Game.getInstance().getPlayerCount() == 3) {
            trophy2.setIcon(new ImageIcon(getClass().getResource(this.displayCards(Game.getInstance().getGameTrophies().get(1)))));
        }
        GameGUIController gameGUIController = new GameGUIController(this);
        this.guiUpdate();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        header = new JLabel();
        labelTurn = new JLabel();
        stack = new JLabel();
        trophy1 = new JLabel();
        trophy2 = new JLabel();
        labelStack = new JLabel();
        labelTrophies = new JLabel();
        labelWhosPlaying = new JLabel();
        labelPhase = new JLabel();
        player1Card1 = new JButton();
        player1Card2 = new JButton();
        player2Card1 = new JButton();
        player2Card2 = new JButton();
        player3Card1 = new JButton();
        player3Card2 = new JButton();
        player1Label = new JLabel();
        player2Label = new JLabel();
        player3Label = new JLabel();
        player4Card1 = new JButton();
        player4Card2 = new JButton();
        player4Label = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(800, 600));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
        0, 0, 0, 0) , "", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
        . BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
        red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
        beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
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
            "0[]rel" +
            "[]rel" +
            "[]" +
            "[]" +
            "[44]" +
            "[]" +
            "[]" +
            "[16]" +
            "[]"));

        if (Game.getInstance().getPlayerCount() == 3) {
            //---- header ----
            header.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/Rules.png"));
            add(header, "cell 0 0 6 1,alignx center,growx 0");

            //---- labelTurn ----
            labelTurn.setText("Tour 1");
            labelTurn.setFont(new Font("Ubuntu Light", Font.BOLD, 22));
            add(labelTurn, "cell 0 2 2 1,alignx center,growx 0");

            //---- pioche ----
            stack.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
            add(stack, "cell 2 2 2 1,alignx center,growx 0");

            //---- trophy1 ----
            trophy1.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/4Pique.png"));
            add(trophy1, "cell 4 2,alignx right,growx 0");

            //---- trophy2 ----
            trophy2.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/3Carreau.png"));
            add(trophy2, "cell 5 2,alignx left,growx 0");

            //---- labelPioche ----
            labelStack.setText("9 cartes dans la pioche");
            labelStack.setFont(new Font("Ubuntu Light", Font.BOLD | Font.ITALIC, 10));
            add(labelStack, "cell 2 3 2 1,alignx center,growx 0");

            //---- labelTrophies ----
            labelTrophies.setText("Troph\u00e9es de la partie");
            labelTrophies.setFont(new Font("Ubuntu Light", Font.BOLD | Font.ITALIC, 10));
            add(labelTrophies, "cell 4 3 2 1,alignx center,growx 0");

            //---- labelWhosPlaying ----
            labelWhosPlaying.setText("C'est \u00e0 Player 1 de jouer !");
            labelWhosPlaying.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
            add(labelWhosPlaying, "cell 1 4 4 1,align center bottom,grow 0 0");

            //---- labelPhase ----
            labelPhase.setText("Clique sur la carte que tu souhaites cacher.");
            labelPhase.setFont(new Font("Ubuntu Light", Font.BOLD | Font.ITALIC, 16));
            add(labelPhase, "cell 1 5 4 1,alignx center,growx 0");

            //---- player1Card1 ----
            player1Card1.setBorder(null);
            player1Card1.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
            player1Card1.setMargin(new Insets(0, 0, 0, 0));
            player1Card1.setIconTextGap(0);
            player1Card1.setBorderPainted(false);
            player1Card1.setContentAreaFilled(false);
            player1Card1.setHorizontalAlignment(SwingConstants.RIGHT);
            player1Card1.setMaximumSize(new Dimension(109, 173));
            player1Card1.setMinimumSize(new Dimension(109, 173));
            player1Card1.setPreferredSize(new Dimension(109, 173));
            add(player1Card1, "cell 0 6,alignx right,growx 0");

            //---- player1Card2 ----
            player1Card2.setBorder(null);
            player1Card2.setBorderPainted(false);
            player1Card2.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
            player1Card2.setContentAreaFilled(false);
            player1Card2.setHorizontalAlignment(SwingConstants.LEFT);
            add(player1Card2, "cell 1 6,alignx center,growx 0");

            //---- player2Card1 ----
            player2Card1.setBorder(null);
            player2Card1.setBorderPainted(false);
            player2Card1.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
            player2Card1.setHorizontalAlignment(SwingConstants.RIGHT);
            player2Card1.setContentAreaFilled(false);
            add(player2Card1, "cell 2 6,alignx right,growx 0");

            //---- player2Card2 ----
            player2Card2.setBorder(null);
            player2Card2.setBorderPainted(false);
            player2Card2.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
            player2Card2.setHorizontalAlignment(SwingConstants.LEFT);
            player2Card2.setContentAreaFilled(false);
            add(player2Card2, "cell 3 6,alignx left,growx 0");

            //---- player3Card1 ----
            player3Card1.setBorder(null);
            player3Card1.setBorderPainted(false);
            player3Card1.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
            player3Card1.setHorizontalAlignment(SwingConstants.RIGHT);
            player3Card1.setContentAreaFilled(false);
            add(player3Card1, "cell 4 6,alignx right,growx 0");

            //---- player3Card2 ----
            player3Card2.setBorder(null);
            player3Card2.setBorderPainted(false);
            player3Card2.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
            player3Card2.setHorizontalAlignment(SwingConstants.LEFT);
            player3Card2.setContentAreaFilled(false);
            add(player3Card2, "cell 5 6,alignx left,growx 0");

            //---- player1Label ----
            player1Label.setText("Player 1");
            add(player1Label, "cell 0 7 2 1,alignx center,growx 0");

            //---- player2Label ----
            player2Label.setText("Player 2");
            add(player2Label, "cell 2 7 2 1,alignx center,growx 0");

            //---- player3Label ----
            player3Label.setText("Player 3");
            add(player3Label, "cell 4 7 2 1,alignx center,growx 0");
        }
        else {
            //---- header ----
            header.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/Rules.png"));
            add(header, "cell 0 0 6 1, alignx center, growx 0");

            //---- labelTurn ----
            labelTurn.setText("Tour 1");
            labelTurn.setFont(new Font("Ubuntu Light", Font.BOLD, 20));
            add(labelTurn, "cell 2 1 2 1,alignx center,growx 0");

            //---- player2Card3 ----
            player1Card1.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/2Pique.png"));
            add(player1Card1, "cell 0 2,alignx right,growx 0");
            player1Card1.setMargin(new Insets(0, 0, 0, 0));
            player1Card1.setIconTextGap(0);
            player1Card1.setBorderPainted(false);
            player1Card1.setContentAreaFilled(false);
            player1Card1.setHorizontalAlignment(SwingConstants.RIGHT);

            //---- player2Card4 ----
            player1Card2.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/APique.png"));
            add(player1Card2, "cell 1 2,alignx left,growx 0");
            player1Card2.setBorder(null);
            player1Card2.setBorderPainted(false);
            player1Card2.setContentAreaFilled(false);
            player1Card2.setHorizontalAlignment(SwingConstants.LEFT);

            //---- pioche ----
            stack.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
            add(stack, "cell 2 2 2 1,alignx center,growx 0");

            //---- trophy1 ----
            trophy1.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/4Pique.png"));
            add(trophy1, "cell 4 2 2 1,alignx center,growx 0");

            //---- player1Label ----
            player1Label.setText("Player 1");
            add(player1Label, "cell 0 3 2 1,alignx center,growx 0");

            //---- labelPioche ----
            labelStack.setText("9 cartes dans la pioche");
            labelStack.setFont(new Font("Ubuntu Light", Font.BOLD | Font.ITALIC, 10));
            add(labelStack, "cell 2 3 2 1,alignx center,growx 0");

            //---- labelTrophies ----
            labelTrophies.setText("Troph\u00e9es de la partie");
            labelTrophies.setFont(new Font("Ubuntu Light", Font.BOLD | Font.ITALIC, 10));
            add(labelTrophies, "cell 4 3 2 1,alignx center,growx 0");

            //---- labelWhosPlaying ----
            labelWhosPlaying.setText("C'est \u00e0 Player 1 de jouer !");
            labelWhosPlaying.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
            add(labelWhosPlaying, "cell 1 4 4 1,align center bottom,grow 0 0");

            //---- labelPhase ----
            labelPhase.setText("Clique sur la carte que tu souhaites cacher.");
            labelPhase.setFont(new Font("Ubuntu Light", Font.BOLD | Font.ITALIC, 16));
            add(labelPhase, "cell 1 5 4 1,align center top,grow 0 0");

            //---- player2Card1 ----
            player2Card1.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/2Pique.png"));
            add(player2Card1, "cell 0 6,alignx right,growx 0");
            player2Card1.setBorder(null);
            player2Card1.setBorderPainted(false);
            player2Card1.setHorizontalAlignment(SwingConstants.RIGHT);
            player2Card1.setContentAreaFilled(false);

            //---- player2Card2 ----
            player2Card2.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/APique.png"));
            add(player2Card2, "cell 1 6,alignx left,growx 0");
            player2Card2.setBorder(null);
            player2Card2.setBorderPainted(false);
            player2Card2.setHorizontalAlignment(SwingConstants.LEFT);
            player2Card2.setContentAreaFilled(false);

            //---- player3Card1 ----
            player3Card1.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
            add(player3Card1, "cell 2 6,alignx right,growx 0");
            player3Card1.setBorder(null);
            player3Card1.setBorderPainted(false);
            player3Card1.setHorizontalAlignment(SwingConstants.RIGHT);
            player3Card1.setContentAreaFilled(false);

            //---- player3Card2 ----
            player3Card2.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
            add(player3Card2, "cell 3 6,alignx left,growx 0");
            player3Card2.setBorder(null);
            player3Card2.setBorderPainted(false);
            player3Card2.setHorizontalAlignment(SwingConstants.LEFT);
            player3Card2.setContentAreaFilled(false);

            //---- player4Card1 ----
            player4Card1.setBorder(null);
            player4Card1.setBorderPainted(false);
            player4Card1.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
            player4Card1.setHorizontalAlignment(SwingConstants.RIGHT);
            player4Card1.setContentAreaFilled(false);
            add(player4Card1, "cell 4 6,alignx right,growx 0");

            //---- player4Card2 ----
            player4Card2.setBorder(null);
            player4Card2.setBorderPainted(false);
            player4Card2.setIcon(new ImageIcon("/home/ridzu/dev/ProjetLO02-Jest/resources/CardBack.png"));
            player4Card2.setHorizontalAlignment(SwingConstants.LEFT);
            player4Card2.setContentAreaFilled(false);
            add(player4Card2, "cell 5 6,alignx left,growx 0");

            //---- player2Label ----
            player2Label.setText("Player 2");
            add(player2Label, "cell 0 7 2 1,alignx center,growx 0");

            //---- player3Label ----
            player3Label.setText("Player 3");
            add(player3Label, "cell 2 7 2 1,alignx center,growx 0");

            //---- player4Label ----
            player4Label.setText("Player 4");
            add(player4Label, "cell 4 7 2 1,alignx center,growx 0");
        }
        //GEN-END:initComponents

    }

    public void guiUpdate() throws InterruptedException {

        labelStack.setText(Game.getInstance().getBaseStack().size() + " cartes dans la pioche");
        labelWhosPlaying.setText("C'est \u00e0 " + Game.getInstance().getCurrentPlaying() + " de jouer !");
        labelPhase.setText(this.displayPhase(Game.getInstance().getGamePhase()));
        labelTurn.setText("Tour " + Game.getInstance().getTurn());
        this.updateCards();
        this.revalidate();


        Game.getInstance().getCLI().displayUpdate();

        Game.getInstance().setReady(true);
    }

    public void updateCards() {

        if (Game.getInstance().getGamePhase() == "hiding") {
            for (Player player : Game.getInstance().getPlayers()) {

                if (player == Game.getInstance().getCurrentPlaying()) {
                    player.getGuiCards().get(0).setIcon(new ImageIcon(getClass().getResource(this.displayCards(player.getMain().get(0)))));
                    player.getGuiCards().get(1).setIcon(new ImageIcon(getClass().getResource(this.displayCards(player.getMain().get(1)))));
                    player.getGuiCards().get(0).setEnabled(true);
                    player.getGuiCards().get(1).setEnabled(true);
                } else {
                    player.getGuiCards().get(0).setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
                    player.getGuiCards().get(1).setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
                    player.getGuiCards().get(0).setEnabled(false);
                    player.getGuiCards().get(1).setEnabled(false);
                }

            }

        }
        else if (Game.getInstance().getGamePhase() == "jesting") {

            for (Player player : Game.getInstance().getPlayers()) {
                player.getGuiCards().get(0).setEnabled(false);
                player.getGuiCards().get(1).setEnabled(false);
                player.getGuiCards().get(0).setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
                player.getGuiCards().get(1).setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
            }
            this.revalidate();
            if (Game.getInstance().getSelectCards().size() == 2) {
                Game.getInstance().getSelectPlayers().get(0).getGuiCards().get(0).setEnabled(true);
                Game.getInstance().getSelectPlayers().get(0).getGuiCards().get(1).setEnabled(true);
                Game.getInstance().getSelectPlayers().get(0).getGuiCards().get(0).setIcon(new ImageIcon(getClass().getResource(this.displayCards(Game.getInstance().getSelectPlayers().get(0).getMain().get(0)))));
                Game.getInstance().getSelectPlayers().get(0).getGuiCards().get(1).setIcon(new ImageIcon(getClass().getResource(this.displayCards(Game.getInstance().getSelectPlayers().get(0).getMain().get(1)))));
            }
            else if (Game.getInstance().getSelectCards().size() == 4) {
                Game.getInstance().getSelectPlayers().get(0).getGuiCards().get(0).setIcon(new ImageIcon(getClass().getResource(this.displayCards(Game.getInstance().getSelectPlayers().get(0).getMain().get(0)))));
                Game.getInstance().getSelectPlayers().get(0).getGuiCards().get(1).setIcon(new ImageIcon(getClass().getResource(this.displayCards(Game.getInstance().getSelectPlayers().get(0).getMain().get(1)))));
                Game.getInstance().getSelectPlayers().get(0).getGuiCards().get(0).setEnabled(true);
                Game.getInstance().getSelectPlayers().get(0).getGuiCards().get(1).setEnabled(true);
                Game.getInstance().getSelectPlayers().get(2).getGuiCards().get(0).setIcon(new ImageIcon(getClass().getResource(this.displayCards(Game.getInstance().getSelectPlayers().get(2).getMain().get(0)))));
                Game.getInstance().getSelectPlayers().get(2).getGuiCards().get(1).setIcon(new ImageIcon(getClass().getResource(this.displayCards(Game.getInstance().getSelectPlayers().get(2).getMain().get(1)))));
                Game.getInstance().getSelectPlayers().get(2).getGuiCards().get(0).setEnabled(true);
                Game.getInstance().getSelectPlayers().get(2).getGuiCards().get(1).setEnabled(true);
            }
            else if (Game.getInstance().getSelectCards().size() == 6) {
                Game.getInstance().getSelectPlayers().get(0).getGuiCards().get(0).setIcon(new ImageIcon(getClass().getResource(this.displayCards(Game.getInstance().getSelectPlayers().get(0).getMain().get(0)))));
                Game.getInstance().getSelectPlayers().get(0).getGuiCards().get(1).setIcon(new ImageIcon(getClass().getResource(this.displayCards(Game.getInstance().getSelectPlayers().get(0).getMain().get(1)))));
                Game.getInstance().getSelectPlayers().get(0).getGuiCards().get(0).setEnabled(true);
                Game.getInstance().getSelectPlayers().get(0).getGuiCards().get(1).setEnabled(true);
                Game.getInstance().getSelectPlayers().get(2).getGuiCards().get(0).setIcon(new ImageIcon(getClass().getResource(this.displayCards(Game.getInstance().getSelectPlayers().get(2).getMain().get(0)))));
                Game.getInstance().getSelectPlayers().get(2).getGuiCards().get(1).setIcon(new ImageIcon(getClass().getResource(this.displayCards(Game.getInstance().getSelectPlayers().get(2).getMain().get(1)))));
                Game.getInstance().getSelectPlayers().get(2).getGuiCards().get(0).setEnabled(true);
                Game.getInstance().getSelectPlayers().get(2).getGuiCards().get(1).setEnabled(true);
                Game.getInstance().getSelectPlayers().get(4).getGuiCards().get(0).setIcon(new ImageIcon(getClass().getResource(this.displayCards(Game.getInstance().getSelectPlayers().get(4).getMain().get(0)))));
                Game.getInstance().getSelectPlayers().get(4).getGuiCards().get(1).setIcon(new ImageIcon(getClass().getResource(this.displayCards(Game.getInstance().getSelectPlayers().get(4).getMain().get(1)))));
                Game.getInstance().getSelectPlayers().get(4).getGuiCards().get(0).setEnabled(true);
                Game.getInstance().getSelectPlayers().get(4).getGuiCards().get(1).setEnabled(true);
            }

        }
    }

    public String displayPhase(String phase) {
        switch (phase) {
            case "hiding":
                return "Clique sur la carte que tu souhaites cacher.";
            case "jesting":
                return "Clique sur la carte que tu souhaites mettre dans ton Jest.";
            default :
                return "En attente.";
        }
    }


    public String displayCards(Card card) {

        if (card.isVisible()) {
            switch (card.toString()) {
                case "2♥":
                    return "/2Coeur.png";
                case "4♥":
                    return "/4Coeur.png";
                case "3♥":
                    return "/3Coeur.png";
                case "1♥":
                    return "/ACoeur.png";
                case "2♦":
                    return "/2Carreau.png";
                case "3♦":
                    return "/3Carreau.png";
                case "4♦":
                    return "/4Carreau.png";
                case "1♦":
                    return "/ACarreau.png";
                case "2♣":
                    return "/2Trefle.png";
                case "3♣":
                    return "/3Trefle.png";
                case "4♣":
                    return "/4Trefle.png";
                case "1♣":
                    return "/ATrefle.png";
                case "2♠":
                    return "/2Pique.png";
                case "3♠":
                    return "/3Pique.png";
                case "4♠":
                    return "/4Pique.png";
                case "1♠":
                    return "/APique.png";
                case "★":
                    return "/Joker.png";
                default:
                    return "/CardBack.png";
            }
        }
        else {
            return "/CardBack.png";
        }

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel header;
    private JLabel labelTurn;
    private JLabel stack;
    private JLabel trophy1;
    private JLabel trophy2;
    private JLabel labelStack;
    private JLabel labelTrophies;
    private JLabel labelWhosPlaying;
    private JLabel labelPhase;
    private JButton player1Card1;
    private JButton player1Card2;
    private JButton player2Card1;
    private JButton player2Card2;
    private JButton player3Card1;
    private JButton player3Card2;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel player3Label;
    private JLabel player4Label;
    private JButton player4Card1;
    private JButton player4Card2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables




}
