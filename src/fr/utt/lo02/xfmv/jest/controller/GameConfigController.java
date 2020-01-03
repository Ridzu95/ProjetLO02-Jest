package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.variantes.Variante1;
import fr.utt.lo02.xfmv.jest.model.variantes.Variante2;
import fr.utt.lo02.xfmv.jest.model.variantes.Variantebase;
import fr.utt.lo02.xfmv.jest.vue.Message;
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.GameConfig;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class GameConfigController {

    public GameConfigController(GameConfig gameConfig) {

        gameConfig.getBeginGameButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    Partie.getInstance().getQueue().put(new Message("nbplayer", ((Integer) gameConfig.getPlayersCount().getValue()).intValue() ) );
                    System.out.println("Le nombre de joueurs a été envoyé");
                    Partie.getInstance().getQueue().put(new Message("nbrealplayer", ((Integer) gameConfig.getRealPlayersCount().getValue()).intValue() ));
                    System.out.println("Le nombre de joueurs réels a été envoyé");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                try {
                    Partie.getInstance().getQueue().put(new Message("variante", ((String) gameConfig.getVarianteSelect().getValue()) ));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                /*
                switch ((String) gameConfig.getVarianteSelect().getValue()) {
                    case "Normal":
                        try {
                            Partie.getInstance().getQueue().put(new Message("variante", 0));
                            System.out.println("La variante a été envoyée");
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        //Partie.getInstance().setVariante(new Variantebase());
                        break;
                    case "Aléatoire":
                        Partie.getInstance().setVariante(new Variante1());
                        break;
                    case "Caché":
                        Partie.getInstance().setVariante(new Variante2());
                        break;
                } */

                //Partie.getInstance().setSetup(true);
                //à faire dans Partie
            }
        });

    }

}
