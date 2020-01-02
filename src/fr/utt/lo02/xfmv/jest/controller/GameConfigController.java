package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.variantes.Variante1;
import fr.utt.lo02.xfmv.jest.model.variantes.Variante2;
import fr.utt.lo02.xfmv.jest.model.variantes.Variantebase;
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.GameConfig;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class GameConfigController {

    public GameConfigController(GameConfig gameConfig) {

        gameConfig.getBeginGameButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Partie.getInstance().setPlayerCount(((Integer) gameConfig.getPlayersCount().getValue()).intValue());
                Partie.getInstance().setRealPlayerCount(((Integer) gameConfig.getRealPlayersCount().getValue()).intValue());

                switch ((String) gameConfig.getVarianteSelect().getValue()) {
                    case "Normal":
                        Partie.getInstance().setVariante(new Variantebase());
                        break;
                    case "Aléatoire":
                        Partie.getInstance().setVariante(new Variante1());
                        break;
                    case "Caché":
                        Partie.getInstance().setVariante(new Variante2());
                        break;
                }

                Partie.getInstance().setSetup(true);

            }
        });

    }

}
