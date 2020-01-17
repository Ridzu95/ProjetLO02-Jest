package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.vue.GUI.GameConfig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class for the Game Configuration UI.
 * Its constructor adds an event listener that set the game settings to the values entered in GUI.
 */

public class GameConfigController {

    public GameConfigController(GameConfig gameConfig) {

        gameConfig.getButtonStartGame().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (((Integer) gameConfig.getSpinnerPlayers().getValue()).intValue() >= ((Integer) gameConfig.getSpinnerRealPlayers().getValue()).intValue()) {
                    try {
                        Game.getInstance().getQueue().put(((Integer) gameConfig.getSpinnerPlayers().getValue()).intValue());
                        Game.getInstance().getQueue().put(((Integer) gameConfig.getSpinnerRealPlayers().getValue()).intValue());
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }


                    switch ((String) gameConfig.getSpinnerVariant().getValue()) {
                        case "Normale":
                            try {
                                Game.getInstance().getQueue().put(1);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            break;
                        case "Aléatoire":
                            try {
                                Game.getInstance().getQueue().put(2);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }

                            break;
                        case "Caché":
                            try {
                                Game.getInstance().getQueue().put(3);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }

                            break;
                    }
                }
            }
        });

    }

}
