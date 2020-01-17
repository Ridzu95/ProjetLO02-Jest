package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.player.Player;
import fr.utt.lo02.xfmv.jest.vue.GUI.GameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the in-game GUI.
 * Adds events listeners for cards on the board.
 * If the card is active and depending on the game phase, clicking on a card sends an integer-type message in the game message queue (from 1 to 4).
 * It was made this way to allow people to play in CLI and GUI at the same time.
 */

public class GameGUIController {

    public GameGUIController(GameGUI gameGUI) {

        for (Player player : Game.getInstance().getPlayers()) {
            for (JButton cardButton : player.getGuiCards()) {

                cardButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (Game.getInstance().getGamePhase() == "hiding") {
                            if (cardButton == player.getGuiCards().get(0)) {
                                try {
                                    Game.getInstance().getQueue().put(1);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            else if (cardButton == player.getGuiCards().get(1)) {
                                try {
                                    Game.getInstance().getQueue().put(2);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }

                            }
                        }
                        else if (Game.getInstance().getGamePhase() == "jesting") {

                            if (Game.getInstance().getSelectCards().size() == 2) {
                                if (cardButton == player.getGuiCards().get(0)) {
                                    try {
                                        Game.getInstance().getQueue().put(1);
                                    } catch (InterruptedException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                else if (cardButton == player.getGuiCards().get(1)) {
                                    try {
                                        Game.getInstance().getQueue().put(2);
                                    } catch (InterruptedException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                            else if (Game.getInstance().getSelectCards().size() == 4) {
                                if (player == Game.getInstance().getSelectPlayers().get(0)) {
                                    if (cardButton == player.getGuiCards().get(0)) {
                                        try {
                                            Game.getInstance().getQueue().put(1);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                    else if (cardButton == player.getGuiCards().get(1)) {
                                        try {
                                            Game.getInstance().getQueue().put(2);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                                else if (player == Game.getInstance().getSelectPlayers().get(2)) {
                                    if (cardButton == player.getGuiCards().get(0)) {
                                        try {
                                            Game.getInstance().getQueue().put(3);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                    else if (cardButton == player.getGuiCards().get(1)) {
                                        try {
                                            Game.getInstance().getQueue().put(4);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                            }
                            else if (Game.getInstance().getSelectCards().size() == 6) {
                                if (player == Game.getInstance().getSelectPlayers().get(0)) {
                                    if (cardButton == player.getGuiCards().get(0)) {
                                        try {
                                            Game.getInstance().getQueue().put(1);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                    else if (cardButton == player.getGuiCards().get(1)) {
                                        try {
                                            Game.getInstance().getQueue().put(2);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                                else if (player == Game.getInstance().getSelectPlayers().get(2)) {
                                    if (cardButton == player.getGuiCards().get(0)) {
                                        try {
                                            Game.getInstance().getQueue().put(3);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                    else if (cardButton == player.getGuiCards().get(1)) {
                                        try {
                                            Game.getInstance().getQueue().put(4);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                                else if (player == Game.getInstance().getSelectPlayers().get(4)) {
                                    if (cardButton == player.getGuiCards().get(0)) {
                                        try {
                                            Game.getInstance().getQueue().put(5);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                    else if (cardButton == player.getGuiCards().get(1)) {
                                        try {
                                            Game.getInstance().getQueue().put(6);
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
