/*
 * Created by JFormDesigner on Thu Dec 19 13:31:42 CET 2019
 */

package fr.utt.lo02.xfmv.jest.vue.graphicInterface;

import java.awt.*;
import javax.swing.*;

import fr.utt.lo02.xfmv.jest.controller.GameConfigController;
import fr.utt.lo02.xfmv.jest.controller.Partie;
import net.miginfocom.swing.*;

import java.util.Observable;
import java.util.Observer;

/**
 * @author unknown
 */
public class GameConfig extends JPanel {

    public GameConfig() {
        initComponents();
        GameConfigController gameConfigController = new GameConfigController(this);
        this.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        header = new JLabel();
        labelPlayers = new JLabel();
        spinnerPlayers = new JSpinner();
        labelRealPlayers = new JLabel();
        spinnerRealPlayers = new JSpinner();
        labelVariante = new JLabel();
        spinnerVariante = new JSpinner();
        labelInfo = new JLabel();
        labelInfo2 = new JLabel();
        buttonStartGame = new JButton();

        //======== this ========
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
        (0,0,0,0), "",javax.swing.border.TitledBorder.CENTER,javax.swing.border
        .TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt
        .Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void
        propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException()
        ;}});
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "0[200,fill]0" +
            "[200,fill]0" +
            "[200,fill]0" +
            "[fill]0",
            // rows
            "0[]" +
            "[102]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[50]" +
            "[]" +
            "[]"));

        //---- header ----
        header.setIcon(new ImageIcon(getClass().getResource("/GameConfig.png")));
        add(header, "cell 0 0 4 1");

        //---- labelPlayers ----
        labelPlayers.setText("Nombre de joueurs");
        labelPlayers.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
        add(labelPlayers, "cell 1 1,align left bottom,grow 0 0");

        //---- spinnerPlayers ----
        spinnerPlayers.setModel(new SpinnerNumberModel(3, 3, 4, 1));
        add(spinnerPlayers, "cell 2 1,aligny bottom,growy 0");

        //---- labelRealPlayers ----
        labelRealPlayers.setText("Nombre de joueurs r\u00e9els");
        labelRealPlayers.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
        add(labelRealPlayers, "cell 1 2,align left center,grow 0 0");

        //---- spinnerRealPlayers ----
        spinnerRealPlayers.setModel(new SpinnerNumberModel(1, 1, 4, 1));
        add(spinnerRealPlayers, "cell 2 2,aligny center,growy 0");

        //---- labelVariante ----
        labelVariante.setText("Variante");
        labelVariante.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
        add(labelVariante, "cell 1 3,align left top,grow 0 0");

        //---- spinnerVariante ----
        spinnerVariante.setModel(new SpinnerListModel(new String[] {"Normale", "Al\u00e9atoire", "Cach\u00e9"}));
        add(spinnerVariante, "cell 2 3,aligny center,grow 100 0");

        //---- labelInfo ----
        labelInfo.setText("Une partie de Jest se joue \u00e0 3 ou 4 joueurs !");
        labelInfo.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
        add(labelInfo, "cell 1 5 2 1,alignx center,growx 0");

        //---- labelInfo2 ----
        labelInfo2.setText("La variante concerne les troph\u00e9es.");
        labelInfo2.setFont(new Font("Ubuntu Light", Font.BOLD | Font.ITALIC, 12));
        add(labelInfo2, "cell 1 6 2 1,alignx center,growx 0");

        //---- buttonStartGame ----
        buttonStartGame.setText("Commencer la partie");
        buttonStartGame.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
        add(buttonStartGame, "cell 1 7 2 1,aligny bottom,growy 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel header;
    private JLabel labelPlayers;
    private JSpinner spinnerPlayers;
    private JLabel labelRealPlayers;
    private JSpinner spinnerRealPlayers;
    private JLabel labelVariante;
    private JSpinner spinnerVariante;
    private JLabel labelInfo;
    private JLabel labelInfo2;
    private JButton buttonStartGame;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JSpinner getSpinnerPlayers() {
        return spinnerPlayers;
    }

    public void setSpinnerPlayers(JSpinner spinnerPlayers) {
        this.spinnerPlayers = spinnerPlayers;
    }

    public JSpinner getSpinnerRealPlayers() {
        return spinnerRealPlayers;
    }

    public void setSpinnerRealPlayers(JSpinner spinnerRealPlayers) {
        this.spinnerRealPlayers = spinnerRealPlayers;
    }

    public JSpinner getSpinnerVariante() {
        return spinnerVariante;
    }

    public void setSpinnerVariante(JSpinner spinnerVariante) {
        this.spinnerVariante = spinnerVariante;
    }

    public JButton getButtonStartGame() {
        return buttonStartGame;
    }

    public void setButtonStartGame(JButton buttonStartGame) {
        this.buttonStartGame = buttonStartGame;
    }
}
