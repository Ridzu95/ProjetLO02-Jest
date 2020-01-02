/*
 * Created by JFormDesigner on Thu Dec 19 13:31:42 CET 2019
 */

package fr.utt.lo02.xfmv.jest.vue.graphicInterface;

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
        label1 = new JLabel();
        label2 = new JLabel();
        playersCount = new JSpinner();
        label3 = new JLabel();
        realPlayersCount = new JSpinner();
        label4 = new JLabel();
        varianteSelect = new JSpinner();
        beginGameButton = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
        . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder
        . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .
        awt .Font .BOLD ,12 ), java. awt. Color. red) , getBorder( )) )
        ;  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
        ;
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
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
            "[]"));

        //---- label1 ----
        label1.setText("Configuration de la partie");
        add(label1, "cell 0 1 2 1,alignx center,growx 0");

        //---- label2 ----
        label2.setText("Nombre de joueurs");
        add(label2, "cell 0 3");

        //---- playersCount ----
        playersCount.setModel(new SpinnerNumberModel(3, 3, 4, 1));
        add(playersCount, "cell 1 3,alignx center,growx 0");

        //---- label3 ----
        label3.setText("Nombre de joueurs r\u00e9els");
        add(label3, "cell 0 5");

        //---- realPlayersCount ----
        realPlayersCount.setModel(new SpinnerNumberModel(0, 0, 4, 1));
        add(realPlayersCount, "cell 1 5,alignx center,growx 0");

        //---- label4 ----
        label4.setText("Troph\u00e9es");
        add(label4, "cell 0 7");

        //---- varianteSelect ----
        varianteSelect.setModel(new SpinnerListModel(new String[] {"Normal", "Al\u00e9atoire", "Cach\u00e9"}));
        add(varianteSelect, "cell 1 7");

        //---- beginGameButton ----
        beginGameButton.setText("Commencer la partie");
        add(beginGameButton, "cell 0 9 2 1,alignx center,growx 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JLabel label2;
    private JSpinner playersCount;
    private JLabel label3;
    private JSpinner realPlayersCount;
    private JLabel label4;
    private JSpinner varianteSelect;
    private JButton beginGameButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public JButton getBeginGameButton() {
        return beginGameButton;
    }

    public void setBeginGameButton(JButton beginGameButton) {
        this.beginGameButton = beginGameButton;
    }

    public JSpinner getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(JSpinner playersCount) {
        this.playersCount = playersCount;
    }

    public JSpinner getRealPlayersCount() {
        return realPlayersCount;
    }

    public void setRealPlayersCount(JSpinner realPlayersCount) {
        this.realPlayersCount = realPlayersCount;
    }

    public JSpinner getVarianteSelect() {
        return varianteSelect;
    }

    public void setVarianteSelect(JSpinner varianteSelect) {
        this.varianteSelect = varianteSelect;
    }

}
