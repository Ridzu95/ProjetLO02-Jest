package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.joueurs.Joueur;
import fr.utt.lo02.xfmv.jest.model.joueurs.JoueurReel;
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {

    public GameController(Game game) {

        for (Joueur joueur : Partie.getInstance().getJoueurs()) {
            for (JButton cardButton : joueur.getGuiCards()) {

                cardButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (Partie.getInstance().getGamePhase() == "hiding") {
                            if (cardButton == joueur.getGuiCards().get(0)) {
                                try {
                                    Partie.getInstance().getQueue().put(1);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            else if (cardButton == joueur.getGuiCards().get(1)) {
                                try {
                                    Partie.getInstance().getQueue().put(2);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }

                            }
                        }
                        else if (Partie.getInstance().getGamePhase() == "jesting") {

                            if (Partie.getInstance().getSelectCards().size() == 2) {
                                if (cardButton == joueur.getGuiCards().get(0)) {
                                    try {
                                        Partie.getInstance().getQueue().put(1);
                                    } catch (InterruptedException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                else if (cardButton == joueur.getGuiCards().get(1)) {
                                    try {
                                        Partie.getInstance().getQueue().put(2);
                                    } catch (InterruptedException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                            else if (Partie.getInstance().getSelectCards().size() == 4) {
                                if (joueur == Partie.getInstance().getSelectJoueurs().get(0)) {
                                    if (cardButton == joueur.getGuiCards().get(0)) {
                                        try {
                                            Partie.getInstance().getQueue().put(1);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                    else if (cardButton == joueur.getGuiCards().get(1)) {
                                        try {
                                            Partie.getInstance().getQueue().put(2);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                                else if (joueur == Partie.getInstance().getSelectJoueurs().get(2)) {
                                    if (cardButton == joueur.getGuiCards().get(0)) {
                                        try {
                                            Partie.getInstance().getQueue().put(3);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                    else if (cardButton == joueur.getGuiCards().get(1)) {
                                        try {
                                            Partie.getInstance().getQueue().put(4);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                            }
                            else if (Partie.getInstance().getSelectCards().size() == 6) {
                                if (joueur == Partie.getInstance().getSelectJoueurs().get(0)) {
                                    if (cardButton == joueur.getGuiCards().get(0)) {
                                        try {
                                            Partie.getInstance().getQueue().put(1);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                    else if (cardButton == joueur.getGuiCards().get(1)) {
                                        try {
                                            Partie.getInstance().getQueue().put(2);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                                else if (joueur == Partie.getInstance().getSelectJoueurs().get(2)) {
                                    if (cardButton == joueur.getGuiCards().get(0)) {
                                        try {
                                            Partie.getInstance().getQueue().put(3);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                    else if (cardButton == joueur.getGuiCards().get(1)) {
                                        try {
                                            Partie.getInstance().getQueue().put(4);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                                else if (joueur == Partie.getInstance().getSelectJoueurs().get(4)) {
                                    if (cardButton == joueur.getGuiCards().get(0)) {
                                        try {
                                            Partie.getInstance().getQueue().put(5);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                    else if (cardButton == joueur.getGuiCards().get(1)) {
                                        try {
                                            Partie.getInstance().getQueue().put(6);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    }
                });

            }
        }

    }

}
