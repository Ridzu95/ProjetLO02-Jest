package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.joueurs.JoueurReel;
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {

    public GameController(Game game) {

        game.getNextPhase().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (Partie.getInstance().getGamePhase() == "sélection de la carte à cacher" && Partie.getInstance().checkCardsStates()) {
                    Partie.getInstance().setHidingPhasePlayed(true);

                }
            }
        });

        game.getPlayer1Card1().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (Partie.getInstance().getGamePhase() == "sélection de la carte à cacher") {
                    if (Partie.getInstance().getJoueurs().get(0) instanceof JoueurReel && Partie.getInstance().getJoueurs().get(0).getHasPlayed() == false) {
                        if (Partie.getInstance().getJoueurs().get(0).getMain().get(0).getVisible() == true) {
                            Partie.getInstance().getJoueurs().get(0).getMain().get(0).setVisible(false);
                        }
                        else {
                            Partie.getInstance().getJoueurs().get(0).getMain().get(0).setVisible(true);
                        }
                        game.getPlayer1Card1().setText(Partie.getInstance().getJoueurs().get(0).getMain().get(0).toString());
                        game.getPlayer1Card1().revalidate();
                    }
                }
            }
        });

        game.getPlayer1Card2().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (Partie.getInstance().getGamePhase() == "sélection de la carte à cacher") {
                    if (Partie.getInstance().getJoueurs().get(0) instanceof JoueurReel && Partie.getInstance().getJoueurs().get(0).getHasPlayed() == false) {
                        if (Partie.getInstance().getJoueurs().get(0).getMain().get(1).getVisible() == true) {
                            Partie.getInstance().getJoueurs().get(0).getMain().get(1).setVisible(false);
                        }
                        else {
                            Partie.getInstance().getJoueurs().get(0).getMain().get(1).setVisible(true);
                        }
                        game.getPlayer1Card2().setText(Partie.getInstance().getJoueurs().get(0).getMain().get(1).toString());
                        game.getPlayer1Card2().revalidate();
                    }
                }
            }
        });

        game.getPlayer2Card1().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (Partie.getInstance().getGamePhase() == "sélection de la carte à cacher") {
                    if (Partie.getInstance().getJoueurs().get(1) instanceof JoueurReel && Partie.getInstance().getJoueurs().get(1).getHasPlayed() == false) {
                        if (Partie.getInstance().getJoueurs().get(1).getMain().get(0).getVisible() == true) {
                            Partie.getInstance().getJoueurs().get(1).getMain().get(0).setVisible(false);
                        }
                        else {
                            Partie.getInstance().getJoueurs().get(1).getMain().get(0).setVisible(true);
                        }
                        game.getPlayer2Card1().setText(Partie.getInstance().getJoueurs().get(1).getMain().get(0).toString());
                        game.getPlayer2Card1().revalidate();
                    }
                }
            }
        });

        game.getPlayer2Card2().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (Partie.getInstance().getGamePhase() == "sélection de la carte à cacher") {
                    if (Partie.getInstance().getJoueurs().get(1) instanceof JoueurReel && Partie.getInstance().getJoueurs().get(1).getHasPlayed() == false) {
                        if (Partie.getInstance().getJoueurs().get(1).getMain().get(1).getVisible() == true) {
                            Partie.getInstance().getJoueurs().get(1).getMain().get(1).setVisible(false);
                        }
                        else {
                            Partie.getInstance().getJoueurs().get(1).getMain().get(1).setVisible(true);
                        }
                        game.getPlayer2Card2().setText(Partie.getInstance().getJoueurs().get(1).getMain().get(1).toString());
                        game.getPlayer2Card2().revalidate();
                    }
                }
            }
        });

        game.getPlayer3Card1().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (Partie.getInstance().getGamePhase() == "sélection de la carte à cacher") {
                    if (Partie.getInstance().getJoueurs().get(2) instanceof JoueurReel && Partie.getInstance().getJoueurs().get(2).getHasPlayed() == false) {
                        if (Partie.getInstance().getJoueurs().get(2).getMain().get(0).getVisible() == true) {
                            Partie.getInstance().getJoueurs().get(2).getMain().get(0).setVisible(false);
                        }
                        else {
                            Partie.getInstance().getJoueurs().get(2).getMain().get(0).setVisible(true);
                        }
                        game.getPlayer3Card1().setText(Partie.getInstance().getJoueurs().get(2).getMain().get(0).toString());
                        game.getPlayer3Card1().revalidate();
                    }
                }
            }
        });

        game.getPlayer3Card2().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (Partie.getInstance().getGamePhase() == "sélection de la carte à cacher") {
                    if (Partie.getInstance().getJoueurs().get(2) instanceof JoueurReel && Partie.getInstance().getJoueurs().get(2).getHasPlayed() == false) {
                        if (Partie.getInstance().getJoueurs().get(2).getMain().get(1).getVisible() == true) {
                            Partie.getInstance().getJoueurs().get(2).getMain().get(1).setVisible(false);
                        }
                        else {
                            Partie.getInstance().getJoueurs().get(2).getMain().get(1).setVisible(true);
                        }
                        game.getPlayer3Card2().setText(Partie.getInstance().getJoueurs().get(2).getMain().get(1).toString());
                        game.getPlayer3Card2().revalidate();
                    }
                }
            }
        });

    }

}
